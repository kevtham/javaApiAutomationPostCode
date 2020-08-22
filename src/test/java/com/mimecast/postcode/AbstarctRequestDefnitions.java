package com.mimecast.postcode;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;

public class AbstarctRequestDefnitions {
    final static Logger log = Logger.getLogger(AbstarctRequestDefnitions.class);

    protected HttpClient httpClient;
    final int timeout = 5;

    @Before
    protected void setUp() {
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(setTimeOut()).build();
    }

    protected HttpResponse getResponse(final String uri) {
        HttpResponse response = null;
        try {
            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("accept", "application/json");

            response = httpClient.execute(getRequest);
        } catch (Exception e) {
            log.error("Error Occured on Get Call =>" + e.getMessage());
        }
        return response;
    }

    protected HttpResponse getResponse(final String url, String postCode) {
        HttpResponse response = null;
        try {

            URIBuilder builder = new URIBuilder();
            builder.setHost(url).setParameter("q", postCode);
            URI uri = builder.build();

            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("accept", "application/json");

            response = httpClient.execute(getRequest);
        } catch (Exception e) {
            log.error("Error Occured on Get call using query param =>" + e.getMessage());
        }
        return response;
    }

    protected HttpResponse postResponse(final String url, StringEntity entityObj) {
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost(url);
            request.addHeader("accept", "application/json");
            request.addHeader("content-type", "application/json");
            request.setEntity(entityObj);

            response = httpClient.execute(request);
        } catch (Exception e) {
            log.error("Error Occured on Post call =>" + e.getMessage());
        }
        return response;
    }

    protected RequestConfig setTimeOut() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 100)
                .setConnectionRequestTimeout(timeout * 100).setSocketTimeout(timeout * 100).build();
        return config;
    }
}
