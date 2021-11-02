package com.example.controller;

import com.example.model.response.Response;
import com.example.model.response.Views;
import com.example.model.geoposition.GeoRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GeopositionController {

    @JsonView(Views.Public.class)
    @RequestMapping(value="/sendCoords",method=RequestMethod.POST)
    public Response getSearchUserProfiles(@RequestBody GeoRequest geoRequest, HttpServletRequest request) {
        Response response = new Response();
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
}

