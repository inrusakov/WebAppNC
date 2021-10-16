package com.example.model.geoposition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Marker {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    public double latitude;
    public double longtitude;

    public Marker(Integer id, double latitude, double longtitude) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Marker() {

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
