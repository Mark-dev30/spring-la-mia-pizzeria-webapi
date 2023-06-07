package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.pojo.Specialoffer;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/")
	public String getHome(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		
		return "index";
		
	}
	
	@PostMapping("/pizza/by/name")
	public String getPizzaByTitle(Model model,@RequestParam(required = false) String name) {
		List<Pizza> pizze = pizzaService.findByNameContaining(name);
//		Passiamo a index.html la lista delle pizze filtrate
		model.addAttribute("pizze", pizze);
//		Passiamo a index.html anche il parametro (Nome inserito nel form) passato dal form al back-end
		model.addAttribute("name", name);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String printPizzaWithId(Model model, @PathVariable("id") int id) {
		
		
//		Pizza pizza = getPizzaById(id);
		Optional<Pizza> pizzaid = pizzaService.findByIdWithSpecialoffer(id);
		Pizza pizza = pizzaid.get();
		if(pizza != null) {
			model.addAttribute("pizza", pizza);
		}

		return "pizza";

	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		
		return "newpizza";
	}
	
	//Questa funzione viene chiamata quando viene inviata una richiesta POST all'URL "/pizza/create"
	
	@PostMapping("/pizza/create") 
	public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) { //L'oggetto 'Pizza' viene popolato automaticamente con i dati inviati dal client tramite la richiesta POST grazie all'annotazione @ModelAttribute
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("pizza",pizza);
			model.addAttribute("errors", bindingResult);
			
			return "newpizza";
			
		}
		
		pizzaService.save(pizza);
		System.err.println(pizza);
		System.err.println(pizza.getIngredients());
		
//		Ritorna la rotta '/' (in questo caso la homepage)
		
		return "redirect:/"; 
	}
	
	@GetMapping("/pizza/update/{id}")
	public String editPizza(Model model, @PathVariable int id) {
		List<Ingredient> ingredients = ingredientService.findAll();
		Optional<Pizza> pizzaid = pizzaService.findById(id);
		Pizza pizza = pizzaid.get();
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "updatepizza";
	}
	
	@PostMapping("/pizza/update/{id}")
	public String updatePizza(Model model, @PathVariable int id, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("pizza",pizza);
			model.addAttribute("errors", bindingResult);
			
			return "updatepizza";
			
		}
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Optional<Pizza> pizzaid = pizzaService.findById(id);
		Pizza pizza = pizzaid.get();
		pizzaService.deletePizza(pizza);
		
		return "redirect:/";
	}

	
	private Pizza getPizzaById(int id) {
		
		Pizza singlePizza = null;
		for (Pizza pizza: getPizze()) {
			if(pizza.getId() == id) {
				singlePizza = pizza;
			}
		}
		return singlePizza;
	}
	
	private List<Pizza> getPizze() {
		List<Pizza> pizze = pizzaService.findAll();
		return pizze;
	}

}
