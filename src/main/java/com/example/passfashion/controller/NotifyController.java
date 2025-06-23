package com.example.passfashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/notify/{userId}")
    public void notifyUser(@PathVariable String userId, @RequestBody String message) {
        System.out.println("thong bao /notify/{userId}");
        messagingTemplate.convertAndSend("/topic/user." + userId, message);
    }
}
