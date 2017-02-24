package com.devon.demo.main.service;

import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;


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

    public TakeAction(Result result, String source, DummyDB dummyDB, RestTemplate restTemplate) {
        this.result = result;
        this.source = source;
        this.dummyDB = dummyDB;
        this.restTemplate = restTemplate;
        this.taskService = (GetRoleDetailsAsyncTaskImpl) AiDemoApplication.getApplicationContext().getBean("getRoleDetailsAsyncTaskImpl");
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

            DeferredResult<String> re = callSAPToGetRoleDetails(result.getStringParameter(USER_ID));

            response = checkSourceForPinValid(re, "We will send you an Email with all your details");

        } else {
            response = checkSource("Invalid PIN, do you want to try again?");
        }

        return response;
    }

    private DeferredResult<String> callSAPToGetRoleDetails(String userid) {
//        ResponseEntity<String> response2;
        try {
            /*String url = String.format(env.getProperty("sap.roles.details"), userid);

            logger.debug("url: {}", url);
            URI uri = new URI(url);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("mssbots", "Miracle@121"));

            HttpEntity<String> toRest2 = new HttpEntity<String>(headers);
            response2 = restTemplate.exchange(uri, HttpMethod.GET, toRest2, String.class);
            logger.debug(response2.getBody().toString());
*/

            logger.debug("Async call - enter");
            DeferredResult<String> deferredResult = new DeferredResult<>();
            CompletableFuture.supplyAsync(() -> taskService.buildResponse(userid))
                    .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result.getBody()));
            logger.debug("Async call - release");


            return deferredResult;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
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

    private String checkSourceForPinValid(DeferredResult responseMsgToSlack, String responseMsgToGoogleHome) {
        String response;
        if (source != null && source.equals("slack")) {
            logger.debug("============{}",responseMsgToSlack.getResult());
            response = FOR_RESPONSE_BACK_TO_SLACK_SENDER + responseMsgToSlack.getResult().toString();

        } else {
            response = responseMsgToGoogleHome;
        }
        return response;
    }

}
