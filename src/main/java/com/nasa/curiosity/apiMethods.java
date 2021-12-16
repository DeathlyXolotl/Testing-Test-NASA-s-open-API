package com.nasa.curiosity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiMethods{

    //variable to star a request to the api
    public static RequestSpecification httpRequest;
    //variable to save the response of the request
    public static Response response;
    //variable to save the response to json string
    public static String jsonResponse;

    public apiMethods () {
        // set base url api
        RestAssured.baseURI = apiConnections.baseAPIURL;
    }

    public static List<String> oneThousandsSolPhotos() {

        // this variable function as cointaner for the image url obtained from the API
        List<String> imgArraySol = new ArrayList<String>();

        /* this part construct the endpoint, type of request and extract the response obtained
        save the response to string*/
        jsonResponse = requestResponse("sol", apiConnections.solDate, apiConnections.apiKey, apiConnections.roverNameCuriosity, apiConnections.endPointPhotos).asString();

        // this part extract the 10 first photos taken by curiosity on the 1000 martian sol date
        for (int i = 0; i < 10; i++) {
            imgArraySol.add(JsonPath.with(jsonResponse).get("photos["+i+"].img_src")); 
        }

        return imgArraySol;
    }

    public static List<String> earthDatePhotos() {

        // this variable function as cointaner for the image url obtained from the API
        List<String> imgArrayEarth = new ArrayList<String>();

        /*this part construct the endpoint, type of request and extract the response obtained
        transform the response to string*/
        jsonResponse = requestResponse("earth_date", apiConnections.earthDate, apiConnections.apiKey, apiConnections.roverNameCuriosity, apiConnections.endPointPhotos).asString();

        // this part extract the 10 first photos taken by curiosity on the equivalent of 1000 martian sol to earth date
        for (int i = 0; i < 10; i++) {
            imgArrayEarth.add(JsonPath.with(jsonResponse).get("photos["+i+"].img_src")); 
        }

        return imgArrayEarth;
    }

    public static TreeMap<String,Integer> cameraCounterCuriosity() {

        List<String> cameraCuriosity = new ArrayList<String>();

        jsonResponse = requestResponse("sol", apiConnections.solDate, apiConnections.apiKey, apiConnections.roverNameCuriosity, apiConnections.endPointPhotos).asString();

        // this part extract the name of all the camera that is specified in each of the photo taken by curiosity on the 1000 martian sol 
        cameraCuriosity = JsonPath.with(jsonResponse).get("photos.camera.name");
        
        return countPhotosFromCameras(cameraCuriosity);
    }

    public static TreeMap<String,Integer> cameraCounterSpirit() {

        List<String> cameraSpirit = new ArrayList<String>();

        jsonResponse = requestResponse("sol", apiConnections.solDate, apiConnections.apiKey, apiConnections.roverSpirit, apiConnections.endPointPhotos).asString();

        // this part extract the name of all the camera that is specified in each of the photo taken by Spirit on the 1000 martian sol 
        cameraSpirit = JsonPath.with(jsonResponse).get("photos.camera.name");
        
        return countPhotosFromCameras(cameraSpirit);
    }

    public static TreeMap<String,Integer> cameraCounterOpp() {

        List<String> cameraOpp = new ArrayList<String>();

        jsonResponse = requestResponse("sol", apiConnections.solDate, apiConnections.apiKey, apiConnections.roverOpportunity, apiConnections.endPointPhotos).asString();

        // this part extract the name of all the camera that is specified in each of the photo taken by Opportunity on the 1000 martian sol 
        cameraOpp = JsonPath.with(jsonResponse).get("photos.camera.name");

        return countPhotosFromCameras(cameraOpp);
    }

    public static TreeMap<String,Integer> cameraCounterPerseverance() {

        List<String> cameraPerseverance = new ArrayList<String>();

        jsonResponse = requestResponse("sol", apiConnections.solDate, apiConnections.apiKey, apiConnections.roverPerseverance, apiConnections.endPointPhotos).asString();

        // this part extract the name of all the camera that is specified in each of the photo taken by Perseverance on the 1000 martian sol 
        cameraPerseverance = JsonPath.with(jsonResponse).get("photos.camera.name");
        
        return countPhotosFromCameras(cameraPerseverance);
    }

    private static TreeMap<String,Integer> countPhotosFromCameras(List<String> listCamera) {

        // This tree map obtain the name of the camera and the amount of pictures that took that camera
        TreeMap<String, Integer> cameraCounterMap = new TreeMap<String, Integer>();
        for (String cameraName : listCamera) {
            Integer a = cameraCounterMap.get(cameraName);
            cameraCounterMap.put(cameraName, (a == null) ? 1 : a + 1 );
        }

        return cameraCounterMap;

    }

    private static Response requestResponse(String typeOfDate, String endpointDate, String apiKey, String roverName, String endpointApi){

        // This uses rest assured to construct the request to the api and extract the response in json format
        return RestAssured.given().queryParam(typeOfDate, endpointDate).queryParam("api_key", apiKey).when().get(roverName + endpointApi).then().contentType(ContentType.JSON).extract().response();
    }

}
