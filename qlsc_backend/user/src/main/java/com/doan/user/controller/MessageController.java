package com.doan.user.controller;

import com.doan.user.model.UserResponse;
import com.doan.user.security.AppAuthHelper;
import com.doan.user.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final AppAuthHelper appAuthHelper;

    @GetMapping("messages")
    public ResponseEntity<Map<String, Object>> getAllMessage(
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "page", defaultValue = "1") int page) {
        String userId = appAuthHelper.httpCredential().getUserId();
        Map<String, Object> map = messageService.getListMessage(userId, page, size);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("messages/{id}")
    public UserResponse readMessage(@PathVariable int id) {
        boolean success = messageService.readMessage(id);
        return new UserResponse(success);
    }
}
