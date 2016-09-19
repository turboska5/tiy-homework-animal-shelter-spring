package com.andrewrnagel.animalshelter.entity;

import javax.persistence.*;

/**
 * Created by Andrew Nagel on 9/12/16 at 3:38 PM EST.
 */

@Entity
@Table(name = "type")
public class Type {
    //object properties
    @Id
    @GeneratedValue
    @Column(name = "type_ID")
    private int typeID;

    @Column(name = "type_name")
    private String type;

    //constructors
    //default constructor
    public Type() {}

    //parameterized constructor
    public Type(int typeID, String type) {
        this.setTypeID(typeID);
        this.setType(type);
    }

    //getters
    public int getTypeID() {
        return this.typeID;
    }

    public String getType() {
        return this.type;
    }

    //setters
    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setType(String type) {
        this.type = type;
    }
}