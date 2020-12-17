package pl.gallery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PG_galleries")
public class Gallery {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@OneToOne(mappedBy = "gallery")
    private User user;
	
	@OneToMany(mappedBy = "gallery", fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	
}
