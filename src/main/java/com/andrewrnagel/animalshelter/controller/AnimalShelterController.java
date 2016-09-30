package com.andrewrnagel.animalshelter.controller;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Note;
import com.andrewrnagel.animalshelter.repo.AnimalRepository;
import com.andrewrnagel.animalshelter.service.AnimalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:16 PM EST.
 */

@Controller
public class AnimalShelterController {

    @Autowired
    private AnimalsService animalsService;
//    @Autowired
//    private AnimalRepository animalRepository;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String redirectMainPage() throws SQLException {
//        return "redirect:/ListAnimals";
//    }

    @RequestMapping(path = "/ListAnimals", method = RequestMethod.GET)
    public String searchAnimals(Model model, HttpSession session,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "5") int itemsPerPage,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "0") Integer type,
                                @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {
//        Page<Animal> animalPage = animalRepository.getAllAnimals(new PageRequest(page-1, (int)itemsPerPage));
//        model.addAttribute("back", page-1);
//        model.addAttribute("next", page+1);
//        model.addAttribute("lastPage", animalPage.getTotalPages());
//        model.addAttribute("thisPage", page);
//        model.addAttribute("gamePage", animalPage);

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

        model.addAttribute("lastAnimalName", name);
        model.addAttribute("lastAnimalType", type);
        if(animalID == 0) {
            model.addAttribute("lastAnimalID", null);
        } else {
            model.addAttribute("lastAnimalID", animalID);
        }
        if (name.isEmpty() && type.equals(0) && animalID.equals(0)) {
            model.addAttribute("typesList", animalsService.getAllTypes());
            model.addAttribute("animalList", animalsService.getAllAnimals());
            return "ListAnimals";
        } else {
            model.addAttribute("typesList", animalsService.getAllTypes());
            model.addAttribute("animalList", animalsService.searchAndDisplayAnimals(animalID, type, "%" + name + "%"));
            return "ListAnimals";
        }
    }

    @RequestMapping(path = "/EditAnimals", method = RequestMethod.GET)
    public String loadEditPage(Model model, HttpSession session,
                             @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

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
    public String addAnimal(@Valid Animal animal, BindingResult bindingResult,
                            HttpSession session, Model model) throws SQLException {

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("typesList", animalsService.getAllTypes());
            model.addAttribute("animal", animal);
            return "EditAnimals";
        } else {
            //existing animal
            if(animal.getAnimalID() > 0) {
                List<Note> animalNotes = animalsService.getAnimal(animal.getAnimalID()).getAnimalNotes();
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
        }
        return "redirect:/ListAnimals";
    }

    @RequestMapping(path = "/AnimalNotes", method = RequestMethod.GET)
    public String loadNotePage(Model model, HttpSession session,
                               @RequestParam(defaultValue = "0") Integer animalID) throws SQLException {

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

        model.addAttribute("note", new Note());
        model.addAttribute("animal", animalsService.getAnimal(animalID));
//        model.addAttribute("notesList", animalsService.getAnimalNotesDesc(animalID));
        return "AnimalNotes";
    }

    @RequestMapping(path = "/AnimalNotes", method = RequestMethod.POST)
    public String addNote(@Valid Note note, BindingResult bindingResult, HttpSession session,
                          int animalID, String noteContent, Model model) throws SQLException {

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

        if(bindingResult.hasErrors()) {
            Animal animal = animalsService.getAnimal(animalID);
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("animal", animal);
            return "AnimalNotes";
        } else {
            Animal animal = animalsService.getAnimal(animalID);
            note = new Note(animal, noteContent);
            animal.getAnimalNotes().add(note);
            animalsService.addAnimal(animal);
        }
        return "redirect:/AnimalNotes?animalID=" + animalID;
    }

    @RequestMapping(path = "/DeleteNote", method = RequestMethod.GET)
    public String deleteNote(Model model, HttpSession session,
                             @RequestParam(defaultValue = "0") Integer animalID,
                             @RequestParam(defaultValue = "0") Integer noteID) throws SQLException {

        if(session.getAttribute("userId") == null) {
            return "redirect:/LoginForm";
        }

        model.addAttribute("animal", animalsService.getAnimal(animalID));
        animalsService.deleteNote(noteID);
        return "redirect:/AnimalNotes?animalID=" + animalID;
    }
}