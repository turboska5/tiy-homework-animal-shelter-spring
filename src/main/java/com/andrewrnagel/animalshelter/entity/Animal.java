package com.andrewrnagel.animalshelter.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:30 PM EST.
 */

@Entity
@Table(name = "animal")
public class Animal {
    //object properties
    @Id
    @GeneratedValue
    @Column(name = "animal_ID", nullable = false, unique = true)
    private int animalID;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "breed")
    private String breed = "";

    @Column(name = "description", nullable = false)
    private String description;

    //TODO: separate lookup table for default images?
    @Column(name = "image_url", nullable = false)
    private String picture = "images/X.jpg";

    @ManyToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "animal_ID")
    private Type animalType = new Type();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_ID")
    private Set<Note> animalNotes = new HashSet<>();

    //constructors
    //default constructor
    public Animal() {}

    //constructor with breed(opt); missing animalID and typeID
    //used when creating a new Animal from within AnimalShelter program
    public Animal(String name, String type, String breed, String desc) {
        this.name = name;
        this.breed = breed;
        this.description = desc;
        this.animalType.setType(type);
    }

    //constructor with breed(opt), including animal index
    //used when type can be inferred via lookup
    public Animal(int animalID, String name, String typename, String breed, String description) {
        this.animalID = animalID;
        this.name = name;
        this.breed = breed;
        this.description = description;
        this.animalType.setType(typename);
    }

    //constructor with breed(opt), including animal index and typeID
    //used when reading an existing animal from the animal table
    public Animal(int animalID, String name, String type, String breed, String desc, int typeID) {
        this.animalID = animalID;
        this.name = name;
        this.breed = breed;
        this.description = desc;
        this.animalType.setType(type);
        this.animalType.setTypeID(typeID);
    }

    //constructor without breed
    //used in web version when creating a new animal without a breed supplied by user
    public Animal(Integer animalID, String name, String type, String description, int typeID) {
        this.animalID = animalID;
        this.name = name;
        this.description = description;
        this.animalType.setType(type);
        this.animalType.setTypeID(typeID);
    }

    //methods
    //getters
    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getDescription() {
        return this.description;
    }

    public Type getType() {
        return this.animalType;
    }

    public int getAnimalID() {
        return this.animalID;
    }

//    public int getAnimalTypeID() {
//        return this.animalType.getTypeID();
//    }

    public Set<Note> getAnimalNotes() {
        return this.animalNotes;
    }

    public String getPicture() {
        return this.picture;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        this.animalType = type;
        if(this.animalType.getType() == "Cat") {
            this.setPicture("images/Cat.png");
        } else if(this.animalType.getType() == "Dog") {
            this.setPicture("images/Dog.jpg");
        } else {
        }
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

//    public void setAnimalTypeID(int animalTypeID) {
//        this.animalType.setTypeID(animalTypeID);
//    }

    public void setAnimalNotes(Set<Note> animalNotes) {
        this.animalNotes = animalNotes;
    }

    public void setPicture(String URL) {
        this.picture = URL;
    }

//    //toString
//    public String toString() {
//        String animalNotes = "";
//        String animalStats = format("%-12s %-32s\n%-12s %-32s\n%-12s %-32s\n%-12s %-64s\n",
//                "Name:", this.name,
//                "Type:", this.animalType.getType(),
//                "Breed (opt):", this.breed,
//                "Description:", this.description);
//        if(this.getAnimalNotes().isEmpty()) {
//            animalNotes = String.format("%-12s %-64s\n", "Notes:", "No animal notes found for this animal.");
//        } else {
//            //iterate through and generate string
//            for(int i = 0; i < getAnimalNotes().size(); i++) {
//                if(i == 0){
//                    String noteOne = getAnimalNotes().get(0).getNoteCreationDateAsString().toString() + ": "
//                            + getAnimalNotes().get(0).getNoteContent();
//                    animalNotes = String.format("%-12s %-64s\n", "Notes:", noteOne);
//                } else {
//                    String noteNow = getAnimalNotes().get(i).getNoteCreationDateAsString().toString() + ": "
//                            + getAnimalNotes().get(i).getNoteContent();
//                    String currentNote = String.format("%-12s %-64s\n", "", noteNow);
//                    animalNotes = animalNotes + currentNote;
//                }
//            }
//        }
//        return animalStats+animalNotes;
//    }

    //pictureGeneration
    public void setPic() {
    }

    //disk serialization operations
    //take an animal and return a string serialized by "|" character
    public String serialize() {
        return format("%s|%s|%s|%s\n", name, animalType.getType(), breed, description);
    }

    //take an animal string serialized by "|" character and return animal object
    public static Animal deserialize(String data){
        String[] parsedData = data.split("\\|");
        return new Animal(parsedData[0], parsedData[1], parsedData[2], parsedData[3]);
    }
}