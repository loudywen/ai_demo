package com.devon.demo.main.rest;

import ai.api.model.Fulfillment;
import ai.api.model.Result;
import ai.api.web.AIWebhookServlet;
import com.devon.demo.main.service.Action;
import com.devon.demo.main.service.DummyDB;
import com.devon.demo.main.service.TakeAction;
import javax.servlet.annotation.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by diwenlao on 2/20/17.
 */

@WebServlet(asyncSupported = true, value = "/webhook")
public class Webhook extends AIWebhookServlet {

  private static final String EXCEPTION_RESPONSE = "Service is under maintenance or some exception happen at the backend :(";
  private static final Logger logger             = LoggerFactory.getLogger(Webhook.class);

  private DummyDB dummyDB;
  @Autowired
  private Webhook(DummyDB dummyDB) {
    this.dummyDB = dummyDB;

  }

  @Override
  protected void doWebhook(AIWebhookRequest request, Fulfillment response) {
    logger.debug("Received request: {}", request);

    try {
      Result result = request.getResult();
      Action action = new TakeAction(response, result,
          (request.getOriginalRequest() != null) ? request.getOriginalRequest().getSource() : null,
          dummyDB);

           /* String key = request.getOriginalRequest().getSource();
            String channel = (String) request.getOriginalRequest().getData().get("channel");
            String team = (String) request.getOriginalRequest().getData().get("team");
            String user = (String) request.getOriginalRequest().getData().get("user");
            Message message = new MessageBuilder()
                    .setChannel(channel)
                    .setUsername(user)
                    .setText(action.responseToAction())
                    .build();


            JsonElement json = gson.fromJson(gson.toJsonTree(message), JsonElement.class);

            Map<String, JsonElement> data = new HashMap<>();
            data.put("slack", json);
            response.setData(data);*/

      String temp = action.responseToAction();
      response.setSpeech(temp);
      response.setDisplayText(temp);


    } catch (Exception ex) {
      response.setSpeech(EXCEPTION_RESPONSE);
      logger.error(ex.getMessage(), ex);
    }
  }


}


