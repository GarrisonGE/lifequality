package com.ucr.edu.lifequality.Model;

public class Location {
    private Double latitude;
    private Double longitude;

    public Location(String data){
        this.latitude = new Double(data.split(",")[0]);
        this.longitude = new Double(data.split(",")[1]);
    }

    public Location(double la, double lg) {
        this.latitude = la;
        this.longitude = lg;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
