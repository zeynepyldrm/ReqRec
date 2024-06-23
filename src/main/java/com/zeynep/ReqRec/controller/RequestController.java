package com.zeynep.ReqRec.controller;

import com.zeynep.ReqRec.model.Request;
import com.zeynep.ReqRec.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping("/")
    public void addRequest(@RequestBody Request request) throws Exception {
       requestService.saveMessage(request);
    }

    @GetMapping("/messages")
    public List<Request> getAllRequests() throws Exception {
        return requestService.getAllRequest();
    }


}
