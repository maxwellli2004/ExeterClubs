package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.UUID;

/// Creates routes (web url handlers) with different inputs and models to pass data to view
@Controller
public class ApplicationController {
    @GetMapping("/exampleindex")
    public String exampleIndex() {
        return "page";
    }

    @GetMapping("/example")
	public String example(Model model) {
        model.addAttribute("title", name);
        return "example";
    }
}