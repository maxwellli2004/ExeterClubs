package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Auth.AuthService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// Handles all Authentication requests
public class AuthController {
    // Main Sign in / out page
    @GetMapping("/authenticate")
    public static String signUp(Model model, HttpSession session) {
        System.out.println("Navigating to signup page");
        AuthService.addAuth(model, session);

        return "auth";
    }

    // Validates user and redirects to home page
    @RequestMapping(value="/signIn", method=RequestMethod.POST)
    public static String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        System.out.println("Signing in user with email: " + email);

        try {
            String idToken = AuthService.validateUser(email, password);

            session.setAttribute("idToken", idToken);
        }
        catch(Exception e) {
            System.out.println("Error validating user: " + e.getMessage());
        }

        model.addAttribute("clubs", ClubController.getAllClubs());
        return "redirect:/";
    }

    // Creates user and redirects to home page
    @RequestMapping(value="signUp", method=RequestMethod.POST)
    public static String signUp(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        System.out.println("Signing up user with email: " + email);

        try {
            AuthService.createUser(email, password);
            String idToken = AuthService.validateUser(email, password);

            session.setAttribute("idToken", idToken);
        }
        catch(Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }

        model.addAttribute("clubs", ClubController.getAllClubs());
        return "redirect:/";
    }

    // Signs out user and redirects to home page
    @RequestMapping(value="/signOut", method=RequestMethod.POST)
    public static String signOut(Model model, HttpSession session) {
        System.out.println("Signing out user");

        session.removeAttribute("idToken");
        
        AuthService.addAuth(model, session);

        model.addAttribute("clubs", ClubController.getAllClubs());
        return "redirect:/";
    }
}