package pl.gallery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PG_photos")
public class Photo {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	//@NotNull(message = "Upload file")
	
	@Lob
    @Column(columnDefinition="LONGBLOB", nullable =false)
	private byte[] photo;
	
	@NotNull(message = "Name is mandatory")
	String name;
	 
	@ManyToOne
	private Gallery gallery;
	

	public Photo() {};
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	 
	 
}
