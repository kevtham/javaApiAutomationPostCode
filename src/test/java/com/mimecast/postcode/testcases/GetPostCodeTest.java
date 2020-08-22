package com.mimecast.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.common.ErrorMessage;
import com.mimecast.postcode.common.Validator;
import com.mimecast.postcode.model.GetPostCodeResponse;
import com.mimecast.postcode.service.GetPostCodeService;
import com.mimecast.postcode.service.RandomPostCodeService;

public class GetPostCodeTest extends GetPostCodeService {

    final static Logger log = Logger.getLogger(GetPostCodeTest.class);
    Validator validate = new Validator();
    RandomPostCodeService randomPostService = new RandomPostCodeService();

    /*
     * Below test validate the successful scenario.
     */
    @Test
    public void test_getPostcode_success() {

        // construct url
        String url = Constants.URL + Constants.FORWARD_SLASH + randomPostService.getRandomPostalCode();
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

    /*
     * Below test validate the negative scenario.(invalid post code)
     */
    @Test
    public void test_getPostcode_invalid_postcode() {

        // construct url
        String url = Constants.URL + Constants.FORWARD_SLASH + Constants.INVALID_POST_CODE;
        String response = getPostCode(url);

        // parse
        GetPostCodeResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(404, postCoderesponse.getStatus());
        assertEquals(ErrorMessage.INVALID_POST_CODE, postCoderesponse.getError());
    }

    /*
     * Below test validate the negative scenario.(empty post code)
     */
    @Test
    public void test_getPostcode_no_postcode() {

        // construct url
        String url = Constants.URL + Constants.FORWARD_SLASH;
        String response = getPostCode(url);

        // parse
        GetPostCodeResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(400, postCoderesponse.getStatus());
        assertEquals(ErrorMessage.EMPTY_POST_CODE, postCoderesponse.getError());
    }
}
