package com.example.model.Event;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Event {

    @Override
    public String toString() {
        return "Event{" +
                "EventID=" + eventID +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", uRL='" + uRL + '\'' +
                ", companyID='" + companyID + '\'' +
                ", date=" + date +
                ", imagePath=" + imagePath +
                ", isPaid=" + isPaid +
                ", price=" + price +
                '}';
    }
    private int eventID;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @Size(min = 10, max = 300, message = "Description should be between 10 and 300 characters")
    private String description;
    @NotEmpty
    private String type;
    private String uRL;
    private int companyID;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future(message = "Date must be in the future")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String imagePath;
    private boolean isPaid = false;
    @PositiveOrZero(message = "Price can't be a negative integer")
    private int price;
    private int admin_id;

    public Event(){}

    public Event(int eventID, String name, String description, String type,
                 String uRL, int companyID, Date date, boolean isPaid, int price, int admin_id){
        this.eventID = eventID;
        this.name = name;
        this.description = description;
        this.uRL = uRL;
        this.companyID = companyID;
        this.date = date;
        this.isPaid = isPaid;
        this.price = price;
        this.admin_id = admin_id;
    }

    public void setAdmin_id(int admin_id){
        this.admin_id = admin_id;
    }

    public int getAdmin_id(){
        return admin_id;
    }

    public void setEventID(int eventID){
        this.eventID = eventID;
    }
    public int getEventID(){
        return eventID;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price != 0 && price >=0) {
            isPaid = true;
            this.price = price;
        }
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


