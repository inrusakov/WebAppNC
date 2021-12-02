package com.example.controller.Travelling;

import com.example.model.Traveling.Journey.Journey;
import com.example.model.Traveling.Journey.JourneyViews;
import com.example.service.traveling.JourneyService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journey")
public class JourneyRestController {

    @Autowired
    JourneyService journeyService;

    @GetMapping("")
    @JsonView(JourneyViews.all.class)
    ResponseEntity<List<Journey>> all(
            @RequestParam(name = "q", required = false) String title
    ) {
        try {
            List<Journey> journeyList = journeyService.getJourney_isParticipant(title);
            if (journeyList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(journeyList, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    @JsonView(JourneyViews.all.class)
    ResponseEntity<Journey> one(@PathVariable("id") Integer id) {
        return journeyService.findById(id);
    }

    @PostMapping("")
    @JsonView(JourneyViews.all.class)
    ResponseEntity<Journey> create(
            @RequestBody Journey journey
    ){
        try {
            return journeyService.create(journey);
        }catch ( Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    @JsonView(JourneyViews.all.class)
    ResponseEntity<Journey> journey_update(
            @RequestBody Journey journey,
            @PathVariable("id") Journey journey_fromDB
    ) {
        if(journey_fromDB == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            BeanUtils.copyProperties(journey, journey_fromDB,"id", "group", "creation_time");
            return journeyService.edit(journey_fromDB);
        }
    }

    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> delete(
            @PathVariable(name = "id") Journey journey
    ) {
        try {
            return journeyService.delete(journey);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
