package com.mimecast.postcode.common;

import java.util.Random;
import org.apache.log4j.Logger;
import com.mimecast.postcode.AbstarctRequestDefnitions;

public class RandomPostCodeUtil extends AbstarctRequestDefnitions {

    final static Logger log = Logger.getLogger(RandomPostCodeUtil.class);

    /*
     * Below method returns the random postcode at runtime.
     */
    public String getRandomPostalCode() {
        String[] randomCodes=Constants.RANDOM_POSTCODES;
        Random r=new Random(); 
        int randomNumber=r.nextInt(randomCodes.length);
        return randomCodes[randomNumber];
    }
}
