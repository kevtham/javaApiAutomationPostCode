package com.mimecast.postcode.common;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;

import com.mimecast.postcode.model.GetPostCodeResponse;

public class Validator {
    final static Logger log = Logger.getLogger(Validator.class);
    
    public void validatePostCodeResults(GetPostCodeResponse response) {
        log.info("Attempting to validate the PostCodeResults");

        assertNotNull(response.getResult().getPostcode());
        assertNotNull(response.getResult().getQuality());
        assertNotNull(response.getResult().getEastings());
        assertNotNull(response.getResult().getNorthings());
        assertNotNull(response.getResult().getCountry());
        assertNotNull(response.getResult().getNhs_ha());
        assertNotNull(response.getResult().getLongitude());
        assertNotNull(response.getResult().getLatitude());
        assertNotNull(response.getResult().getEuropean_electoral_region());
        assertNotNull(response.getResult().getPrimary_care_trust());
        assertNotNull(response.getResult().getRegion());
        assertNotNull(response.getResult().getLsoa());
        assertNotNull(response.getResult().getMsoa());
        assertNotNull(response.getResult().getIncode());
        assertNotNull(response.getResult().getOutcode());
        assertNotNull(response.getResult().getParliamentary_constituency());
        assertNotNull(response.getResult().getParish());
        assertNotNull(response.getResult().getAdmin_district());
        assertNotNull(response.getResult().getAdmin_ward());
        assertNotNull(response.getResult().getCcg());
        assertNotNull(response.getResult().getNuts());
    }

    public void validatePostCodes(GetPostCodeResponse response) {
        log.info("Attempting to validate the PostCodes");

        assertNotNull(response.getResult().getCodes().getAdmin_district());
        assertNotNull(response.getResult().getCodes().getAdmin_county());
        assertNotNull(response.getResult().getCodes().getAdmin_ward());
        assertNotNull(response.getResult().getCodes().getParliamentary_constituency());
        assertNotNull(response.getResult().getCodes().getParish());
        assertNotNull(response.getResult().getCodes().getCcg());
        assertNotNull(response.getResult().getCodes().getCcg_id());
        assertNotNull(response.getResult().getCodes().getCed());
        assertNotNull(response.getResult().getCodes().getNuts());
    }
}
