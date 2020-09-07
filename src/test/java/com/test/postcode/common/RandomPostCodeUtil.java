package com.test.postcode.common;

import java.util.Random;

public class RandomPostCodeUtil {

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
