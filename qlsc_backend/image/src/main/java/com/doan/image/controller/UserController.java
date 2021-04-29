package com.doan.image.controller;

import com.doan.image.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final StorageService storageService;

    @GetMapping(path = "/image/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public Resource serveFile(@PathVariable String filename) {
        return storageService.loadAsResource(filename);
    }

}
