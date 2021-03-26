package com.doan.user.controller;

import com.doan.user.exception.commonException.NotFoundException;
import com.doan.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity getAllMessage(@RequestParam("size") int size,
                                        @RequestParam("page") int page){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Map<String, Object> map = messageService.getListMessage(authentication.getName(),page,size);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity readMessage(@PathVariable int id) throws NotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        messageService.readMessage(id,authentication.getName());
        return ResponseEntity.ok().build();
    }
}
