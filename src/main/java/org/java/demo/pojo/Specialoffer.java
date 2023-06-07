package org.java.demo.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity  
public class Specialoffer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Title can't be null")
	private String title;
	@FutureOrPresent(message = "enter a future date")
	private LocalDate startDate;
	@FutureOrPresent(message = "enter a future date")
	private LocalDate endDate;
	@NotNull(message = "Discount can't be null")
	private Integer discount;
	
	@ManyToOne
	@JsonBackReference
	private Pizza pizza;
	
	public Specialoffer() {}
	
	public Specialoffer(String title, LocalDate startDate, LocalDate endDate, Integer discount, Pizza pizza) {
		setTitle(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setDiscount(discount);
		setPizza(pizza);
		
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Float getTotalPrice( ) {
		
		int price = pizza.getPrice();
		float discount = price * this.getDiscount() / 100f;
		float total = price - discount;
		return total;
	}

	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle()
		+ " - " + getStartDate() + " - " + getEndDate() + " - " + getDiscount();
	}
	
}
