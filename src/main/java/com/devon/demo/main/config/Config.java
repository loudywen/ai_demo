package com.devon.demo.main.config;

import com.devon.demo.util.IdleConnectionMonitorThread;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by diwenlao on 2/21/17.
 */
@Configuration
public class Config {

    @Autowired
    Environment env;

    @Bean
    public RestTemplate restTemplate() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(Integer.parseInt(env.getProperty("MaxTotalConnection")));
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(Integer.parseInt(env.getProperty("DefaultMaxPerRoute")));
        poolingHttpClientConnectionManager.setValidateAfterInactivity(Integer.parseInt(env.getProperty("ValidateAfterInactivity")));
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClientBuilder.build());
        IdleConnectionMonitorThread staleMonitor = new IdleConnectionMonitorThread(poolingHttpClientConnectionManager);
        staleMonitor.setDaemon(true);
        staleMonitor.start();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;

    }
}
