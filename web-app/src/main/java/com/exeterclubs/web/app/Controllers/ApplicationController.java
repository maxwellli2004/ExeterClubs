package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Auth.*;
import com.exeterclubs.web.app.Models.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

/// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {
    // MARK: - Routes
    @GetMapping("/")
    public static String exampleIndex(Model model, HttpSession session) {
        AuthService.addAuth(model, session);
        model.addAttribute("clubs", ClubController.getAllClubs());

        return "index";
    }

    @GetMapping("/example")
	public String example(Model model) {
        System.out.println("Navigating to example page");

        try {
            List<Club> clubs = ClubController.read();

            model.addAttribute("clubs", clubs);
            model.addAttribute("club", new Club());
        }
        catch(Exception e) {
            return "error";
        }

        return "example";
    }

    @GetMapping("/club/create") 
    public String createClub(Model model, HttpSession session) {
        model.addAttribute("club", new Club());
        AuthService.addAuth(model, session);
        return "createclub";
    }

    // MARK: - Club Stuff
    @GetMapping("/clubDetail/{id}")
    public String clubDetail(Model model, @ModelAttribute("club") Club club, BindingResult result, @PathVariable("id") String id, HttpSession session) {
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
            AuthService.addAuth(model, session);
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
}

