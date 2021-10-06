package com.example.dao;

import com.example.model.Event.Event;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventDAO {
    private static int EVENTS_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/db_example";
    private static final String USERNAME = "springuser";
    private static final String PASSWORD = "ThePassword";

    private static Connection connection;

    private List<Event> events = new ArrayList<>();

    /**
     * Initializing the connection to database using JDBC.
     */

    static {
        try{
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    /**
     * Get all events from database.
     * @return List of Events
     */

    public List<Event> index() {

        List<Event> events = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Events";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Event event = new Event();

                event.setName(resultSet.getString("Name"));
                event.setEventID(resultSet.getInt("EventID"));
                if(resultSet.getObject(9) == null)
                    event.setPrice(0);
                else
                    event.setPrice(resultSet.getInt("Price"));
                event.setDate(resultSet.getDate("Date"));
                event.setuRL(resultSet.getString("Url"));
                event.setType(resultSet.getString("Type"));
                event.setCompanyID(resultSet.getInt("CompanyID"));
                event.setDescription(resultSet.getString("Description"));
                event.setImagePath(resultSet.getString("Image"));

                events.add(event);
            }

            statement.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        this.events = events;

        return events;
    }

    /**
     * Shows event from database by its id.
     * @param id
     * @return
     */

    public Event show(int id) {
        return events.stream().filter(event -> event.getEventID() == id).findAny().orElse(null);
    }

    /**
     * Saves a new event in a db.
     * @param event
     */

    public void save(Event event) {
        event.setEventID(events.size() + 1);
        event.setCompanyID(2); //temp
        events.add(event);
        Date date = event.getDate();
        String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(date);
        try{
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO events values (" +
                    event.getEventID()+ ", " +
                    event.getCompanyID()+ ", " + // Correlated with userID in table Users
                    "'" + event.getName() + "', " +
                    "'" + event.getType() + "', " +
                    "'" + event.getDescription() + "', " +
                    "'" + dateStr + "', " +
                    "'" + event.getImagePath() + "', " +
                    "'" + event.getuRL() + "', " +
                    event.getPrice() + ");";

            statement.execute(SQL);

            statement.close();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }
    //Need to be done
/*
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

*/

}
