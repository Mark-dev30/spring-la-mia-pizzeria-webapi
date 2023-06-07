package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.service.IngredientService;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredients(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		System.err.println(ingredients);
		return "ingredient";
	}
	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "newingredient";
	}
	
	@PostMapping("/create") 
	public String storePizza(Model model,  @ModelAttribute Ingredient ingredient ) {
		
		ingredientService.save(ingredient);

		return "redirect:/ingredients"; 
	}
	@GetMapping("/update/{id}")
	public String editIngredient(Model model, @PathVariable int id) {
		
		Ingredient ingredient = ingredientService.findById(id).get();
		model.addAttribute("ingredient", ingredient);
		
		return "updateingredient";
	}
	
	@PostMapping("/update/{id}")
	public String updatePizza(Model model, @PathVariable int id,  @ModelAttribute Ingredient ingredient) {
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredients";
	}
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable int id) {
		Ingredient ingredient = ingredientService.findById(id).get();
		
		for (Pizza p : ingredient.getPizze()) {
			p.removeIngredient(ingredient);
			pizzaService.save(p);
			
		}
		
		ingredientService.deleteIngredient(ingredient);
		
		return "redirect:/ingredients";
	}
	
}
