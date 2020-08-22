package com.mimecast.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.common.Validator;
import com.mimecast.postcode.model.GetPostCodeQueryParamResponse;
import com.mimecast.postcode.service.GetPostCodeService;
import com.mimecast.postcode.service.RandomPostCodeService;

public class GetPostCodeWithQueryParamTest extends GetPostCodeService{

    final static Logger log = Logger.getLogger(GetPostCodeWithQueryParamTest.class);
    
    Validator validate = new Validator();
    RandomPostCodeService randomPostService = new RandomPostCodeService();

    /*
     * Below test validate the successful scenario.
     */
    @Test
    public void test_getPostcode_with_query_param_success() {

        // construct url and render random post Code
        String response = getPostCodeQueryParam(Constants.URL, randomPostService.getRandomPostalCode());

        // parse
        GetPostCodeQueryParamResponse postCoderesponse = new Gson().fromJson(response, GetPostCodeQueryParamResponse.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
    }
}
