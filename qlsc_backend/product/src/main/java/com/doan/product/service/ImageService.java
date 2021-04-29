package com.doan.product.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private WebClient webClient;

    @HystrixCommand(fallbackMethod = "insertFallBackImage",
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
        }, threadPoolProperties = {
        @HystrixProperty(name = "coreSize", value = "20"),
        @HystrixProperty(name = "maxQueueSize", value = "20")
    })
    public String insertImage(MultipartFile file) {

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        File tmp = convert(file);
        map.add("file", new FileSystemResource(tmp));

        String result = webClient.post()
            .uri("http://localhost:8082/admin/uploadFile")
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .body(BodyInserters.fromMultipartData(map))
            .retrieve()
            .bodyToMono(String.class)
            .block();
        tmp.delete();
        return result;
    }

    public String insertFallBackImage(MultipartFile file) {
        return null;
    }

    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convFile;
    }
}
