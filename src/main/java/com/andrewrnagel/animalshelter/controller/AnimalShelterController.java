package com.andrewrnagel.animalshelter.controller;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Type;
import com.andrewrnagel.animalshelter.entity.Note;

import com.andrewrnagel.animalshelter.repo.AnimalRepository;
import com.andrewrnagel.animalshelter.repo.NoteRepository;
import com.andrewrnagel.animalshelter.repo.TypeRepository;
import com.andrewrnagel.animalshelter.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:16 PM EST.
 */

@Controller
public class AnimalShelterController {

    @Autowired
    private AnimalsService animalsService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String redirectMainPage() throws SQLException {
        return "redirect:/ListAnimals";
    }

    @RequestMapping(path = "/ListAnimals", method = RequestMethod.GET)
    public String searchAnimals(Model model,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "0") Integer type,
                                @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {
        if (name.isEmpty() && type.equals(0) && animalID.equals(0)) {
            model.addAttribute("typesList", animalsService.getAllTypes());
            model.addAttribute("animalList", animalsService.getAllAnimals());
            return "ListAnimals";
        } else {
            model.addAttribute("typesList", animalsService.getAllTypes());
            model.addAttribute("animalList", animalsService.searchAndDisplayAnimals(animalID, type, name));
            model.addAttribute("animalList", animalsService.searchAndDisplayAnimals(animalID, type, name));
            return "ListAnimals";
        }
    }

    @RequestMapping(path = "/EditAnimals", method = RequestMethod.GET)
    public String loadEditPage(Model model,
                             @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {
        model.addAttribute("typesList", animalsService.getAllTypes());
        if(animalID > 0) {
            model.addAttribute("animal", animalsService.getAnimal(animalID));
        } else {
            Animal animal = new Animal("", "", "", "");
            model.addAttribute("animal", animal);
        }
        return "EditAnimals";
    }

    @RequestMapping(path = "/EditAnimals", method = RequestMethod.POST)
    public String addAnimal(Animal animal) throws SQLException {
        //existing animal
        if(animal.getAnimalID() > 0) {
            Set<Note> animalNotes = animalsService.getAnimal(animal.getAnimalID()).getAnimalNotes();
            animal.setAnimalNotes(animalNotes);
        }
        //generic picture check
        if(animal.getPicture().equals("images/X.jpg")) {
            if(animal.getType().getType().equals("Cat")) {
                animal.setPicture("images/Cat.png");
            } else if(animal.getType().getType().equals("Dog")) {
                animal.setPicture("images/Dog.jpg");
            }
        }
        animalsService.addAnimal(animal);
        return "redirect:/ListAnimals";
    }

    @RequestMapping(path = "/AnimalNotes", method = RequestMethod.GET)
    public String loadNotePage(Model model,
                               @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {
        model.addAttribute("animal", animalsService.getAnimal(animalID));
        return "AnimalNotes";
    }

    @RequestMapping(path = "/AnimalNotes", method = RequestMethod.POST)
    public String addNote(int animalID, String noteContent) throws SQLException {
        Animal animal = animalsService.getAnimal(animalID);
        Note note = new Note(animal, noteContent);
        animal.getAnimalNotes().add(note);
        animalsService.addAnimal(animal);
        return "redirect:/AnimalNotes?animalID=" + animalID;
    }

    @RequestMapping(path = "/DeleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model,
                             @RequestParam(defaultValue = "0") Integer animalID,
                             @RequestParam(defaultValue = "0") Integer noteID) throws SQLException {
        model.addAttribute("animal", animalsService.getAnimal(animalID));
        animalsService.deleteNote(noteID);
        return "redirect:/AnimalNotes?animalID=" + animalID;
    }
}