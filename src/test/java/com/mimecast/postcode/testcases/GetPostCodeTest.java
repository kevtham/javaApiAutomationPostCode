package com.mimecast.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.common.Validator;
import com.mimecast.postcode.model.GetPostCodeResponse;
import com.mimecast.postcode.service.GetPostCodeService;

public class GetPostCodeTest extends GetPostCodeService {

    final static Logger log = Logger.getLogger(GetPostCodeTest.class);
    Validator validate = new Validator();

    @Test
    public void test_getPostcode_success() {

        // construct url
        String url = Constants.URL + Constants.FORWARD_SLASH + getRandomPostalCode();
        String response = getPostCode(url);

        // parse
        GetPostCodeResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        validate.validatePostCodeResults(postCoderesponse);
        validate.validatePostCodes(postCoderesponse);
    }

    private String getRandomPostalCode() {
        String response = getPostCode(Constants.GET_RANDOM_POSTCODE_URL);
        // parse
        GetPostCodeResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeResponse.class);

        return postCoderesponse.getResult().getPostcode();
    }
}
