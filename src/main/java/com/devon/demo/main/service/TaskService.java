package com.devon.demo.main.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by diwenlao on 2/21/17.
 */
public interface TaskService {

    ResponseEntity<String> buildResponse(String userid);
}
