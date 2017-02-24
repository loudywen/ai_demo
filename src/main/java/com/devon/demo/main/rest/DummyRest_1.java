/*
package com.devon.demo.main.rest;

import com.devon.demo.main.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

*/
/**
 * Created by diwenlao on 2/21/17.
 *//*

@RestController
public class DummyRest_1 {

    private static final Logger logger = LoggerFactory.getLogger(DummyRest_1.class);


    private final TaskService taskService;

    @Autowired
    public DummyRest_1(TaskService taskService) {

        this.taskService = taskService;

    }


    @PostMapping ("/anotherrestcall")
    @ResponseBody
    public DeferredResult<String> anotherRestCall(RequestEntity<String> requestEntity) {
        logger.debug("Async call - enter");
        logger.debug(requestEntity.getBody());
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(taskService::buildResponse)
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result.getBody()));
        logger.debug("Async call - release");

        return deferredResult;
    }

}
*/
