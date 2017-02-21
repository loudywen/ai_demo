package com.devon.demo.main.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by diwenlao on 2/21/17.
 */
@Service
public class DummyAsyncTaskImpl implements TaskService {
    @Override
    public ResponseEntity<String> buildResponse() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("I DON'T CARE :(", HttpStatus.OK);
        return responseEntity;
    }
}
