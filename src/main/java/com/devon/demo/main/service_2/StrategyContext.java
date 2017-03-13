package com.devon.demo.main.service_2;

import ai.api.model.Fulfillment;
import ai.api.model.Result;

/**
 * Created by Devon on 3/12/2017.
 */
public class StrategyContext {

  private IValidation validation;

  public StrategyContext() {
  }

  public void setValidation(IValidation validation) {
    this.validation = validation;
  }

  public String performAction(Fulfillment fulfillment, Result result) {

    return validation.valid(fulfillment,result);
  }
}
