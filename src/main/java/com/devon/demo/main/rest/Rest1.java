package com.devon.demo.main.rest;

import ai.api.GsonFactory;
import ai.api.model.Fulfillment;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by diwenlao on 2/20/17.
 */
@RestController
public class Rest1 {

    private static final Logger logger = LoggerFactory.getLogger(Rest1.class);
    private Gson gson = GsonFactory.getDefaultFactory().getGson();

    @PostMapping("/webhook")
    public ResponseEntity<Fulfillment> entry(RequestEntity<String> requestEntity) throws IOException {
        logger.info("got hit from api.ai");
        /*Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
           logger.info(value);
        }*/

        logger.info("======\n {}", requestEntity.getBody());
        Fulfillment response = new Fulfillment();
        response.setSpeech("haha, I am from rest endpoint");
        ResponseEntity<Fulfillment> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        return responseEntity;
    }

}


