package com.earlnt.mydb.controllers;

import com.earlnt.mydb.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping("/message")
    public Message sayMessage() {
        return new Message("Hello, World!");
    }
}
