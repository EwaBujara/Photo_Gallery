package pl.gallery.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.Photo;
import pl.gallery.entities.User;
import pl.gallery.repositories.PhotoRepository;
import pl.gallery.repositories.UserRepository;
import pl.gallery.validator.UserLogValidator;

@Controller
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    
    @Autowired
    PhotoRepository photoRepository;
    
    @Autowired
    private UserLogValidator userLogValidator;
    
    @GetMapping("/login")
    public String login(Model model){
    	
        model.addAttribute("userLog", new User());
        return "login";
    }
    
    @PostMapping("login")
    public String login(@ModelAttribute("userLog") User userLog,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpSession session,
                        Model model){

        userLogValidator.validate(userLog, bindingResult);

        if (bindingResult.hasErrors()){
            return "login";
        }

        User currentUser = userRepository.findByName(userLog.getName());
        session.setAttribute("currentUser", currentUser);
        
        if(currentUser.getName().equals("admin")) {
        	model.addAttribute("users", userRepository.findAll());
        	return "users-list";
        }else {
        Gallery gallery = currentUser.getGallery();
        
   		 List<Photo> photos = photoRepository.findAllByGallery(gallery);
        	model.addAttribute("user", currentUser);
        	model.addAttribute("photos", photos);
        	return "gallery";
        }
        
    }
    
}

