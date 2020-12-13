package pl.gallery.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gallery.validator.NewUserValidator;
import pl.gallery.entities.User;
import pl.gallery.repositories.UserRepository;
import pl.gallery.services.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	@Autowired
    private NewUserValidator newUserValidator;
	
	@GetMapping("/users")
	public String showUsersList(Model model) {
		
		model.addAttribute("users", userRepository.findAll());
    	return "users-list";
	}
	
	
	@GetMapping("/adduser")
    public String showSignUpForm(Model model) {
		
		model.addAttribute("userForm", new User());
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("userForm") User userForm, 
    		BindingResult bindingResult,
    		Model model) {
    	
    	newUserValidator.validate(userForm, bindingResult);
    	
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
             
        userService.save(userForm);
        
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }
   
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }
}
