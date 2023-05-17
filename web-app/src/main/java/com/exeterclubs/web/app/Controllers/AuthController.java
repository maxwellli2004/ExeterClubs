package com.exeterclubs.web.app.Controllers;
import com.exeterclubs.web.app.Models.User;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @RequestMapping(value="/signUp/email={email}password={password}", method=RequestMethod.POST)
    public static String signUp(@PathVariable String email, @PathVariable String password, Model model) {
        try {
            AuthService.createUser(email, password);
        }
        catch(FirebaseAuthException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    model.addAttribute("clubs", ClubController.getAllClubs());
        return "redirect:/";
    }

  
}