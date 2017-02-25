package com.devon.demo.main.service;

import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.devon.demo.util.Utility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


/**
 * Created by diwenlao on 2/23/17.
 */

public class TakeAction implements Action {

    private Result result;
    private String source;
    private DummyDB dummyDB;
    private RestTemplate restTemplate;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Logger logger = LoggerFactory.getLogger(TakeAction.class);

    private String[] responseSentence = {"can you provide your pin number", "you are not a valid user, do you want to provide your user id again"};

    //    private static final String FOR_RESPONSE_BACK_TO_SLACK_SENDER = "<@#generic.slack_user_id> ";
    private static final String FOR_RESPONSE_BACK_TO_SLACK_SENDER = "";

    private static final String USER_ID = "userid";
    private static final String PIN = "pin";
    private static final String ENTER_USER_ID = "enter.userid";
    private static final String PIN_INTENT = "pin";
    private TaskService taskService;
    private Environment env;

    public TakeAction(Result result, String source, DummyDB dummyDB, RestTemplate restTemplate) {
        this.result = result;
        this.source = source;
        this.dummyDB = dummyDB;
        this.restTemplate = restTemplate;
        this.taskService = (GetRoleDetailsAsyncTaskImpl) AiDemoApplication.getApplicationContext().getBean("getRoleDetailsAsyncTaskImpl");
        this.env = AiDemoApplication.getApplicationContext().getEnvironment();
    }


    @Override
    public String responseToAction() {

        String response = null;
        if (result.getAction().equalsIgnoreCase(ENTER_USER_ID)) {
            response = enterUserId();
        } else if (result.getAction().equalsIgnoreCase(PIN_INTENT)) {
            response = userValid();
        }
        return response;
    }


    private String enterUserId() {

        String response;
        if (dummyDB.findUserID(result.getStringParameter(USER_ID))) {
            response = checkSource(responseSentence[0]);

        } else {
            response = checkSource(responseSentence[1]);
        }
        return response;
    }


    private String userValid() {
        String response;


        if (dummyDB.findPin(result.getStringParameter(USER_ID), result.getIntParameter(PIN))) {
            // call SAP here later

            ResponseEntity<String> re = callSAPToGetRoleDetails(result.getStringParameter(USER_ID));

            response = checkSourceForPinValid(re.getBody().toString());

        } else {
            response = checkSource("Invalid PIN, do you want to try again?");
        }

        return response;
    }

    private ResponseEntity<String> callSAPToGetRoleDetails(String userid) {
        ResponseEntity<String> response2;
        try {
            String url = String.format(env.getProperty("sap.roles.details"), userid);

            logger.debug("url: {}", url);
            URI uri = new URI(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("mssbots", "Miracle@121"));

            HttpEntity<String> toRest2 = new HttpEntity<String>(headers);
            response2 = restTemplate.exchange(uri, HttpMethod.GET, toRest2, String.class);
            logger.debug(response2.getBody().toString());

           /* logger.debug("Async call - enter");
           *//* DeferredResult<String> deferredResult = new DeferredResult<>();
            CompletableFuture.supplyAsync(() -> taskService.buildResponse(userid))
                    .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result.getBody()));
            *//*

                logger.error(ex.getMessage(), ex);*/
            /*    return response2;
            }*/

           /* logger.debug("Async call - release");*/


            return response2;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response2 = new ResponseEntity<String>("SAP server is down, please come back later", HttpStatus.OK);
            return response2;
        }

    }

    private String checkSource(String responseMsg) {
        String response;
        if (source != null && source.equals("slack")) {
            response = FOR_RESPONSE_BACK_TO_SLACK_SENDER + responseMsg;

        } else {
            response = responseMsg;
        }
        return response;
    }

    private String checkSourceForPinValid(String responseMsgToSlack) {
        String response;
        if (source != null && source.equals("slack")) {
            logger.debug("============{}", responseMsgToSlack);
            response = FOR_RESPONSE_BACK_TO_SLACK_SENDER + responseMsgToSlack;

        } else {

            if (responseMsgToSlack.equals("SAP server is down, please come back later")) {
                response = responseMsgToSlack;
            } else {
                response = "We will send you an Email with all your details";
            }
        }
        return response;
    }

}
