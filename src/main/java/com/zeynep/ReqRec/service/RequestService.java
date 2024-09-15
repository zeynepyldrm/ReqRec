package com.zeynep.ReqRec.service;

import com.zeynep.ReqRec.model.Request;
import com.zeynep.ReqRec.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository repository;

    AesService aesService = new AesService();

    public Request saveMessage(Request request) throws Exception {
        if(Strings.isEmpty(request.getMessage())){
            throw new Exception("message cannot be null");
        }
        request.setMessage(aesService.encryptText(request.getMessage()));
        return repository.save(request);
    }

    public List<Request> getAllRequest() throws Exception {
        List<Request> requestsInEncrypt = repository.findAll();
        List<Request> requestsInDecrypt = new ArrayList<>();
        for(Request req : requestsInEncrypt){
            Request request = new Request();
            request.setId(req.getId());
            request.setMessage(aesService.decryptText(req.getMessage()));
            requestsInDecrypt.add(request);
        }
        return requestsInDecrypt;
    }
}
