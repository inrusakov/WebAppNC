package com.example.model.geoposition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marker {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    public double latitude;
    public double longtitude;
    private String description;

    public Marker(double latitude, double longtitude, String desc) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.description = desc;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
