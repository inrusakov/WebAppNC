package com.example.controller;

import com.example.model.ajax.AjaxResponse;
import com.example.model.ajax.Views;
import com.example.model.geoposition.GeoRequest;
import com.example.model.geoposition.Marker;
import com.example.model.geoposition.Route;
import com.example.repos.RouteRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class GeopositionController {

    @Autowired
    private RouteRepository routeRepository;

    @JsonView(Views.Public.class)
    @RequestMapping(value="/sendGeo",method=RequestMethod.POST)
    public AjaxResponse getSearchUserProfiles(@RequestBody GeoRequest geoRequest, HttpServletRequest request) {
        AjaxResponse response = new AjaxResponse();
        //String user = geoRequest.getUser();
        String latitude = geoRequest.getLatitude();
        String longitude = geoRequest.getLongitude();
        if (!latitude.equals("") && !longitude.equals("")){
            response.setCode("200");
            response.setMsg("Correct");
        }
        else {
            response.setCode("400");
            response.setMsg("Fields are not correct");
        }

        return response;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value="/sendRoute",method=RequestMethod.POST)
    public AjaxResponse getUserRoute(@RequestBody ArrayList<String> list, HttpServletRequest request) {
        AjaxResponse response = new AjaxResponse();
        if (list.isEmpty()) {
            response.setCode("400");
            response.setMsg("Fields are not correct");
        } else {
            response.setCode("200");
            response.setMsg("Correct");
        }
        Route route = new Route();
        for (String s : list) {
            String[] coords = s.split(",");
            route.addMarker(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
        }
        routeRepository.save(route);
        return response;
    }
}

