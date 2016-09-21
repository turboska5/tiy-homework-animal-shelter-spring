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

//    //return list holding stored animal objects by type (sans notes)
//    public List<Animal> findAllAnimalsByType(int typeID) throws SQLException {
//        return this.animalRepository.findByType(typeID);
//    }

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

//    //return all Note objects in an arrayList given animalID
//    public List<Note> getAllAnimalNotesByAnimalID(int animalID) throws SQLException {
//        return this.noteRepository.findByAnimalID(animalID);
//    }

    //    //Return list meeting search criteria
//    public ArrayList<Animal> listAllAnimals(String name, Integer typeID, Integer animalID) throws SQLException {
//        ResultSet resultSet = animalRepository.listAllAnimals(name, typeID, animalID);
//        ArrayList<Animal> animals = new ArrayList<>();
//
//        while (resultSet.next()){
//            Animal animal = new Animal(
//                    resultSet.getInt("animalID"),
//                    resultSet.getString("name"),
//                    resultSet.getString("typename"),
//                    resultSet.getString("breed"),
//                    resultSet.getString("description")
//            );
//
//            // get the notes
//            ResultSet noteResults = noteRepository.listAllNotesByAnimal(animal.getAnimalID());
//
//            while(noteResults.next()){
//                Note note = new Note(
//                        noteResults.getInt("noteID"),
//                        noteResults.getString("text"),
//                        noteResults.getString("date")
//                );
//                animal.getAnimalNotes().add(note);
//            }
//            //This sets picture according to type
//            if(animal.getType().equals("Cat")) {
//                animal.setPicture("/images/Cat.png");
//            } else if(animal.getType().equals("Dog")) {
//                animal.setPicture("/images/Dog.jpg");
//            }
//            animals.add(animal);
//        }
//        return animals;
//    }
//
//
//    //update animal from specified index with updated animal object in animal table
//    public void updateAnimal(Animal animal) throws SQLException {
//        this.animalRepository.updateAnimal(animal);
//    }
//
//    //return arrayList holding all note objects from note table
//    public ArrayList<Note> getAllAnimalNotes() throws SQLException {
//        ArrayList<Note> notes = new ArrayList<>();
//        try {
//            ResultSet results = this.noteRepository.listAllNotes();
//            while (results.next()) {
//                Note note = new Note(
//                        results.getInt("noteID"),
//                        results.getString("text"),
//                        results.getString("date"),
//                        results.getInt("animal")
//                );
//                notes.add(note);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return notes;
//    }
//
//    //add note associated with animal object to the note table
//    public void addNote(Animal animal, Note note) throws SQLException {
//        this.noteRepository.addAnimalNote(animal, note);
//    }
//
//    //add note associated with animalID to note table
//    public void addNote(int animalID, Note note) throws SQLException {
//        this.noteRepository.addAnimalNote(animalID, note);
//    }
//
//    //remove note associated with animal from note table by noteID
//    public void removeNote(int noteID) throws SQLException {
//        this.noteRepository.removeNote(noteID);
//    }
//
//
//    //remove ALL notes associated with an animal from note table
//    public void removeAllAnimalNotesWithID(Animal animal) throws SQLException {
//        try {
//            ResultSet results = this.noteRepository.listAllNotesByAnimal(animal);
//            while(results.next()) {
//                this.noteRepository.removeNote(animal, results.getInt("noteID"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //get all types from the type lookup table as an array of String names
//    public ArrayList<String> getAllTypesAsStrings() throws SQLException {
//        ArrayList<String> types = new ArrayList<>();
//        try {
//            ResultSet results = this.typeRepository.getAllTypes();
//            while (results.next()) {
//                types.add(results.getString("typeName"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return types;
//    }
//
//
//    //get typeID from lookup table, given the name of the type
//    public int getTypeIDByName(String string) throws SQLException {
//        return typeRepository.getTypeIDByName(string);
//    }
//
//    //add a type to the type lookup table using name
//    public void addType(String type) throws SQLException {
//        this.typeRepository.addType(type);
//    }
//
//    //remove a type from lookup table using typeID
//    public void removeType(int typeID) throws SQLException {
//        this.typeRepository.removeType(typeID);
//    }
//
//    //get a Type object from lookup table by typeID
//    public Type getAnimalType(int typeID) throws SQLException {
//        ResultSet result = this.typeRepository.getAnimalType(typeID);
//        if(result.next()){
//            return new Type(
//                    result.getInt("typeID"),
//                    result.getString("typename")
//            );
//        }
//        return null;
//    }
//
//    //replace typename with new string value
//    public void updateType(int index, String type) throws SQLException {
//            this.typeRepository.updateType(index, type);
//    }
//
//    //supporting private functions
//    //cycle through a given resultSet and return an ArrayList of Animals (sans notes)
//    private ArrayList<Animal> populateAnimalResultsAsList(ResultSet results) throws SQLException {
//        ArrayList<Animal> animals = new ArrayList<>();
//        while (results.next()) {
//            Animal animal = new Animal(
//                    results.getInt("animalID"),
//                    results.getString("name"),
//                    results.getString("typename"),
//                    //this.typeRepository.getTypeNameByID(results.getInt("type")),
//                    results.getString("breed"),
//                    results.getString("description"),
//                    //results.getInt("type")
//                    this.typeRepository.getTypeIDByName(results.getString("typename"))
//            );
//            animals.add(animal);
//        }
//        return animals;
//    }
}