package org.java.demo.controller.dto;

import org.java.demo.pojo.Pizza;

public class PizzaDto {

	Pizza pizza;
	
	public PizzaDto(Pizza pizza) {
		setPizza(pizza);
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
}
