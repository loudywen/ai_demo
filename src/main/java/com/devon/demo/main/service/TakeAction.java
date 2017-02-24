package com.devon.demo.main.service;

import ai.api.model.Result;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diwenlao on 2/23/17.
 */

public class TakeAction implements Action {

    private Result result;
    private String source;

    private List dummyUserDB = Arrays.asList("dilao1");
    

    private String[] responseSentence = {"can you provide your pin number", "you are not a valid user"};

    private static final String USER_ID = "userid";
    private static final String PIN = "pin";
    private static final String ENTER_USER_ID = "enter.userid";
    private static final String USER_VALID = "user.valid";

    public TakeAction(Result result, String source) {
        this.result = result;
        this.source = source;
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
        if (dummyUserDB.contains(result.getParameters().get(USER_ID))) {
            response = responseSentence[0];
        } else {
            response = responseSentence[1];
        }
        return response;
    }

    private String userValid() {
        return null;
    }
}
