package com.mimecast.postcode.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mimecast.postcode.AbstarctRequestDefnitions;
import com.mimecast.postcode.model.post.Postcodes;

public class CreatePostCodeService extends AbstarctRequestDefnitions {

    final static Logger log = Logger.getLogger(CreatePostCodeService.class);
    HttpResponse httpResponse = null;
    Gson gson = new Gson();

    public String postPostalCode(String url, ArrayList<String> postcopdeList) {
        log.info("Attempting to call the postPostalCode :::");

        try {
            Postcodes postcode = new Postcodes();
            postcode.setPostcodes(postcopdeList);
            StringEntity stringEntity = new StringEntity(gson.toJson(postcode));

            httpResponse = postResponse(url, stringEntity);

            return EntityUtils.toString(httpResponse.getEntity());
        } catch (ParseException | IOException e) {
            log.info("Error occured on parsing the response : method postPostalCode  =>" + e.getMessage());
        }
        return null;
    }

}
