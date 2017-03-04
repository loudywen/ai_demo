//package com.devon.demo.main.rest;
//
//import com.devon.demo.main.model.user.User;
//import com.devon.demo.main.service.TaskService;
//import java.util.concurrent.CompletableFuture;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.async.DeferredResult;
//
///**
// * The type Dummy rest 1.
// */
//@RestController
//public class DummyRest_1 {
//
//    private static final Logger logger = LoggerFactory.getLogger(DummyRest_1.class);
//    private final TaskService taskService;
//
//    /**
//     * Instantiates a new Dummy rest 1.
//     *
//     * @param taskService the task service
//     */
//    @Autowired
//    public DummyRest_1(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    /**
//     * Another rest call deferred result.
//     *
//     * @param userid the userid
//     * @return the deferred result
//     */
//    @GetMapping("/findbyuser")
//    @ResponseBody
//    public DeferredResult<User> anotherRestCall( @RequestParam(value="userid") String userid) {
//        logger.debug("Async call - enter");
//        DeferredResult<User> deferredResult = new DeferredResult<>();
//        CompletableFuture.supplyAsync(() -> taskService.buildResponse(userid))
//                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
//        logger.debug("Async call - release");
//
//        return deferredResult;
//    }
//
//}
