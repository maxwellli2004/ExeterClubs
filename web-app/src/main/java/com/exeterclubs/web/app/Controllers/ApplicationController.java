package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.UUID;

// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {
    @GetMapping("/exampleindex")
    public String exampleIndex() {
        return "page";
    }

    @GetMapping("/example")
	public String example(Model model, @RequestParam(value="name", defaultValue="Example Page") String name) {
        model.addAttribute("title", name);
        return "example";
    }

    @GetMapping("/example/users/new")
    public String newUser(Model model, @RequestParam(value="email", defaultValue = "1234@1234.com") String email) {

        User user = new User("trevor@piltch.com", "1234", UUID.randomUUID(), false);

        try {
            UserController.savePatientDetails(user);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return "user";
    }
}