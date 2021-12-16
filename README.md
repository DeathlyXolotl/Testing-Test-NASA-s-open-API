# Doc
## Example Test Framework to Test-NASA-s-open-API.
### This is a pilot framework to test the NASA's open API using JAVA and Rest Assured

This project contain a Test Framework using tha NASA's API in JAVA with Rest Assured. Is not an extensive project is just a solution of series of challenges to understand
the complexity of using the API with Java-RestAssured technology. The API used in this project is the Mars Rover API that explore the image data gathered by the Nasa's rovers on Mars (Curiosity, Opportunity, Spirit and the new one Perseverance)
to show us how is the world beyond earth. 

Each photo taken by the cameras and rover are stored separately which means access to that data requires differents queries. There are multiple ways to do queries 
against the API. We can access through the name of the rover, earth date, martian sol date, name of the camera, image name, etc. 

## Table of Contents
* [Test Framework Info](#test-framework-info)
* [Endpoint Mars Rover API](#endpoint-mars-rover-api)
* [Java-RestAssured](#java-restassured)
* [Sources](#sources)

## Test Framework Info
The test framework is based on Java-RestAssured technology using the Nasa's Mars Rover API to develop a series of challenges that are focus in recovering photos taken 
by the rovers on the 1000 martian sol which can be extracted from different ways and queries. Then it validates that the photos extracted from the Curiosity Rover on the 
1000 martian sol are the same extracted from the equivalent date on earth date. And last its validates that the amount of pictures taken on that date does not exceeds certaint amount of photos taken by cameras from other rovers.

## Endpoint Mars Rover API
### Base URL
```
"https://api.nasa.gov/mars-photos/api/v1/rovers"
```
### Parameter needed to acess the photos
```
"/photos"
```
### Parameter needed to access the specified rover 
```
"/curiosity"
"/spirit"
"/opportunity"
"/perseverance"
```
### Parameter needed to access the specified photos taken by date 
```
earth date -> "2015-05-30"
martian sol date -> "1000"
```

## Java-RestAssured
### How to make the request to the API to extract the information of recover the first 10 photos from the Rover Curiosity
```
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
        
        private static Response requestResponse(String typeOfDate, String endpointDate, String apiKey, String roverName, String endpointApi){

        return RestAssured.given().queryParam(typeOfDate, endpointDate).queryParam("api_key", apiKey).when().get(roverName + endpointApi).then().contentType(ContentType.JSON).extract().response();
    }
```

## Sources
Work with Nasa's open API <https://api.nasa.gov/index.html#getting-started>



 
