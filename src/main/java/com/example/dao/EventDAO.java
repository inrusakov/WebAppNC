package com.example.dao;

import com.example.model.Event.Event;
import com.example.model.User;
import com.example.repos.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class EventDAO {

    private static final String URL = "jdbc:postgresql://ec2-54-195-195-81.eu-west-1.compute.amazonaws.com:5432/d8clr4vuub6l0t";
    private static final String USERNAME = "pwkbqtiryrbaym";
    private static final String PASSWORD = "8c72ae3d9edf7f09bf1cb9c4338f7580e603ee7fd5d81ee5b9de184544ea322f";

    private static Connection connection;

    private List<Event> events = new ArrayList<>();
    private List<Event> userEvents = new ArrayList<>();

    private int userId;
    private String userLogin;
    private String organization;
    private int orgId;

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

    public String getOrganization(){return organization;}

    private void setUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userLogin = auth.getName();
        try{
            Statement statement = connection.createStatement();

            String SQLUserId = "SELECT id" +
                    " FROM app_user" +
                    " WHERE email = '" + userLogin + "';";

            ResultSet resultSet = statement.executeQuery(SQLUserId);
            resultSet.next();

            userId = resultSet.getInt("id");

            String SQLOrg = "SELECT name" +
                    " FROM organizations" +
                    " WHERE admin_id = '" + userId + "';";

            resultSet = statement.executeQuery(SQLOrg);
            resultSet.next();

            organization = resultSet.getString("name");

            String SQLOrgId = "SELECT org_id" +
                    " FROM organizations" +
                    " WHERE admin_id = '" + userId + "';";

            resultSet = statement.executeQuery(SQLOrgId);
            resultSet.next();

            orgId = resultSet.getInt("org_id");

            statement.close();

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public List<Event> index() {

        List<Event> events = new ArrayList<>();

        this.setUserInfo();

        try{
            Statement statement = connection.createStatement();


            String SQL = "SELECT * FROM events";

            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Event event = new Event();

                event.setName(resultSet.getString("name"));
                event.setEventID(resultSet.getInt("event_id"));
                if(resultSet.getObject(9) == null)
                    event.setPrice(0);
                else
                    event.setPrice(resultSet.getInt("price"));
                event.setDate(resultSet.getDate("event_date"));
                event.setuRL(resultSet.getString("url"));
                event.setType(resultSet.getString("type"));
                event.setCompanyID(resultSet.getInt("org_id"));
                event.setDescription(resultSet.getString("description"));
                event.setImagePath(resultSet.getString("image_url"));

                events.add(event);
            }

            statement.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        this.events = events;

        return events;
    }

    public List<Event> edit() {

        List<Event> events = new ArrayList<>();

        this.setUserInfo();

        try{
            Statement statement = connection.createStatement();


            String SQL = "SELECT * FROM events " +
                    "WHERE admin_id = (SELECT id FROM app_user " +
                    "WHERE email = '" + userLogin + "');";

            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Event event = new Event();

                event.setName(resultSet.getString("name"));
                event.setEventID(resultSet.getInt("event_id"));
                if(resultSet.getObject(9) == null)
                    event.setPrice(0);
                else
                    event.setPrice(resultSet.getInt("price"));
                event.setDate(resultSet.getDate("event_date"));
                event.setuRL(resultSet.getString("url"));
                event.setType(resultSet.getString("type"));
                event.setCompanyID(resultSet.getInt("org_id"));
                event.setDescription(resultSet.getString("description"));
                event.setImagePath(resultSet.getString("image_url"));

                events.add(event);
            }

            statement.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        this.userEvents = events;

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

    public Event showUserEvent(int id) {
        return userEvents.stream().filter(event -> event.getEventID() == id).findAny().orElse(null);
    }

    /**
     * Saves a new event in a db.
     * @param event
     */

    public void save(Event event) {

        this.setUserInfo();

        event.setEventID(events.size() + 1);
        event.setCompanyID(orgId); //temp
        events.add(event);
        Date date = event.getDate();
        String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(date);
        try{
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO events (event_id, org_id, name, type, description, event_date, image_url, url, price, admin_id) values (" +
                    event.getEventID()+ ", " +
                    event.getCompanyID()+ ", " + // Correlated with userID in table Users
                    "'" + event.getName() + "', " +
                    "'" + event.getType() + "', " +
                    "'" + event.getDescription() + "', " +
                    "'" + dateStr + "', " +
                    "'" + event.getImagePath() + "', " +
                    "'" + event.getuRL() + "', " +
                    event.getPrice() + ", " +
                    userId + ");";

            statement.execute(SQL);

            statement.close();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }

    public void update(Event updatedEvent) {
        Event eventToBeUpdated = showUserEvent(updatedEvent.getEventID());

        System.out.println(eventToBeUpdated.toString());

        System.out.println(updatedEvent.toString());

        eventToBeUpdated.setName(updatedEvent.getName());
        eventToBeUpdated.setDescription(updatedEvent.getDescription());
        eventToBeUpdated.setType(updatedEvent.getType());
        eventToBeUpdated.setuRL(updatedEvent.getuRL());
        eventToBeUpdated.setDate(updatedEvent.getDate());
        eventToBeUpdated.setPrice(updatedEvent.getPrice());
        eventToBeUpdated.setImagePath(updatedEvent.getImagePath());

        try{
            Statement statement = connection.createStatement();
            String SQL = "UPDATE events " +
                    "SET name = '" + updatedEvent.getName() + "', " +
                    "description = '" + updatedEvent.getDescription() + "', " +
                    "type = '" + updatedEvent.getType() + "', " +
                    "url = '" + updatedEvent.getuRL() + "', " +
                    "event_date = '" + updatedEvent.getDate() + "', " +
                    "price = " + updatedEvent.getPrice() + ", " +
                    "image_url = '" + updatedEvent.getImagePath() + "' " +
                    "WHERE event_id = " + updatedEvent.getEventID();
            statement.execute(SQL);

            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //Need to be done
/*
    public void delete(int id) {
        events.removeIf(p -> p.getCompanyID() == id);
    }

*/

}
