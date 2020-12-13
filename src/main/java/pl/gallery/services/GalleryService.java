package pl.gallery.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.Photo;
import pl.gallery.repositories.PhotoRepository;

@Component
public class GalleryService {
	
	@Autowired
	PhotoRepository photoRepository;
	
	public void savePhoto(Photo photo) {
		photoRepository.save(photo);
	}

	
	public Photo storePhoto(byte[] file, String name, Gallery gallery) throws Exception{
      
            Photo dbFile = new Photo();
            dbFile.setPhoto(file);
            dbFile.setName(name);
            dbFile.setGallery(gallery);

            return photoRepository.save(dbFile);
        
    }
	
	 public Photo getFile(String fileId) {
	        return photoRepository.findById(fileId);
	    }
}
