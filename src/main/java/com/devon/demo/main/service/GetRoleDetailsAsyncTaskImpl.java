/*
package com.devon.demo.main.service;

import com.devon.demo.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

*/
/**
 * Created by diwenlao on 2/21/17.
 *//*

@Service
public class GetRoleDetailsAsyncTaskImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(GetRoleDetailsAsyncTaskImpl.class);

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> buildResponse(String userid) {
        ResponseEntity<String> response2;

        try {
            String url = String.format(env.getProperty("sap.roles.details"), userid);

            logger.debug("url: {}", url);
            URI uri = new URI(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("mssbots", "Miracle@121"));

            HttpEntity<String> toSAPDetails = new HttpEntity<String>(headers);
            response2 = restTemplate.exchange(uri, HttpMethod.GET, toSAPDetails, String.class);
            logger.debug("response from sap get role details: {}", response2.getBody().toString());
            return response2;
        } catch (Exception ex) {
            response2 = new ResponseEntity<>("SAP server is down, please come back later", HttpStatus.OK);

            logger.error(ex.getMessage(), ex);
            return response2;
        }

    }
}
*/
