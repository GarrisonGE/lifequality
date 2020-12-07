package com.ucr.edu.lifequality.Model;

public class Location {
    private Double longitude;
    private Double latitude;


    public Location(String data){
        this.longitude = new Double(data.split(",")[0]);
        this.latitude = new Double(data.split(",")[1]);
    }

    public Location(double lg, double la) {
        this.longitude = lg;
        this.latitude = la;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
