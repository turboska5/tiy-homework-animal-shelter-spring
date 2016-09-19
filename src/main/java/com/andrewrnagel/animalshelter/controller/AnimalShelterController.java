package com.andrewrnagel.animalshelter.controller;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Type;
import com.andrewrnagel.animalshelter.entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:16 PM EST.
 */

@Controller
public class AnimalShelterController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String animal(Animal animal, Model model) {
        model.addAttribute("animal", animal);
        return "animal";
    }

//    @RequestMapping(path = "/describeMe", method = RequestMethod.GET)
//    public String describeMeForm(){
//        return "describeMeForm";
//    }
//
//    @RequestMapping(path = "/describeMe", method = RequestMethod.POST)
//    public String describeMeButton(Person person) {
//        return "person";
//    }
}