package com.example.passfashion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class NotifyService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public void notifyUser(long userId, String message) {
        System.out.println("notifyUser service thong bao /notify/{userId}: " + userId);
        System.out.println("message: " + message);
        messagingTemplate.convertAndSend("/topic/user." + userId, message);
    }
}
