package com.devon.demo.main.service;

import ai.api.model.AIOutputContext;
import ai.api.model.Fulfillment;
import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.devon.demo.main.model.sapdetail.SapDetailRoot;
import com.devon.demo.main.model.sapdetailerror.SapDetailError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by diwenlao on 2/23/17.
 */

public class TakeAction implements Action {

    private Result result;
    private String source;
    private DummyDB dummyDB;
    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(TakeAction.class);

    private String[] responseSentence = {"can you provide your pin number", "you are not a valid user, do you want to provide your user id again"};

    //private static final String FOR_RESPONSE_BACK_TO_SLACK_SENDER = "<@#generic.slack_user_id> ";
    private static final String FOR_RESPONSE_BACK_TO_SLACK_SENDER = "";

    private static final String USER_ID = "userid";
    private static final String PIN = "pin";
    private static final String SAP_CATEGORY = "sap_category";

    private static final String ENTER_USER_ID_INTENT = "enter.userid";
    private static final String PIN_INTENT = "pin";
    private Fulfillment fulfillment;
    private SapAction sapAction;

    public TakeAction(Fulfillment fulfillment, Result result, String source, DummyDB dummyDB) {
        this.result = result;
        this.source = source;
        this.dummyDB = dummyDB;
        this.sapAction = (SapActionImpl) AiDemoApplication.getApplicationContext().getBean("sapActionImpl");
        this.fulfillment = fulfillment;
    }


    @Override
    public String responseToAction() {

        String response = null;
        if (result.getAction().equalsIgnoreCase(ENTER_USER_ID_INTENT)) {
            response = userValidate();
        } else if (result.getAction().equalsIgnoreCase(PIN_INTENT)) {
            response = pinValidate();
        }
        return response;
    }


    private String userValidate() {

        String response;
        if (dummyDB.findUserID(result.getStringParameter(USER_ID).toLowerCase())) {
            response = responseSentence[0];
            resetUserId();
        } else {
            response = responseSentence[1];
        }
        return response;
    }


    private String pinValidate() {
        String sapResponse;
        String response;
        if (dummyDB.findUserID(result.getStringParameter(USER_ID).toLowerCase())) {
            if (dummyDB.findPin(result.getStringParameter(USER_ID).toLowerCase(), result.getIntParameter(PIN))) {
                sapResponse = sapAction.takeSapAction(result.getStringParameter(SAP_CATEGORY), result.getStringParameter(USER_ID).toLowerCase());
                response = checkSourceForSapDetailReply(sapResponse);
                ResetPinContext();

            } else {
                resetUserId();
                response = "Invalid PIN, do you want to try again?";
            }
        } else {
            response = responseSentence[1];
        }
        return response;
    }


    private void resetUserId() {
        AIOutputContext outContext_pre_input = new AIOutputContext();
        outContext_pre_input.setLifespan(0);
        outContext_pre_input.setName("pre-input");
        fulfillment.setContextOut(outContext_pre_input);
    }

    private void ResetPinContext() {
        AIOutputContext outContext_pin = new AIOutputContext();
        outContext_pin.setLifespan(0);
        outContext_pin.setName("pin");

        AIOutputContext outContext_pre_input = new AIOutputContext();
        outContext_pre_input.setLifespan(0);
        outContext_pre_input.setName("pre-input");

        List<AIOutputContext> listContextToRest = new ArrayList<>();
        listContextToRest.add(outContext_pin);
        listContextToRest.add(outContext_pre_input);

        fulfillment.setContextOut(listContextToRest);
    }


    private String checkSourceForSapDetailReply(String responseMsgToSlack) {
        String concatMsg;
        String response;
        try {

            if (responseMsgToSlack.contains("errordetails")) {
                SapDetailError sde = mapper.readValue(responseMsgToSlack, SapDetailError.class);
                concatMsg = sde.getError().getMessage().getValue();
                logger.debug("error concat:{}", concatMsg);
            } else {
                SapDetailRoot sdr = mapper.readValue(responseMsgToSlack, SapDetailRoot.class);
                concatMsg = "Your role is: " + sdr.getD().getAgrname() + ". Role description: " + sdr.getD().getAgrtext();
                logger.debug("detail concat:{}", concatMsg);
            }

            if (source != null && source.equals("slack")) {
                response = concatMsg;
            } else {
                if (responseMsgToSlack.equals("SAP server is down, please come back later")) {
                    response = responseMsgToSlack;
                } else {
                    response = "We will send you an Email with all your details";
                    //TODO Send Email here later
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            response = "We are having issue with our backend system, please come back later";
        }
        return response;
    }

}
