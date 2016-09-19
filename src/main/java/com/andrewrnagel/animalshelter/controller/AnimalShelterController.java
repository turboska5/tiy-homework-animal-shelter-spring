package com.andrewrnagel.animalshelter.controller;

import com.andrewrnagel.animalshelter.entity.Animal;
import com.andrewrnagel.animalshelter.entity.Type;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

/**
 * Created by Andrew Nagel and Jimmy Bush on 9/19/16 at 2:16 PM EST.
 */

@Controller
public class AnimalShelterController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getAnimal(Animal animal, Model model) {
        model.addAttribute("animal", animal);
        return "/WEB-INF/ListAnimals.jsp";
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