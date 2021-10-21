package com.example.controller;

import com.example.model.response.Response;
import com.example.model.response.Views;
import com.example.model.geoposition.Route;
import com.example.repos.RouteRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @JsonView(Views.Public.class)
    @RequestMapping(value="/sendRoute",method=RequestMethod.POST)
    public Response getUserRoute(@RequestBody ArrayList<String> list, HttpServletRequest request) {
        Response response = new Response();
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
            route.addMarker(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]),coords[2]);
        }
        routeRepository.save(route);
        return response;
    }

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

