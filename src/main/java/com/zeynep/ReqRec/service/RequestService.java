package com.zeynep.ReqRec.service;

import com.zeynep.ReqRec.dto.RequestDTO;
import com.zeynep.ReqRec.model.Request;
import com.zeynep.ReqRec.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    AesService aesService = new AesService();

    public RequestService() throws Exception {
    }

    public Request saveMessage(Request request) throws Exception {
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
