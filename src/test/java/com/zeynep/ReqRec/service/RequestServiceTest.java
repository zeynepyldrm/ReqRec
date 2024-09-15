package com.zeynep.ReqRec.service;

import com.zeynep.ReqRec.model.Request;
import com.zeynep.ReqRec.repository.RequestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @InjectMocks
    RequestService requestService;


    @Mock
    RequestRepository repository;

    String encryptedText = "Yv+SY6JANdLEZoNrwqKp/Q==";
    String originalText = "hello";

    @Test
    void givenCorrectRequestDto_whenSaveMessage_thenSaveDBAndReturnRequestDto() throws Exception {
        Request request = new Request();
        request.setMessage("hello");
        Mockito.when(repository.save(any())).thenReturn(request);

        Request returnedRequest = requestService.saveMessage(request);

        Mockito.verify(repository, Mockito.times(1)).save(any());
        Assertions.assertEquals(returnedRequest.getMessage(), request.getMessage());
    }

    @Test
    void givenMissingRequest_whenSaveMessage_thenThrowExceptionMustNotNull() {
        Request request = new Request();
        Exception exception = assertThrows(Exception.class, () -> requestService.saveMessage(request));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals(exception.getMessage(), "message cannot be null");
    }

    @Test
    void givenEncryptedListRequest_whenGetAllRequest_thenReturnDecryptedRequestList() throws Exception{
        Request request = new Request();
        request.setMessage(encryptedText);
        List<Request> encryptedList = new ArrayList<>();
        encryptedList.add(request);

        Mockito.when(repository.findAll()).thenReturn(encryptedList);

        List<Request> decryptedList = requestService.getAllRequest();

        Assertions.assertEquals(decryptedList.get(0).getMessage(), originalText);

    }
}
