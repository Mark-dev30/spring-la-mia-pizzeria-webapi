package org.java.demo.controller;



import java.util.List;

import org.java.demo.pojo.Pizza;
import org.java.demo.pojo.Specialoffer;
import org.java.demo.service.PizzaService;
import org.java.demo.service.SpecialofferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;



@Controller
public class SpecialofferController {
	
	@Autowired
	private SpecialofferService offerService;
	
	@Autowired
	private PizzaService pizzaService;
	
	
	@GetMapping("/offer/create")
	public String createOffer(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("offer", new Specialoffer());
		model.addAttribute("pizze", pizze);
		
		return "newoffer";
	}
	@PostMapping("/offer/create")
	public String storeOffer(Model model, @Valid @ModelAttribute Specialoffer specialoffer, BindingResult bindingResult) {
		List<Pizza> pizze = pizzaService.findAll();
		if(bindingResult.hasErrors()) {
			model.addAttribute("offer",specialoffer);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("pizze", pizze);
			
			return "newoffer";
			
		}
		
		offerService.save(specialoffer);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/offer/update/{id}")
	public String editOffer(Model model, @PathVariable int id) {
		List<Pizza> pizze = pizzaService.findAll();
		Specialoffer specialoffer = offerService.findById(id).get();
		model.addAttribute("offer", specialoffer);
		model.addAttribute("pizze", pizze);
		
		return "updateoffer";
	}
	@PostMapping("/offer/update/{id}")
	public String updateOffer(Model model, @Valid @ModelAttribute Specialoffer specialoffer, BindingResult bindingResult) {
		List<Pizza> pizze = pizzaService.findAll();
		if(bindingResult.hasErrors()) {
			model.addAttribute("offer",specialoffer);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("pizze", pizze);
			
			return "updateoffer";
			
		}
		offerService.save(specialoffer);
		
		return "redirect:/";
	}
	
}
