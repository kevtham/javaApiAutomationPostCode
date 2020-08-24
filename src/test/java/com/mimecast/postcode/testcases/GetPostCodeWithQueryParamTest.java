package com.mimecast.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.common.ErrorMessage;
import com.mimecast.postcode.common.RandomPostCodeUtil;
import com.mimecast.postcode.common.Validator;
import com.mimecast.postcode.model.GetPostCodeQueryParamResponse;
import com.mimecast.postcode.service.GetPostCodeService;

public class GetPostCodeWithQueryParamTest extends GetPostCodeService {

    final static Logger log = Logger.getLogger(GetPostCodeWithQueryParamTest.class);

    Validator validate = new Validator();
    RandomPostCodeUtil randomPostService = new RandomPostCodeUtil();

    /*
     * Below test validate the successful scenario.
     */
    @Test
    public void test_getPostcode_with_query_param_success() {

        // construct url and render random post Code
        String response = getPostCodeQueryParam(Constants.GET_HOST, randomPostService.getRandomPostalCode());

        // parse
        GetPostCodeQueryParamResponse postCoderesponse = new Gson().fromJson(response,
                GetPostCodeQueryParamResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertFalse(postCoderesponse.getResult().isEmpty());
        assertEquals(200, postCoderesponse.getStatus());
        for (int i = 0; i < postCoderesponse.getResult().size(); i++) {
            validate.validatePostCodeResults(postCoderesponse.getResult().get(i));
            validate.validatePostCodes(postCoderesponse.getResult().get(i).getCodes());
        }
    }

    /*
     * Below test validate the negative scenario.(invalid post code)
     */
    @Test
    public void test_getPostcode_with_query_param_invalid_postcode() {

        // construct url and render random post Code
        String response = getPostCodeQueryParam(Constants.GET_HOST, Constants.INVALID_POST_CODE);

        // parse
        GetPostCodeQueryParamResponse postCoderesponse = new Gson().fromJson(response,
                GetPostCodeQueryParamResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        assertNull(postCoderesponse.getResult());
    }

    /*
     * Below test validate the negative scenario.(empty post code)
     */
    @Test
    public void test_getPostcode_no_postcode() {

     // construct url and render random post Code
        String response = getPostCodeQueryParam(Constants.GET_HOST, Constants.EMPTY_STRING);

        // parse
        GetPostCodeQueryParamResponse postCoderesponse = new Gson().fromJson(response,
                GetPostCodeQueryParamResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(400, postCoderesponse.getStatus());
        assertEquals(ErrorMessage.EMPTY_POST_CODE, postCoderesponse.getError());
    }
}
