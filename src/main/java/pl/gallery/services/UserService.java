package pl.gallery.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.User;
import pl.gallery.repositories.GalleryRepository;
import pl.gallery.repositories.UserRepository;

@Component
public class UserService {

	@Autowired
    UserRepository userRepository;
	@Autowired
	GalleryRepository galleryRepository;
		
	    public boolean verify(User user) {
	        if(userRepository.findByName(user.getName())!=null){

	            return user.getPassword().equals(userRepository.findByName(user.getName()).getPassword());
	        }
	        else return false;
	    }
	    
	    
	    public void createNewUser(User user) {
	    	Gallery gallery = new Gallery();
	    	galleryRepository.save(gallery);    	
	    	user.setGallery(gallery);    	
	    	userRepository.save(user);
	    }
}
