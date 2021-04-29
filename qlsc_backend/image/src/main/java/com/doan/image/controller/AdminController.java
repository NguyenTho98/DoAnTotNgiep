package com.doan.image.controller;

import com.doan.image.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {

    private final StorageService storageService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String type = file.getContentType();
        if (type != null && type.equals("image/png") || Objects.equals(type, "image/jpeg")) {
            storageService.store(file);
            return StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        } else {
            return "File không hợp lệ!";
        }
    }

    @GetMapping("test")
    public String testApi() {
        return "Success";
    }
}
