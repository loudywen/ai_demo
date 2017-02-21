package com.devon.demo.main.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by diwenlao on 2/21/17.
 */
@RestController
public class DummyRest_1 {

    private static final Logger logger = LoggerFactory.getLogger(DummyRest_1.class);


    @PostMapping ("/anotherrestcall")
    public ResponseEntity<String> anotherRestCall(RequestEntity<String> requestEntity) {
        logger.debug("got hit from Webhook");

        logger.debug(requestEntity.getBody());
        //logger.debug(requestEntity.getHeaders().toString());



        ResponseEntity<String> responseEntity = new ResponseEntity<>("I DON'T CARE :(", HttpStatus.OK);
        return responseEntity;
    }
}
