package com.devon.demo.main.rest;

import ai.api.model.Fulfillment;
import ai.api.web.AIWebhookServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.annotation.WebServlet;

/**
 * Created by diwenlao on 2/20/17.
 */

@WebServlet("/webhook")
public class Rest1 extends AIWebhookServlet {

    private static final Logger logger = LoggerFactory.getLogger(Rest1.class);
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    protected void doWebhook(AIWebhookRequest request, Fulfillment response) {
        logger.info("got hit by api.ai!");
        ResponseEntity<String> response2 = restTemplate.postForEntity("/anotherrestcall", request.getResult().getResolvedQuery(), String.class);
        String backToBot = response2.getBody();


        response.setSpeech("You said: " + request.getResult().getResolvedQuery()+ " I said: "+ backToBot);

    }
}


