package com.devon.demo.main.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by diwenlao on 2/21/17.
 */
@RestController
public class Rest2 {

    private static final Logger logger = LoggerFactory.getLogger(Rest2.class);


    @GetMapping("/anotherrestcall")
    public ResponseEntity<String> anotherRestCall(RequestEntity<String> requestEntity) {
        logger.info("got hit from Rest1");
        logger.info("======\n {}", requestEntity.getBody());

        ResponseEntity<String> responseEntity = new ResponseEntity<>("I DON'T CARE :(", HttpStatus.OK);
        return responseEntity;
    }
}
