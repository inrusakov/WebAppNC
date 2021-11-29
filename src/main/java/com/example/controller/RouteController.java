package com.example.controller;

import com.example.model.response.Response;
import com.example.model.response.Views;
import com.example.model.geoposition.Route;
import com.example.repos.RouteRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

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

    @RequestMapping(value="/updateRoute",method=RequestMethod.POST)
    public Response editPost(@RequestBody Route route, HttpServletRequest request, Model model){
        Response response = new Response();
        if (route.getId() == null) {
            response.setCode("400");
            response.setMsg("Fields are not correct");
        } else {
            response.setCode("200");
            response.setMsg("Correct");
        }
        routeRepository.save(route);
        model.addAttribute("routeId", route.getId());
        return response;
    }

    @GetMapping(path="/getRoutes")
    CollectionModel<EntityModel<Route>> all() {

        List<EntityModel<Route>> employees = routeRepository.findAll().stream()
                .map(route -> EntityModel.of(route,
                        linkTo(methodOn(RouteController.class).one(route.getId())).withSelfRel(),
                        linkTo(methodOn(RouteController.class).all()).withRel("routes")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(RouteController.class).all()).withSelfRel());
    }

    @GetMapping("/routes/{routeId}")
    EntityModel<Route> one(@PathVariable Integer routeId) {

        Route employee = routeRepository.findById(routeId) //
                .orElseThrow(() -> new IllegalArgumentException("Invalid route Id:" + routeId));

        return EntityModel.of(employee, //
                linkTo(methodOn(RouteController.class).one(routeId)).withSelfRel(),
                linkTo(methodOn(RouteController.class).all()).withRel("routes"));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/routeObserver/{routeId}")
    public String observePost(@PathVariable("routeId") Integer routeId, String submit, Model model){
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        model.addAttribute("route",optionalRoute.get().getMarkers());
        model.addAttribute("id",optionalRoute.get().getId());
        return "routeObserver";
    }

    @GetMapping("/editRoute/{routeId}")
    public String editPost(@PathVariable("routeId") Integer routeId, Model model){
        model.addAttribute("route", (Route)routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid route Id:" + routeId)));
        return "editRoute";
    }
}

