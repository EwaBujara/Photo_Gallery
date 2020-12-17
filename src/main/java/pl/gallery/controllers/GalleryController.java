package pl.gallery.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.Photo;
import pl.gallery.entities.User;
import pl.gallery.repositories.GalleryRepository;
import pl.gallery.repositories.PhotoRepository;
import pl.gallery.repositories.UserRepository;


@Controller
public class GalleryController {
	
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	GalleryRepository galleryRepository;
	@Autowired
	PhotoRepository photoRepository;
	
	@GetMapping("gallery/{id}")
	String showUserGallery(@PathVariable("id")long id, Model model) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		Gallery gallery = user.getGallery();
		List<Photo> photos = photoRepository.findAllByGallery(gallery);
		
		Consumer<Photo> convertByteToImage = (Photo photo) -> {
			MultipartFile multipartFile = new MockMultipartFile(photo.getName(), photo.getPhoto());
         photo.setMultipartFile(multipartFile);
        };
		
        photos.forEach(convertByteToImage);
        
		model.addAttribute("user", user);
     	model.addAttribute("photos", photos);
		return "gallery";
	}
	 
	 @GetMapping("/addphoto/{id}")
	 public String galleryUpdate(@PathVariable("id")long id, Model model, String name, MultipartFile file ) {
		 User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		 model.addAttribute("user", user);	 
		 return "add-photo";
	 }
	 
	 @PostMapping("/addphoto/{id}")
	 public String uploadPhoto( @RequestParam("name") String name,
			 @RequestParam("image") MultipartFile multipartFile,
			 @PathVariable("id")long id,
			 Model model) throws IOException {
		 
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		Gallery gallery = user.getGallery();
		

		byte [] by =  multipartFile.getBytes();
	     
		Photo photo = new Photo();
		photo.setName(name);
	    photo.setPhoto(by);
		photo.setGallery(gallery);
		photoRepository.save(photo);
		
		List<Photo> photos = photoRepository.findAllByGallery(gallery);
		 model.addAttribute("user", user);
    	model.addAttribute("photos", photos);
		return "gallery";
	 }
   

		 
}

