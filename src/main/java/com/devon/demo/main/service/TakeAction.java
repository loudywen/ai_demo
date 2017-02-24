package com.devon.demo.main.service;

import ai.api.model.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * Created by diwenlao on 2/23/17.
 */

public class TakeAction implements Action {

    private Result result;
    private String source;
    private DummyDB dummyDB;
    private RestTemplate restTemplate;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private String[] responseSentence = {"can you provide your pin number", "you are not a valid user"};

    private static final String USER_ID = "userid";
    private static final String PIN = "number-sequence";
    private static final String ENTER_USER_ID = "enter.userid";
    private static final String USER_VALID = "user.valid";

    public TakeAction(Result result, String source, DummyDB dummyDB, RestTemplate restTemplate) {
        this.result = result;
        this.source = source;
        this.dummyDB = dummyDB;
        this.restTemplate = restTemplate;
    }


    @Override
    public String responseToAction() {

        String response = null;
        if (result.getAction().equalsIgnoreCase(ENTER_USER_ID)) {
            response = enterUserId();
        } else if (result.getAction().equalsIgnoreCase(USER_VALID)) {
            response = userValid();
        }
        return response;
    }


    private String enterUserId() {

        String response;
        if (dummyDB.findUserID(result.getStringParameter(USER_ID))) {
            response = responseSentence[0];
        } else {
            response = responseSentence[1];
        }
        return response;
    }

    private String userValid() {
        String response ;


        if(dummyDB.findPin(result.getStringParameter(USER_ID),result.getParameters().get("pin").getAsJsonObject().get(PIN).getAsInt())){
            // call SAP here
            if(source!=null && source.equals("slack")){
                response = "You are all set";
            }else{
                response = "We will send you an Email with all your details";
            }
        }else{
            response = "Invaild PIN";
        }

        return response;
    }
}
