package com.devon.demo.main.service_2;

/**
 * Created by Devon on 3/12/2017.
 */
public class ValidationFactory extends IValidationFactory {

  @Override
  public IValidation getValidationInstance(Action action) throws Exception {
    switch (action)
    {
      case ENTER_USER_ID_ACTION:
        return new UserValidation();
      case PIN_ACTION:
        return new PinValidation();
      case ANOTHER_QUESTION_YES_ACTION:
        return null;
      default:
        throw new Exception( "Unknown action: "+action);
    }

  }
}
