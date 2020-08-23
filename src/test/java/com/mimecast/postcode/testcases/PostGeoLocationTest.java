package com.mimecast.postcode.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.mimecast.postcode.common.Constants;
import com.mimecast.postcode.common.Validator;
import com.mimecast.postcode.model.post.Geo;
import com.mimecast.postcode.model.post.Geolocation;
import com.mimecast.postcode.model.post.PostResultsForGeo;
import com.mimecast.postcode.service.CreatePostCodeService;

public class PostGeoLocationTest extends CreatePostCodeService {

    final static Logger log = Logger.getLogger(PostGeoLocationTest.class);

    Validator validate = new Validator();
    Geo geo = new Geo();

    /*
     * Below test validate the successful scenario. - Single Geo Object
     */
    @Test
    public void test_postGeo_one_object_success() {

        // Build Geo Object
        geo.setGeolocations(buildGeoObject());

        // construct url
        String response = postPostalCode(Constants.GET_URL, geo);

        // parse
        PostResultsForGeo postCoderesponse = new Gson().fromJson(response, PostResultsForGeo.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        for (int firstResult = 0; firstResult < postCoderesponse.getResult().size(); firstResult++) {
            for (int subResult = 0; subResult < postCoderesponse.getResult().get(firstResult).getResult()
                    .size(); subResult++) {
                validate.validatePostCodeResults(
                        postCoderesponse.getResult().get(firstResult).getResult().get(subResult));
                validate.validatePostCodes(
                        postCoderesponse.getResult().get(firstResult).getResult().get(subResult).getCodes());
                validate.validateGeoQuery(postCoderesponse.getResult().get(firstResult).getQuery());
                assertNotNull(postCoderesponse.getResult().get(firstResult).getResult().get(subResult).getDistance());
            }

        }
    }

    /*
     * Below test validate the successful scenario. - Many Geo Object
     */
    @Test
    public void test_postGeo_many_object_success() {

        // Build Geo Object
        geo.setGeolocations(buildGeoObject());

        // construct url
        String response = postPostalCode(Constants.GET_URL, geo);

        // parse
        PostResultsForGeo postCoderesponse = new Gson().fromJson(response, PostResultsForGeo.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        for (int firstResult = 0; firstResult < postCoderesponse.getResult().size(); firstResult++) {
            for (int subResult = 0; subResult < postCoderesponse.getResult().get(firstResult).getResult()
                    .size(); subResult++) {
                validate.validatePostCodeResults(
                        postCoderesponse.getResult().get(firstResult).getResult().get(subResult));
                validate.validatePostCodes(
                        postCoderesponse.getResult().get(firstResult).getResult().get(subResult).getCodes());
                validate.validateGeoQuery(postCoderesponse.getResult().get(firstResult).getQuery());
                assertNotNull(postCoderesponse.getResult().get(firstResult).getResult().get(subResult).getDistance());
            }

        }
    }

    /*
     * Below test validate the negative scenario. Bad Request
     */
    @Test
    public void test_postGeo_bad_request_failure() {

        // construct url
        String response = postPostalCode(Constants.GET_URL, geo);

        // parse
        PostResultsForGeo postCoderesponse = new Gson().fromJson(response, PostResultsForGeo.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(400, postCoderesponse.getStatus());
        assertTrue(postCoderesponse.getError().contains(Constants.BAD_REQUEST));
    }

    /*
     * Below test validate the negative scenario. Empty Geo Loaction Object
     */
    @Test
    public void test_postGeo_empty_geoLoactions() {

        List<Geolocation> geoEmptyList = new ArrayList<Geolocation>();

        // Build Geo Object
        geo.setGeolocations(geoEmptyList);

        // construct url
        String response = postPostalCode(Constants.GET_URL, geo);

        // parse
        PostResultsForGeo postCoderesponse = new Gson().fromJson(response, PostResultsForGeo.class);
        log.info("postCoderesponse ->" + response);

        // assert
        assertNotNull(postCoderesponse);
        assertEquals(200, postCoderesponse.getStatus());
        assertTrue(postCoderesponse.getResult().isEmpty());
    }

    private List<Geolocation> buildGeoObject() {
        ArrayList<Geolocation> geoList = new ArrayList<Geolocation>();
        Geolocation geoLocation = new Geolocation();
        geoLocation.setLatitude(50.7186356978817);
        geoLocation.setLongitude(-1.12935802905177);
        geoLocation.setLimit(100);
        geoLocation.setRadius(500);
        geoList.add(geoLocation);

        Geolocation geoLocation2 = new Geolocation();
        geoLocation2.setLatitude(51.4799900627036);
        geoLocation2.setLongitude(-3.1580773127152);
        geoList.add(geoLocation2);

        List<Geolocation> geoAllList = new ArrayList<Geolocation>();
        geoAllList.addAll(geoList);
        return geoAllList;
    }

    private List<Geolocation> buildGeoOneObject() {
        ArrayList<Geolocation> geoList = new ArrayList<Geolocation>();
        Geolocation geoLocation = new Geolocation();
        geoLocation.setLatitude(50.7186356978817);
        geoLocation.setLongitude(-1.12935802905177);
        geoLocation.setLimit(100);
        geoLocation.setRadius(500);
        geoList.add(geoLocation);

        List<Geolocation> geoAllList = new ArrayList<Geolocation>();
        geoAllList.addAll(geoList);
        return geoAllList;
    }
}
