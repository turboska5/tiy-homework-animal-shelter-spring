package com.andrewrnagel.animalshelter.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Created by Andrew Nagel on 8/30/16 at 2:41 PM EST.
 */

@Entity
@Table(name = "note")
public class Note {
    //object properties
    @Id
    @GeneratedValue
    @Column(name = "note_ID")
    private int noteID;

//    @Column(name = "animal_ID", nullable = false, unique = true)
//    private int animalID;

    @NotNull
    @NotEmpty
    @Column(name = "note_content", nullable = false)
    private String noteContent;

    @Column(name = "created_on", nullable = false)
    private LocalDate noteCreationDate;

    @Transient
    private Animal animal;

    //constructors
    //default constructor
    public Note() {}

    //constructor for note (date set automatically to NOW, based on location)
    //used when creating a new Note from within AnimalShelter program
    public Note(String noteContent) {
        this.noteContent = noteContent;
        this.noteCreationDate = LocalDate.now();
    }

    //constructor for note (date and noteID read from table)
    //used when reading an existing Note from the note table
    public Note(int noteID, String noteContent, String date) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.noteCreationDate = LocalDate.parse(date, formatter);
    }

    //constructor for note (date, noteID, and animalID all read from table)
    //used when retrieving ALL existing Notes from the note table
    public Note(int noteID, String noteContent, String date, int animalID) {
        this.noteID = noteID;
        this.noteContent = noteContent;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.noteCreationDate = LocalDate.parse(date, formatter);
//        this.animalID = animalID;
    }

    //constructor for note (animalID, content, with date set automatically to NOW, based on location)
    //used by note web servlet
    public Note(Animal animal, String noteContent) {
        this.noteContent = noteContent;
        this.animal = animal;
        this.noteCreationDate = LocalDate.now();
    }

    //methods
    //getters
    //SQL format yyyy-mm-dd
    public LocalDate getNoteCreationDateAsLocalDate() {
        return this.noteCreationDate;
    }

    public String getNoteCreationDateAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        return this.noteCreationDate.format(formatter);
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public int getNoteID() {
        return this.noteID;
    }

    //setters
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}