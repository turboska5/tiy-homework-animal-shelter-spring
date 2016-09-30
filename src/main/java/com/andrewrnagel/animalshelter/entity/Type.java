package com.andrewrnagel.animalshelter.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Andrew Nagel on 9/12/16 at 3:38 PM EST.
 */

@Entity
@Table(name = "type")
public class Type {
    //object properties
    @Id
    @GeneratedValue
    @Column(name = "type_ID", nullable = false, unique = true)
    private Integer typeID;

    @NotNull
    @NotEmpty
    @Column(name = "type_name", nullable = false, unique = true)
    private String type;

    //constructors
    //default constructor
    public Type() {}

    //parameterized constructor
    public Type(Integer typeID, String type) {
        this.setTypeID(typeID);
        this.setType(type);
    }

    //getters
    public Integer getTypeID() {
        return this.typeID;
    }

    public String getType() {
        return this.type;
    }

    //setters
    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public void setType(String type) {
        this.type = type;
    }
}