package com.doan.maintenancecard.controller;

import com.doan.maintenancecard.kafka.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class KafkaController {

    private final SendMessage sendMessage;

    @PostMapping("/kafka/send-message")
    public String sendMessage(@RequestBody String message) {
        sendMessage.sendMessage(message);
        return "success";
    }

}
