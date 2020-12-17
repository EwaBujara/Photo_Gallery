package pl.gallery.entities;

import java.awt.image.BufferedImage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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
	
	@Transient
	private MultipartFile multipartFile;

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



	public MultipartFile getMultipartFile() {
		return multipartFile;
	}



	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	 
	 
}
