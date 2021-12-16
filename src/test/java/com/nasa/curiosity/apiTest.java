package com.nasa.curiosity;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


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

    @Test(description = "This test validate that the cameras of curiosity did not take more than 10 times the amount of photos taken by the same name camera from others rovers")
    void compareNumberOfPhotosTakenByCamerasOfRovers(){

        for (Map.Entry<String, Integer> curiosityEntry : cameraCounterCuriosity().entrySet()) {
            // This part do the validations of each camera of  curiosity against the other cameras from the other rovers with the same camera 
            if(cameraCounterOpp().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterOpp().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount of photos taken by opportunity");
            }
            if(cameraCounterSpirit().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterSpirit().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount by the photos taken by spirit");
            }
            if(cameraCounterPerseverance().containsKey(curiosityEntry.getKey())) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*cameraCounterPerseverance().get(curiosityEntry.getKey()), "The amount of photos taken by curiosity exceeds 10 times the amount by the photos taken by perseverance");
            }
        }
    }

    @Test(description = "This test validate each camera of the curiosity did not take more than 10 times the amount of photos taken by other cameras frrom others rovers")
    void compareNumberOfPhotosFromCuriosityToOtherRovers() {

        // This part validate each of the cameras of the curiosity against the other ones
        for (Map.Entry<String, Integer> curiosityEntry : cameraCounterCuriosity().entrySet()) {
            //This will always mark an erro because the camera MAST took a huge of amounts of photos that day that is far more that any camera of any rover
            for (Map.Entry<String, Integer> spiritEntry : cameraCounterSpirit().entrySet()) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*spiritEntry.getValue(), "The amount of photos taken in the camera " + curiosityEntry.getKey() + " of the curiosity exceeds 10 times the amount of photos taken by the camera " + spiritEntry.getKey() + " of the spirit");
            }
            for (Map.Entry<String, Integer> oppEntry : cameraCounterOpp().entrySet()) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*oppEntry.getValue(), "The amount of photos taken in the camera " + curiosityEntry.getKey() + " of the curiosity exceeds 10 times the amount of photos taken by the camera " + oppEntry.getKey() + " of the opportunity");
            }
            for (Map.Entry<String, Integer> perseveranceEntry : cameraCounterPerseverance().entrySet()) {
                Assert.assertTrue(curiosityEntry.getValue() <  10*perseveranceEntry.getValue(), "The amount of photos taken in the camera " + curiosityEntry.getKey() + " of the curiosity exceeds 10 times the amount of photos taken by the camera " + perseveranceEntry.getKey() + " of the perseverance");
            }
        }
    }

}
 