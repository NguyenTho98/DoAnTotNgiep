package com.doan.maintenancecard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.doan.maintenancecard.dto.MaintenanceCardDTO;
import com.doan.maintenancecard.exception.CodeExistedException;
import com.doan.maintenancecard.exception.commonException.NotFoundException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotEnoughProductException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotFoundRepairmanException;
import com.doan.maintenancecard.exception.maintenanceCardException.NotUpdateException;
import com.doan.maintenancecard.model.MaintenanceCardCustomer;
import com.doan.maintenancecard.model.MaintenanceCardFilter;
import com.doan.maintenancecard.service.MaintenanceCardDetailService;
import com.doan.maintenancecard.service.MaintenanceCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/")
public class MaintenanceCardController {

    private final MaintenanceCardService maintenanceCardService;
    private final MaintenanceCardDetailService maintenanceCardDetailService;
    // Kiem tra quyen: NV dieu phoi
    @PostMapping("maintenanceCards")
    public ResponseEntity<MaintenanceCardDTO> insertMaintenanceCard(@RequestBody MaintenanceCardDTO maintenanceCardDTO) throws NotEnoughProductException, CodeExistedException, JsonProcessingException {

        MaintenanceCardDTO maintenanceCardDTO1 = maintenanceCardService.insertMaintenanceCard(maintenanceCardDTO);
        return new ResponseEntity(maintenanceCardDTO1, HttpStatus.OK);

    }

    // NV quan li, NV dieu phoi, NV sua chua
    @GetMapping("maintenanceCards")
    public ResponseEntity<Map<String,Object>> searchMaintenanceCard(@ModelAttribute("maintenanceCardFilter") MaintenanceCardFilter maintenanceCardFilter){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Map<String,Object> allMaintenanceCard = maintenanceCardService.searchMaintenanceCard(maintenanceCardFilter,authentication.getName(),Integer.parseInt(roles.get(0).split("_")[1]));
        return new ResponseEntity(allMaintenanceCard, HttpStatus.OK);
    }

    // NV quan li, NV dieu phoi, NV sua chua
    @GetMapping("maintenanceCards/{id}")
    public ResponseEntity<MaintenanceCardDTO> searchMaintenanceCard(@PathVariable Long id) throws NotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        MaintenanceCardDTO maintenanceCardDTO = maintenanceCardService.getMaintenanceCardById(id,authentication.getName(),Integer.parseInt(roles.get(0).split("_")[1]));
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

    // NV dieu phoi
    @PutMapping("maintenanceCards/{id}")
    public ResponseEntity<MaintenanceCardDTO> updateMaintenanceCard(@RequestBody MaintenanceCardDTO maintenanceCardDTO,@PathVariable Long id) throws NotEnoughProductException, NotFoundException, CodeExistedException, NotUpdateException, JsonProcessingException {
        maintenanceCardDTO.setId(id);
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        MaintenanceCardDTO maintenanceCardDTO1 = maintenanceCardService.updateMaintenanceCard(maintenanceCardDTO,authentication.getName(),Integer.parseInt(roles.get(0).split("_")[1]));
        return new ResponseEntity(maintenanceCardDTO1, HttpStatus.OK);
    }
    @GetMapping("/maintenanceCards/customer")
    public ResponseEntity<Map<String,Object>> getMaintenanceCardsByIdCustomer(@ModelAttribute("maintenanceCardCustomer") MaintenanceCardCustomer maintenanceCardCustomer){
        System.out.println(maintenanceCardCustomer);
        Map<String,Object> allMaintenanceCards = maintenanceCardService.getMaintenanceCardByIdCustomer(maintenanceCardCustomer);
        return new ResponseEntity<>(allMaintenanceCards, HttpStatus.OK);

    }

    // Kiem tra quyen : NV sua chua
    @PutMapping("maintenanceCards/workStatus/{id}")
    public ResponseEntity<MaintenanceCardDTO> updateWorkStatusMaintenanceCard(@PathVariable Long id) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        MaintenanceCardDTO maintenanceCardDTO = maintenanceCardService.updateAllStatusMaintenanceCard(id,authentication.getName(),Integer.parseInt(roles.get(0).split("_")[1]));
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

    // Kiem tra quyen : NV sua chua
    @PutMapping(path = "maintenanceCards/workStatus",consumes = MediaType.ALL_VALUE)
    public ResponseEntity updateMultiAllWorkStatusMaintenanceCard(@RequestBody  Long[] ids) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<MaintenanceCardDTO> maintenanceCardDTOs = new ArrayList<>();
        int n = ids.length;
        for(int i=0;i<n;i++) {
            MaintenanceCardDTO maintenanceCardDTO = maintenanceCardService.updateAllStatusMaintenanceCard(ids[i],authentication.getName(),Integer.parseInt(roles.get(0).split("_")[1]));
            maintenanceCardDTOs.add(maintenanceCardDTO);
        }
        return new ResponseEntity(maintenanceCardDTOs, HttpStatus.OK);
    }

    // Kiem tra quyen : NV sua chua
    @PutMapping("maintenanceCardDetails/status/{id}")
    public ResponseEntity<MaintenanceCardDTO> updateStatusMaintenanceCardDetail(@PathVariable Long id) throws NotFoundException, NotFoundRepairmanException, JsonProcessingException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        MaintenanceCardDTO maintenanceCardDTO = maintenanceCardDetailService.updateStatusMaintenanceCardDetail(id,authentication.getName());
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

    @DeleteMapping("maintenanceCards/{id}")
    public ResponseEntity<Boolean> deleteMaintenanceCard(@PathVariable Long id) throws NotFoundException, NotFoundRepairmanException, NotEnoughProductException, JsonProcessingException {
        boolean check = maintenanceCardService.deleteMaintenanceCard(id);
        return new ResponseEntity(check, HttpStatus.OK);
    }

    @PutMapping("maintenanceCards/returnDate/{id}")
    public ResponseEntity<Boolean> setReturnDate(@PathVariable Long id) throws NotFoundException, NotFoundRepairmanException, NotEnoughProductException, JsonProcessingException {
        MaintenanceCardDTO maintenanceCardDTO = maintenanceCardService.setReturnDate(id);
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

    @GetMapping("users/maintenanceCards/{userid}")
    public ResponseEntity<Map<String,Object>> getMaintenanceCardByUserId(@RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
                                                                         @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
                                                                         @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                                                         @RequestParam(value = "descending", defaultValue = "false") boolean descending,
                                                                         @RequestParam(value = "code", defaultValue = "") String code,
                                                                         @PathVariable(value = "userid",required = true)Long userid,
                                                                         @RequestParam(value = "payStatus",required = false,defaultValue = "0,1") byte[] payStatus,
                                                                         @RequestParam(value = "workStatus",required = false,defaultValue = "0,1,2") byte[] workStatus
    ) throws NotFoundException{



        Map<String,Object> map =maintenanceCardService.getMaintenanceCardByRepairMan(pageNum,pageSize,sortBy,descending,userid,code,payStatus,workStatus);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
