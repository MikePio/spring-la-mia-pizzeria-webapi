package org.java.app.db.api.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class PizzaDTO {

	// campi della tabella da inserire: nome descrizione foto prezzo
  @Column(length = 128, nullable = false, unique = true)
  @Length(min = 2, max = 128, message = "The length must be between 2 and 128 characters" )
  private String name;

	private String description;
  
  @NotBlank(message = "The image field must be required")
	private String photo;

  @Column(nullable = false, unique = false)
  @Range(min = 1, max = 1000, message = "The price must be between €1 and €1000" )
  // @DecimalMin(value = "1.00", message = "The price must be at least €1")
  // @DecimalMax(value = "1000.00", message = "The price must be at most €1000")
  private float price;

	public PizzaDTO() { }

	public PizzaDTO(String name, String description, String photo, float price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

  @Override
  public String toString() {
    return "\nPizzaDTO \nname = " + name + "\ndescription = " + description + "\nphoto = " + photo + "\nprice = " + price;
  }

}
