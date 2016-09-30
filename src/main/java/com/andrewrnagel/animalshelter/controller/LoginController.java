package com.andrewrnagel.animalshelter.controller;

import com.andrewrnagel.animalshelter.entity.User;
import com.andrewrnagel.animalshelter.misc.PasswordStorage;
import com.andrewrnagel.animalshelter.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by doug on 9/20/16. Modified by Andrew and Jimmy on 9/27/16.
 */

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    // requires user to be logged in
    @RequestMapping(value = "/")
    public String home(HttpSession session, Model model){
        if(session.getAttribute("userId") == null){
            return "redirect:/LoginForm";
        } else {
            // get the user
            Integer userId = (Integer) session.getAttribute("userId");
            User user = userRepository.getOne(userId);
            model.addAttribute("user", user);
            return "redirect:/ListAnimals";
        }
    }

    @RequestMapping(value = "/LoginForm", method = RequestMethod.GET)
    public String loginForm() throws PasswordStorage.CannotPerformOperationException {
        //create a sample user
//        User user = new User("Andrew", "arnagel@gmail.com", "12345");
//        user.setPassword(PasswordStorage.createHash(user.getPassword()));
//        userRepository.save(user);
        return "LoginForm";
    }

    @RequestMapping(value = "/LoginForm", method = RequestMethod.POST)
    public String login(String userName, String password, HttpSession session, Model model) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = userRepository.getByName(userName);
        if(user != null && PasswordStorage.verifyPassword(password, user.getPassword())){
            session.setAttribute("userId", user.getId());
            return "redirect:/ListAnimals";
        } else {
            model.addAttribute("loginFailed", true);
            return "LoginForm";
        }
    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String registerForm(){
//        return "registerForm";
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String register(User user) throws PasswordStorage.CannotPerformOperationException {
//        user.setPassword(PasswordStorage.createHash(user.getPassword()));
//
//        userRepository.save(user);
//
//        return "redirect:/";
//    }

    @RequestMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}