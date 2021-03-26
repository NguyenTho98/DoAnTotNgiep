package com.doan.user.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin/")
public class QRCodeController {

    @GetMapping("hmac/{id}")
    public String initHmac(@PathVariable String id){
        try {
            var hmacSHA256 = Mac.getInstance("HmacSHA256");
            var secretKey = new SecretKeySpec("qlsc".getBytes(), "HmacSHA256");
            hmacSHA256.init(secretKey);

            return new String(Base64.encodeBase64(hmacSHA256.doFinal(id.getBytes(StandardCharsets.UTF_8)))).trim();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return null;
        }
    }

}
