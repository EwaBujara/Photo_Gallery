package pl.gallery.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.gallery.entities.User;
import pl.gallery.repositories.UserRepository;
import pl.gallery.services.UserService;

@Controller
public class HomeController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	 @GetMapping("/")
	    public String homeSender(HttpSession session) throws IOException {
	    	session.invalidate();
	    	createAdminIfNotFound();     
	        return "index";
	    }
	    
	    @Transactional
	    public void createAdminIfNotFound() {
	    	
	    	String name = "admin";
	    	String password = "password";
	        User user = userRepository.findByName(name);
	        if (user == null) {
	            user = new User();
	            user.setName(name);
	            user.setPassword(password);
	            userService.save(user);
	        }
	    }
}
