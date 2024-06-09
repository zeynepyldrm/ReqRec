package com.zeynep.ReqRec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeynep.ReqRec.dto.RequestDTO;

@RestController
public class APIController {
    @PostMapping("/")
    public void PostRequest(@RequestBody RequestDTO request) {
        System.out.printf("Message: %s\n", request.message);
    }
}
