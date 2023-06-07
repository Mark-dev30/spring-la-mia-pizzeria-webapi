package org.java.demo.pojo;


import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name can't be null")
	private String name;
	@NotBlank(message = "Description can't be null")
	private String description;
	
	private String photo;
	@NotNull(message = "Price can't be null")
	@Min(value = 3, message = "Price can't be less than 3 euro")
	private Integer price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Specialoffer> specialoffer;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {}
	public Pizza(String name, String description, String photo, Integer price, Ingredient...ingredients) {
		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
		setNewIngredients(ingredients);
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	public List<Specialoffer> getSpecialoffer() {
		return specialoffer;
	}
	public void setSpecialoffer(List<Specialoffer> specialoffer) {
		this.specialoffer = specialoffer;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setNewIngredients(Ingredient[] ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	public void addIngredient(Ingredient ingredient) {
		getIngredients().add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		
		getIngredients().remove(ingredient);
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "-" + getName() + "]"  
		+ "/n" + getDescription() + getPrice();
	}
	
}