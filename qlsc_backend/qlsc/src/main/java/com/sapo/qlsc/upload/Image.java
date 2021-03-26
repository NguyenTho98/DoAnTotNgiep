package com.sapo.qlsc.upload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Image {
    private final String directory = "product-image\\";
    private MultipartFile image;
    private String imageName;

    public Image(MultipartFile file) {
        this.image = file;
    }

    public String getImageName() throws IOException{
        byte[] bytes = image.getBytes();
        String fileType = image.getContentType().substring(6, image.getContentType().length());
        String newFileName = new Date().getTime() + "." + fileType;
        Path path = Paths.get(directory + newFileName);
        Files.write(path, bytes);
        this.setImageName(newFileName);
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}
