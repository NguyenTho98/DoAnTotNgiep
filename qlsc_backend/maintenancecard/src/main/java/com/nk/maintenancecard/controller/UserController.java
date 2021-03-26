package com.nk.maintenancecard.controller;

import com.nk.maintenancecard.dto.MaintenanceCardDTO;
import com.nk.maintenancecard.exception.commonException.NotFoundException;
import com.nk.maintenancecard.service.MaintenanceCardService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private MaintenanceCardService maintenanceCardService;

    @GetMapping("/user/maintenanceCards")
    public ResponseEntity<MaintenanceCardDTO> searchMaintenanceCard(@RequestParam Long id, @RequestParam String hmac) throws NotFoundException {
        try {
            var hmacSHA256 = Mac.getInstance("HmacSHA256");
            var secretKey = new SecretKeySpec("qlsc".getBytes(), "HmacSHA256");
            hmacSHA256.init(secretKey);

            String result =  new String(Base64.encodeBase64(hmacSHA256.doFinal(id.toString().getBytes(StandardCharsets.UTF_8)))).trim().replace("/",".");
            if(!result.equals(hmac)){
                throw new NotFoundException("Not found maintenance card");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }

        MaintenanceCardDTO maintenanceCardDTO = maintenanceCardService.getMaintenanceCardById(id,"",3);
        return new ResponseEntity(maintenanceCardDTO, HttpStatus.OK);
    }

}
