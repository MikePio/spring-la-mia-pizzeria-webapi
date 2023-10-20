package org.java.app.db.api.dto;

public class PizzaDTO {

	private String name;
	private String description;
	private String photo;
  private int price;
	
	public PizzaDTO() { }

	public PizzaDTO(String name, String description, String photo, int price) {
		
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

  @Override
  public String toString() {
    return "\nPizzaDTO \nname = " + name + "\ndescription = " + description + "\nphoto = " + photo + "\nprice = " + price;
  }

}
