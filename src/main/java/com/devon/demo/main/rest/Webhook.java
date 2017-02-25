package com.devon.demo.main.rest;

import ai.api.model.Fulfillment;
import ai.api.model.Result;
import ai.api.web.AIWebhookServlet;
import com.devon.demo.main.service.Action;
import com.devon.demo.main.service.DummyDB;
import com.devon.demo.main.service.TakeAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.servlet.annotation.WebServlet;

/**
 * Created by diwenlao on 2/20/17.
 */

@WebServlet(asyncSupported = true, value = "/webhook")
public class Webhook extends AIWebhookServlet {

    @Autowired
    private Environment env;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String EXCEPTION_RESPONSE = "Service is under maintenance or some exception happen at the backend :(";
    private static final Logger logger = LoggerFactory.getLogger(Webhook.class);
    private RestTemplate restTemplate;
    private DummyDB dummyDB;

    @Autowired
    private Webhook(RestTemplate restTemplate, DummyDB dummyDB) {
        this.restTemplate = restTemplate;
        this.dummyDB = dummyDB;
    }

    @Override
    protected void doWebhook(AIWebhookRequest request, Fulfillment response) {

        logger.debug("Received request: {}", request);


        try {
            /*URI uri = new URI(env.getProperty("dummrest1.rest1"));
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Content-Type", "text/plain");
            headers.add("Authorization", "Basic " + Utility.encodeAuthorization("user", "password"));
            HttpEntity<String> toRest2 = new HttpEntity<String>(request.getResult().getResolvedQuery(), headers);

            ResponseEntity<String> response2 = restTemplate.exchange(uri, HttpMethod.POST, toRest2, String.class);
            */


            Result result = request.getResult();
            Action action = new TakeAction(result, (request.getOriginalRequest() != null) ? request.getOriginalRequest().getSource() : null, dummyDB, restTemplate);


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
            response.setSpeech(action.responseToAction());

        } catch (Exception ex) {
            response.setSpeech(EXCEPTION_RESPONSE);
            logger.error(ex.getMessage(), ex);
        }
    }


}


