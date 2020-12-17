package pl.gallery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.gallery.entities.Gallery;
import pl.gallery.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{
	List<Photo> findAllByGallery(Gallery gallery);
	Photo findById(String Id);
}
