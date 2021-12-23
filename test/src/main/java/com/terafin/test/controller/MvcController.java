package com.terafin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.terafin.test.entity.User;
import com.terafin.test.repository.UserRepository;

@Controller
public class MvcController {
	
	@Autowired
	UserRepository repos;
	
	@GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Validated User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        repos.save(user);
        return "redirect:/index";
    }
    
    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", repos.findAll());
        return "index";
    }
    
    @GetMapping({"/list", "/"})
	public ModelAndView getAllUsers() {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("users", repos.findAll());
		return mav;
	}
    
}
