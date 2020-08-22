package com.mimecast.postcode.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mimecast.postcode.AbstarctRequestDefnitions;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.model.GetPostCodeResponse;

public class RandomPostCodeService extends AbstarctRequestDefnitions {

    final static Logger log = Logger.getLogger(RandomPostCodeService.class);

    /*
     * Below method calls the random postcode endpoint to get the postcode at
     * runtime.
     */
    public String getRandomPostalCode() {
        String response = null;
        setUp();
        HttpResponse httpResponse = getResponse(Constants.GET_RANDOM_POSTCODE_URL);

        try {
            response = EntityUtils.toString(httpResponse.getEntity());
        } catch (ParseException | IOException e) {
            log.info("Error occured on parsing the response : method getRandomPostalCode  =>" + e.getMessage());
        }

        // parse
        GetPostCodeResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeResponse.class);

        return postCoderesponse.getResult().getPostcode();
    }

}
