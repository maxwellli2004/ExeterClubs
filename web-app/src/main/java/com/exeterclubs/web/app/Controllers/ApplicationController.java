package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;
import com.google.cloud.Binding;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import java.util.UUID;

/// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {

    // MARK: - Routes
    @GetMapping("/")
    public String exampleIndex(Model model) {
        model.addAttribute("clubs", ClubController.getAllClubs());

        return "index";
    }

    @GetMapping("/index")
    public String index2() {
        return "index";
    }

    @GetMapping("/example")
	public String example(Model model) {
        System.out.println("Navigating to example page");

        try {
            List<User> users = UserController.read();
            List<Club> clubs = ClubController.read();

            model.addAttribute("users", users);
            model.addAttribute("user", new User());

            model.addAttribute("clubs", clubs);
            model.addAttribute("club", new Club());
        }
        catch(Exception e) {
            return "error";
        }

        return "example";
    }

    @GetMapping("/clubDetail/{id}")
    public String clubDetail(Model model, @ModelAttribute("club") Club club, BindingResult result, @PathVariable("id") String id) {
        if (result.hasErrors()) {
            return "error";
        }

        // Get the club from the database
        try {
            club = ClubController.getClub(id);
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        if (club != null) {
            model.addAttribute("club", club);
            model.addAttribute("clubs", ClubController.getAllClubs());
            return "clubdetail";
        }

        return "error";
    }
    //MARK: - Club CRUD
    @RequestMapping(value="clubs/create", method=RequestMethod.POST) 
    public String createClub(Model model, @ModelAttribute("club") Club club, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return "error";
        }

        // Save the club to the database
        try {
            club.setId(UUID.randomUUID().toString());
            ClubController.create(club);
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        model.addAttribute("club", club);

        return "redirect:/example";
    }

    @RequestMapping(value="clubs/delete/{id}", method=RequestMethod.POST)
    public String deleteClub(Model model, @ModelAttribute("club") Club club, BindingResult result, @PathVariable("id") String id) {
        if (result.hasErrors()) {
            return "error";
        }

        // Delete the club from the database
        try {
            ClubController.delete(id);
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        model.addAttribute("clubs", ClubController.getAllClubs());
        model.addAttribute("club", new Club());

        return "redirect:/example";
    }

    // MARK: - User CRUD
    @RequestMapping(value="/users/create", method=RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        // Save the user to the database
        try {
            user.setId(UUID.randomUUID().toString());
            UserController.create(user);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        model.addAttribute("user", user);

        return "redirect:/example";
    }

    @RequestMapping(value="/users/delete/{id}", method=RequestMethod.POST)
    public String deleteUser(Model model, @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") String id) {
        if (result.hasErrors()) {
            return "error";
        }

        // Delete the user from the database
        try {
            UserController.delete(id);
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        model.addAttribute("users", getUsers());
        model.addAttribute("user", new User());

        return "redirect:/example";
    }

    @RequestMapping(value="/users/update/{id}", method=RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("user") User user, BindingResult result, @PathVariable("id") String id) {
        if (result.hasErrors()) {
            return "error";
        }

        // Delete the user from the database
        try {
            UserController.update(id, user);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        model.addAttribute("user", user);

        return "redirect:/example";
    }

    private List<User> getUsers() {
        try {
            return UserController.read();
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}