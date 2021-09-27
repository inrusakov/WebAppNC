package com.example.model.hobby;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hobby {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String type;
    private String name;

    public Hobby(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
