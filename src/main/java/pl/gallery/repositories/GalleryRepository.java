package pl.gallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.User;


public interface GalleryRepository extends JpaRepository<Gallery, Long>{
	Gallery findByUser(User user);
}
