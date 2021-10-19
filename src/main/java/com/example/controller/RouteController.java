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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping(path="/getRoutes")
    public @ResponseBody Iterable<Route> getAllUsers() {
        // This returns a JSON or XML with the users
        return routeRepository.findAll();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/routeObserver/{routeId}")
    public String observePost(@PathVariable("routeId") Integer routeId, String submit, Model model){
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        //Route route = ;
//        Map<Integer, Marker> routeMap = new HashMap<>();
//        for (int i = 0; i < route.getMarkers().size(); i++){
//            routeMap.put(i,route.getMarkers().get(i));
//        }
        model.addAttribute("route",optionalRoute.get().getMarkers());
        return "routeObserver";
    }
}

