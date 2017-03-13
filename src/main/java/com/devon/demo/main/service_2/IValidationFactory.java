package com.devon.demo.main.service_2;

/**
 * Created by Devon on 3/12/2017.
 */
abstract public class IValidationFactory {

  abstract public IValidation getValidationInstance(Action action) throws Exception;
}
