package com.example.dao;

import com.example.model.Event;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventDAO {
    private static int EVENTS_COUNT;
    private List<Event> events;

    {
        events = new ArrayList<>();

        events.add(new Event(++EVENTS_COUNT,"FirstEvent", "FirstDesc", "Type1", "1.com",
                1, new Date(10L), true, 1000));
        events.add(new Event(++EVENTS_COUNT, "SecondEvent", "SecondDesc", "Type2", "2.com",
                2, new Date(20L), true, 2000));
        events.add(new Event(++EVENTS_COUNT, "ThirdEvent", "ThirdDesc", "Type1", "3.com",
                2, new Date(30L), true, 3000));
        events.add(new Event(++EVENTS_COUNT,"ForthEvent", "ForthDesc", "Type4", "4.com",
                1, new Date(40L), true, 4000));
    }

    public List<Event> index() {
        return events;
    }

    public Event show(int id) {
        return events.stream().filter(event -> event.getEventID() == id).findAny().orElse(null);
    }

    public void save(Event event) {
        event.setEventID(++EVENTS_COUNT);
        events.add(event);
    }

    public void update(int id, Event updatedEvent) {
        Event eventToBeUpdated = show(id);

        eventToBeUpdated.setName(updatedEvent.getName());
        eventToBeUpdated.setDescription(updatedEvent.getDescription());
        eventToBeUpdated.setType(updatedEvent.getType());
        eventToBeUpdated.setuRL(updatedEvent.getuRL());
        eventToBeUpdated.setCompanyID(updatedEvent.getCompanyID());
        eventToBeUpdated.setDate(updatedEvent.getDate());
        eventToBeUpdated.setPaid(updatedEvent.getIsPaid());
        eventToBeUpdated.setPrice(updatedEvent.getPrice());

    }

    public void delete(int id) {
        events.removeIf(p -> p.getCompanyID() == id);
    }
}
