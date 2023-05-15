package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import java.util.UUID;

/// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {

    @GetMapping("/")
    public String exampleIndex() {
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

            model.addAttribute("users", users);
            model.addAttribute("user", new User());
        }
        catch(Exception e) {
            return "error";
        }

        return "example";
    }

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

        return "example";
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

    @RequestMapping(value="/users/update", method=RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }

        // Delete the user from the database
        try {
            UserController.update(user);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        model.addAttribute("user", user);

        return "example";
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