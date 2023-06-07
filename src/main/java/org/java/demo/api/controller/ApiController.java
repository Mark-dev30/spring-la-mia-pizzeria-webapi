package org.java.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.controller.dto.PizzaDto;
import org.java.demo.pojo.Pizza;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ApiController {
	@Autowired
	private PizzaService pizzaService;
//	Lista pizze/Ricerca
	
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getPizze(@RequestParam String name){
		
		if(name == null) {
			List<Pizza> pizze = pizzaService.findAll();
			return new ResponseEntity<>(pizze, HttpStatus.OK);
		}
		List<Pizza> pizze = pizzaService.findByNameContaining(name);
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
//	Pizza Singola
	
	@GetMapping("/pizza/{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id){
		
		Pizza pizza = pizzaService.findById(id).get();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
//	Creazione nuova pizza
	@PostMapping("/pizze")
	public ResponseEntity<PizzaDto> pizzaStore(
			@RequestBody Pizza pizza
		){
		pizza = pizzaService.save(pizza);
		
		
		return new ResponseEntity<>(new PizzaDto(pizza), HttpStatus.OK);
	}
	
//	Modifica di una pizza
	@PutMapping("/pizza")
	public ResponseEntity<PizzaDto> pizzaUpdate(
			@RequestBody Pizza pizza
		){
		pizza = pizzaService.save(pizza);
		
		
		return new ResponseEntity<>(new PizzaDto(pizza), HttpStatus.OK);
	}
	
//	Rimuovere una pizza
	
	@DeleteMapping("/pizza/{id}")
	public ResponseEntity<PizzaDto> deletePizza(@PathVariable int id){
		
		Pizza pizza = pizzaService.findById(id).get();
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(new PizzaDto(pizza), HttpStatus.OK);
	}
	
	
	
	
}