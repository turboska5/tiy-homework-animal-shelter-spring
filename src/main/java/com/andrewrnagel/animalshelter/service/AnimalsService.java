package com.andrewrnagel.animalshelter.service;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Note;
import com.andrewrnagel.animalshelter.entity.Type;
import com.andrewrnagel.animalshelter.repo.AnimalRepository;
import com.andrewrnagel.animalshelter.repo.NoteRepository;
import com.andrewrnagel.animalshelter.repo.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrew Nagel on 8/15/16 at 12:33 PM EST.
 */

@Service
public class AnimalsService {
    //object properties
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TypeRepository typeRepository;

    //methods
    //return List holding ALL stored animal objects from animal table
    public List<Animal> getAllAnimals() throws SQLException {
        return this.animalRepository.findAll();
    }

    //return List holding ALL stored types from the type table
    public List<Type> getAllTypes() throws SQLException {
        return this.typeRepository.findAll();
    }

    //add animal to the animal table
    public void addAnimal(Animal animal) throws SQLException {
        this.animalRepository.save(animal);
    }

    //remove animal from specified index in animal table
    public void deleteAnimal(Animal animal) throws SQLException {
        this.animalRepository.delete(animal);
    }

    //return animal from specified index in animal table
    public Animal getAnimal(int index) {
        return this.animalRepository.findOne(index);
    }

    //Add a note to the note table
    public  void addNote(Note note) throws SQLException{
        this.noteRepository.save(note);
    }

    //Add a note to the note table
    public  void deleteNote(int noteID) throws SQLException{
        this.noteRepository.delete(noteID);
    }

    //Return list meeting search criteria
    public List<Animal> getAllAnimals(String name, Integer typeID, Integer animalID) throws SQLException {
        return animalRepository.getAllAnimals(name, typeID, animalID);
    }
}