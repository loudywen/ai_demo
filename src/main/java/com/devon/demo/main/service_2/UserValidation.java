package com.devon.demo.main.service_2;

import ai.api.model.Fulfillment;
import ai.api.model.Result;
import com.devon.demo.main.AiDemoApplication;
import com.devon.demo.main.constant.CommonConstant;
import com.devon.demo.main.service.DummyDB;
import com.devon.demo.util.Utility;

/**
 * Created by Devon on 3/12/2017.
 */
public class UserValidation implements IValidation {

  private DummyDB dummyDB;

  public UserValidation() {
    dummyDB = (DummyDB) AiDemoApplication.getApplicationContext().getBean("dummyDBImpl");
  }

  @Override
  public String valid(Fulfillment fulfillment, Result result) {
    String response;
    if (dummyDB.findUserID(result.getStringParameter(CommonConstant.USER_ID).toLowerCase())) {
      response = CommonConstant.RESPONSE_SENTENCE[0];
      Utility.resetUserIdContext(fulfillment);
    } else {
      response = CommonConstant.RESPONSE_SENTENCE[1];
    }
    return response;
  }
}
