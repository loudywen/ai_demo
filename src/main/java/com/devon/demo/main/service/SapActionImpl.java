package com.devon.demo.main.service;

import com.devon.demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by diwenlao on 2/28/17.
 */
@Service
public class SapActionImpl implements SapAction {

    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SapActionImpl.class);
    private String categoryHolder;

    @Autowired
    private Environment env;

    @Autowired
    public SapActionImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String takeSapAction(String category, String userid) {
        this.categoryHolder = category;
        String sapResponse = callSAPToPerformAction(userid);
        return sapResponse;
    }

    private String callSAPToPerformAction(String userid) {
        ResponseEntity<String> sapResponse;
        String url = null;
        try {
            if (categoryHolder.equals("role_detail")) {
                url = String.format(env.getProperty("sap.roles.details"), userid);
            } else if (categoryHolder.equals("reset_login")) {
                url = String.format(env.getProperty("sap.reset"), userid);
            }

            logger.debug("url: {}", url);
            URI uri = new URI(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("mssbots", "Miracle@121"));
            HttpEntity<String> toSAP = new HttpEntity<String>(headers);
            sapResponse = restTemplate.exchange(uri, HttpMethod.GET, toSAP, String.class);
            logger.debug("SAP response: {}", sapResponse.getBody().toString());
            return sapResponse.getBody();
        } catch (URISyntaxException | HttpClientErrorException ex) {
            if (ex instanceof HttpClientErrorException) {
                logger.error(((HttpClientErrorException) ex).getResponseBodyAsString(), ex);
                return ((HttpClientErrorException) ex).getResponseBodyAsString();
            } else {
                logger.error(ex.getMessage(), ex);
                return "We are facing backend issue, please come back later";
            }
        }
    }
}
