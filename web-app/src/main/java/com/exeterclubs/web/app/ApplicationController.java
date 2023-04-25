package com.exeterclubs.web.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class ApplicationController {
    @GetMapping("/")
	public String example(Model model, @RequestParam(value="name", defaultValue="World") String name) {
        model.addAttribute("title", name);
        return "example";
    }
}