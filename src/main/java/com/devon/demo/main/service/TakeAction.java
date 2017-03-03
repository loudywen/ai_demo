package com.devon.demo.main.service;

import ai.api.model.AIOutputContext;
import ai.api.model.Fulfillment;
import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.devon.demo.main.model.sapdetail.SapDetailRoot;
import com.devon.demo.main.model.sapdetailerror.SapDetailError;
import com.devon.demo.main.model.sapreset.SapResetDetail;
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
    private static final String OLD_SAP_CATEGORY = "old_sap_category";

    private static final String ENTER_USER_ID_ACTION = "enter.userid";
    private static final String PIN_ACTION = "pin";
    private static final String ANOTHER_QUESTION_YES_ACTION = "another.question.yes";

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
        if (result.getAction().equalsIgnoreCase(ENTER_USER_ID_ACTION)) {
            response = userValidate();
        } else if (result.getAction().equalsIgnoreCase(PIN_ACTION)) {
            response = pinValidate();
        } else if (result.getAction().equalsIgnoreCase(ANOTHER_QUESTION_YES_ACTION)) {
            if (result.getStringParameter(OLD_SAP_CATEGORY).equals(result.getStringParameter(SAP_CATEGORY))) {
                response = "You have ask this before, please ask another question or say \"stop/cancel\" to end the conversation, thanks";
            } else {
                String sapResponse = sapAction.takeSapAction(result.getStringParameter(SAP_CATEGORY), result.getStringParameter(USER_ID).toLowerCase());
                response = checkSourceForSapDetailReply(sapResponse);
                resetAnotherQuestionContext();
            }
        }
        return response;
    }


    private String userValidate() {

        String response;
        if (dummyDB.findUserID(result.getStringParameter(USER_ID).toLowerCase())) {
            response = responseSentence[0];
            resetUserIdContext();
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
                resetPinContext();

            } else {
                resetUserIdContext();
                response = "Invalid PIN, do you want to try again?";
            }
        } else {
            response = responseSentence[1];
        }
        return response;
    }



    private void resetUserIdContext() {
        AIOutputContext outContext_pre_input = new AIOutputContext();
        outContext_pre_input.setLifespan(0);
        outContext_pre_input.setName("pre-input");
        fulfillment.setContextOut(outContext_pre_input);
    }

    private void resetPinContext() {
        AIOutputContext outContext_pin = new AIOutputContext();
        outContext_pin.setLifespan(0);
        outContext_pin.setName("pin");

        AIOutputContext outContext_pre_input = new AIOutputContext();
        outContext_pre_input.setLifespan(0);
        outContext_pre_input.setName("pre-input");

        AIOutputContext outContext_cancel_pin = new AIOutputContext();
        outContext_cancel_pin.setLifespan(0);
        outContext_cancel_pin.setName("cancel-pin");

        AIOutputContext outContext_another_question = new AIOutputContext();
        outContext_another_question.setLifespan(1);
        outContext_another_question.setName("another-question");
        outContext_another_question.setParameters(result.getContext("pin").getParameters());

        List<AIOutputContext> listContextToRest = new ArrayList<>();
        listContextToRest.add(outContext_pin);
        listContextToRest.add(outContext_pre_input);
        listContextToRest.add(outContext_cancel_pin);
        listContextToRest.add(outContext_another_question);
        fulfillment.setContextOut(listContextToRest);
    }


    private void resetAnotherQuestionContext() {
        AIOutputContext outContext_another_question = new AIOutputContext();
        outContext_another_question.setLifespan(0);
        outContext_another_question.setName("another-question");

        AIOutputContext outContext_cancel_user = new AIOutputContext();
        outContext_cancel_user.setLifespan(0);
        outContext_cancel_user.setName("cancel-user");

        List<AIOutputContext> listContextToRest = new ArrayList<>();
        listContextToRest.add(outContext_another_question);
        listContextToRest.add(outContext_cancel_user);

        fulfillment.setContextOut(listContextToRest);
    }

    private String checkSourceForSapDetailReply(String responseMsgToSlack) {
        String concatMsg = null;
        String response;
        try {

            if (responseMsgToSlack.contains("errordetails")) {
                SapDetailError sde = mapper.readValue(responseMsgToSlack, SapDetailError.class);
                concatMsg = sde.getError().getMessage().getValue();
                logger.debug("error concat: {}", concatMsg);
            } else if (result.getStringParameter(SAP_CATEGORY).equals("role_detail")) {
                SapDetailRoot sdr = mapper.readValue(responseMsgToSlack, SapDetailRoot.class);
                concatMsg = "Your role is: " + sdr.getD().getAgrname() + ". Role description: " + sdr.getD().getAgrtext();
                logger.debug("detail concat: {}", concatMsg);
            } else if (result.getStringParameter(SAP_CATEGORY).equals("reset_login")) {
                SapResetDetail srd = mapper.readValue(responseMsgToSlack, SapResetDetail.class);
                concatMsg = srd.getD().getMessage();
                logger.debug("Reset concat: {}", concatMsg);
            }

            if (source != null && source.equals("slack") || result.getStringParameter(SAP_CATEGORY) != "role_detail") {
//                response = concatMsg + ". \n Do you have another question? (Current you another question option is unlock your account or role details ^_^)";
                response = concatMsg;

            } else {
                if (responseMsgToSlack.equals("SAP server is down, please come back later")) {
                    response = responseMsgToSlack;
                } else {
//                    response = "We will send you an Email with all your details. Do you have another question? (Current you another question option is unlock your account or role details^_^)";
                    response = "We will send you an Email with all your details.";

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
