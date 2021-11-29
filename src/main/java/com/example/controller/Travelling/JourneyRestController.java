package com.example.controller.Travelling;

import com.example.model.Traveling.Journey.Journey;
import com.example.model.Traveling.Journey.JourneyViews;
import com.example.model.community.Group;
import com.example.service.traveling.JourneyService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel/journey")
public class JourneyRestController {

    @Autowired
    JourneyService journeyService;

    @GetMapping("")
    @JsonView(JourneyViews.list.class)
    List<Journey> all(
            @RequestParam(name = "ttl", required = false) String ttl
    ) {
        return journeyService.getJourney_isParticipant(ttl);
    }

    @GetMapping("{id}")
    @JsonView(JourneyViews.all.class)
    Journey one(@PathVariable("id") Integer id) {
        return journeyService.findById(id);
    }

    @PostMapping("")
    @JsonView(JourneyViews.all.class)
    Journey create(
            @RequestBody Journey journey,
            @RequestBody(required = false) Group group
    ){
        if(group == null){
            return journeyService.create(journey);
        }else
            return journeyService.create(journey, group);
    }

    @PutMapping("{id}")
    @JsonView(JourneyViews.all.class)
    Journey journey_save(
            @RequestBody Journey journey,
            @PathVariable("id") Journey journey_fromDB
    ) {
        BeanUtils.copyProperties(journey, journey_fromDB,"id", "group");
        return journeyService.edit(journey_fromDB);
    }

    @DeleteMapping("{id}")
    void delete(
            @PathVariable(name = "id") Journey journey
    ) {
        journeyService.delete(journey);
    }
}
