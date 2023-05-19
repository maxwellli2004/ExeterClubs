package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Auth.*;
import com.exeterclubs.web.app.Models.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import java.util.UUID;
import com.google.api.client.util.Lists;

import javax.servlet.http.HttpSession;

/// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {
    // MARK: - Routes
    @GetMapping("/")
    public static String exampleIndex(Model model, HttpSession session) {
        System.out.println(session.getAttribute("email"));
        setUpNavBar(model, session);

        return "index";
    }

    @GetMapping("/club/create") 
    public String createClub(Model model, HttpSession session) {
        model.addAttribute("club", new Club());
        setUpNavBar(model, session);
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
            setUpNavBar(model, session);
            return "clubdetail";
        }

        return "error";
    }
    //MARK: - Club CRUD
    @RequestMapping(value="clubs/create", method=RequestMethod.POST) 
    public String createClub(Model model, @ModelAttribute("club") Club club, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            return "error";
        }

        club.setId(UUID.randomUUID().toString());
            
        String email = (String) session.getAttribute("email");

        club.addHead(email);

        // Save the club to the database
        try {
            ClubController.create(club, email);
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        model.addAttribute("club", club);
       setUpNavBar(model, session);

        return "redirect:/clubDetail/" + club.getId();
    }

    @RequestMapping(value="clubs/delete/{id}", method=RequestMethod.POST)
    public String deleteClub(Model model, @ModelAttribute("club") Club club, BindingResult result, @PathVariable("id") String id, HttpSession session) {
        if (result.hasErrors()) {
            return "error";
        }

        // Delete the club from the database
        try {
            ClubController.delete(id, (String) session.getAttribute("email"));
        }
        catch(Exception e) {
            System.out.println(e);
            return "error";
        }

        setUpNavBar(model, session);
        model.addAttribute("club", new Club());

        return "redirect:/";
    }

    public static void setUpNavBar(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email != null) {
            model.addAttribute("email", email);
        }
        try {
            ClubController.read(email);
            model.addAttribute("clubs", ClubController.nonClubs);
            model.addAttribute("myClubs", ClubController.coheadClubs);
            model.addAttribute("memberClubs", ClubController.memberClubs);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        AuthService.addAuth(model, session);
    }
}

