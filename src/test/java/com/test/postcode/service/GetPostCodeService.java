package com.test.postcode.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.test.postcode.AbstractClientDefinitions;

public class GetPostCodeService extends AbstractClientDefinitions {

    final static Logger log = Logger.getLogger(GetPostCodeService.class);
    HttpResponse httpResponse = null;

    public String getPostCode(String url) {
        log.info("Attempting to call the getPostCode :::");

        httpResponse = getResponse(url);
        try {
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ParseException | IOException e) {
            log.info("Error occured on parsing the response : method getPostCode  =>" + e.getMessage());
        }
        return null;
    }

    public String getPostCodeQueryParam(String url, String postCode) {
        log.info("Attempting to call the getPostCodeQueryParam :::");

        httpResponse = getResponse(url, postCode);
        try {
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ParseException | IOException e) {
            log.info("Error occured on parsing the response : method getPostCodeQueryParam =>" + e.getMessage());
        }
        return null;
    }
}
