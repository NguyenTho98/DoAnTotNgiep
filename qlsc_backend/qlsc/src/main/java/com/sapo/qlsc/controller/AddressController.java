package com.sapo.qlsc.controller;

import com.sapo.qlsc.dto.DistrictDTO;
import com.sapo.qlsc.dto.WardDTO;
import com.sapo.qlsc.service.DistrictService;
import com.sapo.qlsc.service.WardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin/")
public class AddressController {

    private final DistrictService districtService;

    private final WardService wardService;

    public AddressController(DistrictService districtService, WardService wardService) {
        this.districtService = districtService;
        this.wardService = wardService;
    }

    @GetMapping(path="/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPro(){
        List<DistrictDTO> districts = districtService.getDistricts();
        return new ResponseEntity(districts, HttpStatus.OK);
    }

    @GetMapping(path="/wards/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWardsOfDistrict(@PathVariable("code") String code){
        List<WardDTO> wards = wardService.getWardOfDistrict(code);
        return new ResponseEntity(wards, HttpStatus.OK);
    }
}
