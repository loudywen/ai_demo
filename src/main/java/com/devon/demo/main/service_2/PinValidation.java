package com.devon.demo.main.service_2;

import ai.api.model.Fulfillment;
import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.devon.demo.main.constant.CommonConstant;
import com.devon.demo.main.service.DummyDB;
import com.devon.demo.main.service.SapAction;
import com.devon.demo.util.Utility;

/**
 * Created by Devon on 3/12/2017.
 */
public class PinValidation implements IValidation {

  private DummyDB   dummyDB;
  private SapAction sapAction;

  public PinValidation() {
    dummyDB = (DummyDB) AiDemoApplication.getApplicationContext().getBean("dummyDBImpl");
    this.sapAction = (SapAction) AiDemoApplication.getApplicationContext()
        .getBean("sapActionImpl");
  }

  @Override
  public String valid(Fulfillment fulfillment, Result result) {
    String sapResponse;
    String response;
    if (dummyDB.findUserID(result.getStringParameter(CommonConstant.USER_ID).toLowerCase())) {
      if (dummyDB
          .findPin(result.getStringParameter(CommonConstant.USER_ID).toLowerCase(),
              result.getIntParameter(CommonConstant.PIN))) {
        sapResponse = sapAction
            .takeSapAction(result.getStringParameter(CommonConstant.SAP_CATEGORY),
                result.getStringParameter(CommonConstant.USER_ID).toLowerCase());
        response = Utility.checkSourceForSapDetailReply(sapResponse,result,"");
        Utility.resetPinContext(fulfillment, result);

      } else {
        Utility.resetUserIdContext(fulfillment);
        response = "Invalid PIN, do you want to try again?";
      }
    } else {
      response = CommonConstant.RESPONSE_SENTENCE[1];
    }
    return response;
  }
}
