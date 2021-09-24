package com.example;

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
                ", companyName='" + companyName + '\'' +
                ", date=" + date +
                ", images=" + images +
                ", isPaid=" + isPaid +
                ", price=" + price +
                '}';
    }

    private String name;
    private String description;
    private String type;
    private String uRL;
    private String companyName;
    private Date date;
    private List<File> images;
    private boolean isPaid = false;
    private int price = 0;

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

    public boolean isPaid() {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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


