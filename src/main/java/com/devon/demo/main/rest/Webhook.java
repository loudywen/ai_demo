package com.devon.demo.main.rest;

import ai.api.model.Fulfillment;
import ai.api.web.AIWebhookServlet;
import com.devon.demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.annotation.WebServlet;
import java.net.URI;

/**
 * Created by diwenlao on 2/20/17.
 */

@WebServlet("/webhook")
public class Webhook extends AIWebhookServlet {

    private static final Logger logger = LoggerFactory.getLogger(Webhook.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void doWebhook(AIWebhookRequest request, Fulfillment response) {
        logger.debug("got hit by api.ai!");
        logger.debug("Client ask: {}", request.getResult().getResolvedQuery());
        String url = "http://52.41.71.62:8080/anotherrestcall";
        try {
            URI uri = new URI(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("user", "password"));
            HttpEntity<String> toRest2 = new HttpEntity<String>(request.getResult().getResolvedQuery(), headers);

            ResponseEntity<String> response2 = restTemplate.exchange(uri, HttpMethod.POST, toRest2, String.class);
            String backToBot = response2.getBody();

            response.setSpeech("You said: " + request.getResult().getResolvedQuery() + "  I said: " + backToBot);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }


    }
}

