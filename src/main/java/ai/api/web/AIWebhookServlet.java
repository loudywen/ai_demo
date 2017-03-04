package ai.api.web;

/***********************************************************************************************************************
 *
 * API.AI Java SDK - client-side libraries for API.AI
 * =================================================
 *
 * Copyright (C) 2017 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic web-hook servlet
 */
public abstract class AIWebhookServlet extends HttpServlet {

  private static final String RESPONSE_CONTENT_TYPE = "application/json";

  private static final String RESPONSE_CHARACTER_ENCODING = "utf-8";

  private static final long serialVersionUID = 1L;

  private final        Gson   gson   = GsonFactory.getDefaultFactory().getGson();
  private static final Logger logger = LoggerFactory.getLogger(AIWebhookServlet.class);


  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Fulfillment output = new Fulfillment();
    // Read from request
    StringBuilder  buffer = new StringBuilder();
    BufferedReader reader = request.getReader();
    String         line;
    while ((line = reader.readLine()) != null) {
      buffer.append(line);
    }
    String data = buffer.toString();
    logger.debug(data);
    doWebhook(gson.fromJson(data, AIWebhookRequest.class), output);

    response.setCharacterEncoding(RESPONSE_CHARACTER_ENCODING);
    response.setContentType(RESPONSE_CONTENT_TYPE);
    gson.toJson(output, response.getWriter());
  }

  /**
   * Web-hook processing method.
   *
   * @param input Received request object
   * @param output Response object. Should be filled in the method.
   */
  protected abstract void doWebhook(AIWebhookRequest input, Fulfillment output);

  /**
   * Web-hook request model class
   */
  protected static class AIWebhookRequest extends AIResponse {

    private static final long serialVersionUID = 1L;

    private OriginalRequest originalRequest;

    /**
     * Get original request object
     *
     * @return <code>null</code> if original request undefined in request object
     */
    public OriginalRequest getOriginalRequest() {
      return originalRequest;
    }
  }

  /**
   * Original request model class
   */
  protected static class OriginalRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String         source;
    private Map<String, ?> data;

    /**
     * Get source description string
     *
     * @return <code>null</code> if source isn't defined in a request
     */
    public String getSource() {
      return source;
    }

    /**
     * Get data map
     *
     * @return <code>null</code> if data isn't defined in a request
     */
    public Map<String, ?> getData() {
      return data;
    }
  }
}
