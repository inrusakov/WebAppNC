package com.example.model.org;

import com.example.model.User;
import com.example.model.geoposition.Address;
import com.example.model.geoposition.Position;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Organisation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int orgID;

    @OneToOne(optional = false, mappedBy = "org")
    private Position position;

    private String name;

    @OneToOne(optional = false, mappedBy = "org")
    private User admin;

    @OneToOne(optional = false, mappedBy = "org")
    private Address address;

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
