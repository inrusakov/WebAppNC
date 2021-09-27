package com.example.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Event {

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", uRL='" + uRL + '\'' +
                ", companyID='" + companyID + '\'' +
                ", date=" + date +
                ", images=" + images +
                ", isPaid=" + isPaid +
                ", price=" + price +
                '}';
    }
    private int eventID;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name shoud be between 2 and 30 characters")
    private String name;
    private String description;
    private String type;
    private String uRL;
    private int companyID;
    @Future(message = "Date must be in the future")
    private Date date;
    private List<File> images;
    private boolean isPaid = false;
    @PositiveOrZero(message = "Price can't be a negative integer")
    private int price = 0;

    public Event(){}

    public Event(int eventID, String name, String description, String type,
                 String uRL, int companyID, Date date, boolean isPaid, int price){
        this.eventID = eventID;
        this.name = name;
        this.description = description;
        this.uRL = uRL;
        this.companyID = companyID;
        this.date = date;
        this.isPaid = isPaid;
        this.price = price;
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

    public void setPrice(int price) throws IllegalArgumentException{
        if(price != 0 && price >=0) {
            isPaid = true;
            this.price = price;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<File> getImages() {
        return images;
    }

    public void setImages(List<File> images) {
        this.images = images;
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


