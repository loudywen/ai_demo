package com.devon.demo.main.service_2;

import ai.api.model.Fulfillment;
import ai.api.model.Result;

/**
 * Created by Devon on 3/12/2017.
 */
public interface IValidation {

  String valid(Fulfillment fulfillment, Result result);
}
