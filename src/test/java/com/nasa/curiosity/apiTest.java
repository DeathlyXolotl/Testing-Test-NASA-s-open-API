package com.nasa.curiosity;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


public class apiTest extends apiMethods{

    @Test(description = "This test compare the 10 photos extracted from the 1000 sol and the other 10 photos extracted from the Earth Day equals to 1000 sol")
    void comparePhotosTakenByCuriosity(){

        //This part extract the 10 first photos taken by curiosity on the 1000 sol 
        for (String img : oneThousandsSolPhotos()) {
            System.out.println(img);
        }

        //This part extract the 10 first photos taken by curiosity on the equivalent of the 1000 sol date to earth date
        for (String img : earthDatePhotos()) {
            System.out.println(img);
        }

        //This compare the 10 first photos taken by curiosiity on the 1000 martian sol against the first 10 photos taken on the equivalent of 1000 martian sol to earth date
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(oneThousandsSolPhotos().get(i), earthDatePhotos().get(i), "The photos are not the same");
        }
    }

    @Test(description = "This test validate that the cameras of curiosity did not take more than 10 times that others camera on the 1000 Mars sol")
    void compareNumberOfPhotosTakenByCamerasOfRovers(){

        // This part do the validations of each camera of  curiosity against the other cameras from the other rovers with the same camera 
        for (Map.Entry<String, Integer> curiosityEntry : cameraCounterCuriosity().entrySet()) {
            if(cameraCounterOpp().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterOpp().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount by the photos taken by opportunity");
            }
            if(cameraCounterSpirit().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterSpirit().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount by the photos taken by spirit");
            }
            if(cameraCounterPerseverance().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterPerseverance().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount by the photos taken by perseverance");
            }
        }
    }

}
 