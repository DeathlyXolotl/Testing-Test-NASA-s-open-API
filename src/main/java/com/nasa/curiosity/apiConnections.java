package com.nasa.curiosity;

/* These are the variables used to connect to the endpoint of the api*/ 

public abstract class apiConnections {

    public static final String baseAPIURL = "https://api.nasa.gov/mars-photos/api/v1/rovers";
    public static final String apiKey = "Fk0JRksqezRYKZG93WAknsEkBIfEIeWtCQTIKG7Z";
    public static final String endPointPhotos = "/photos";
    
    public static final String roverNameCuriosity = "/curiosity";
    public static final String roverSpirit = "/spirit";
    public static final String roverOpportunity = "/opportunity";
    public static final String roverPerseverance = "/perseverance";
    
    public static final String solDate = "1000";
    public static final String earthDate =  "2015-05-30";
    
}
