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
    public String loadMainPage(Model model) throws SQLException {
        model.addAttribute("typesList", animalsService.getAllTypes());
        model.addAttribute("animalList", animalsService.getAllAnimals());
        return "ListAnimals";
    }

    //TODO
    @RequestMapping(path = "/ListAnimals", method = RequestMethod.POST)
    public String searchAnimals() throws SQLException {
        return "redirect:/ListAnimals";
    }

    @RequestMapping(path = "/EditAnimals", method = RequestMethod.GET)
    public String loadEditPage(Animal animal, Model model) throws SQLException {
        model.addAttribute("typesList", animalsService.getAllTypes());
        return "EditAnimals";
    }

    @RequestMapping(path = "/EditAnimals", method = RequestMethod.POST)
    public String addAnimal(Animal animal) throws SQLException {
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
        Note note = new Note(animalsService.getAnimal(animalID), noteContent);
        animalsService.addNote(note);
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