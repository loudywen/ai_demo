package com.devon.demo.util;

import ai.api.model.AIOutputContext;
import ai.api.model.Fulfillment;
import ai.api.model.Result;
import com.devon.demo.main.constant.CommonConstant;
import com.devon.demo.main.model.sapdetail.SapDetailRoot;
import com.devon.demo.main.model.sapdetailerror.SapDetailError;
import com.devon.demo.main.model.sapunlock.SapUnlock;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by diwenlao on 2/21/17.
 */
public final class Utility {

  private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private static final Logger logger = LoggerFactory.getLogger(Utility.class);

  public static String encodeAuthorization(String userId, String password) {
    String authorization = (userId + ":" + password);
    return Base64.encodeBase64String(authorization.getBytes());
  }

  public static String decodeAuthorization(String encodedAuthorization) {
    byte[] decodedBytes = Base64.decodeBase64(encodedAuthorization);
    return new String(decodedBytes);
  }

  public static String checkSourceForSapDetailReply(String responseMsgToSlack, Result result,
      String source) {
    String concatMsg = null;
    String response;
    try {

      if (responseMsgToSlack.contains("errordetails")) {
        SapDetailError sapDetailError = gson.fromJson(responseMsgToSlack, SapDetailError.class);
        concatMsg = sapDetailError.getError().getMessage().getValue();
        logger.debug("error concat: {}", concatMsg);
      } else if (result.getStringParameter(CommonConstant.SAP_CATEGORY).equals("role_detail")) {
        SapDetailRoot sapDetailRoot = gson.fromJson(responseMsgToSlack, SapDetailRoot.class);
        StrBuilder    strBuilder    = new StrBuilder();
        sapDetailRoot.getD().getResults().stream().forEach(sapResult -> {
          strBuilder
              .append("Role: " + sapResult.getAgrName() + "     Description: " + sapResult
                  .getAgrText());
          strBuilder.append("\n");
        });
        concatMsg = strBuilder.toString();

        logger.debug("detail concat: {}", concatMsg);
      } else if (result.getStringParameter(CommonConstant.SAP_CATEGORY).equals("reset_login")) {
        SapUnlock sapUnlock = gson.fromJson(responseMsgToSlack, SapUnlock.class);
        concatMsg = sapUnlock.getD().getMessage();
        logger.debug("Reset concat: {}", concatMsg);
      }

      if (source != null && source.equals("slack")
          || result.getStringParameter(CommonConstant.SAP_CATEGORY) != "role_detail") {
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

  public static void resetUserIdContext(Fulfillment fulfillment) {
    AIOutputContext outContext_pre_input = new AIOutputContext();
    outContext_pre_input.setLifespan(0);
    outContext_pre_input.setName("pre-input");
    fulfillment.setContextOut(outContext_pre_input);
  }

  public static void resetPinContext(Fulfillment fulfillment, Result result) {
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

  public static void resetAnotherQuestionContext(Fulfillment fulfillment) {
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
}
