package com.example.model.geoposition;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

//@Entity
public class Route {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

//    @OneToMany
//    private List<Marker> markers = new LinkedList<Marker>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public List<Marker> getMarkers() {
//        return markers;
//    }
//
//    public void setMarkers(List<Marker> markers) {
//        this.markers = markers;
//    }
}
