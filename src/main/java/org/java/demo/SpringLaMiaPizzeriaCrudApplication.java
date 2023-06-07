package org.java.demo;

import java.time.LocalDate;

import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.service.RoleService;
import org.java.demo.auth.service.UserService;
import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.pojo.Specialoffer;
import org.java.demo.service.IngredientService;
import org.java.demo.service.PizzaService;
import org.java.demo.service.SpecialofferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialofferService specialofferService;
	
	@Autowired 
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String root = new BCryptPasswordEncoder().encode("root");
		
		User userUser = new User("user", root, roleUser);
		User userAdmin = new User("admin", root, roleAdmin);
				
		userService.save(userUser);
		userService.save(userAdmin);
		
		
		Pizza p1 = new Pizza("Margherita", "Classic pizza in italy", "https://www.centrocormano.it/wp-content/uploads/2021/08/tipi-di-pizze-2.jpg", 5);
		Pizza p2 = new Pizza("Capriciosa", "Classic pizza in italy", "https://upload.wikimedia.org/wikipedia/commons/2/2a/Pizza_capricciosa.jpg", 10);
		Pizza p3 = new Pizza("Pomodoro", "Classic pizza in italy", "https://www.centrocormano.it/wp-content/uploads/2021/08/tipi-di-pizze-2.jpg", 15);
		Pizza p4 = new Pizza("Prosciutto e Funghi", "Classic pizza in italy", "https://upload.wikimedia.org/wikipedia/commons/2/2a/Pizza_capricciosa.jpg", 12);
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
			
		Specialoffer sp1 = new Specialoffer("Offer1",LocalDate.now(),LocalDate.of(2023, 10, 5),15, p1);
		Specialoffer sp2 = new Specialoffer("Offer2",LocalDate.now(),LocalDate.of(2023, 10, 6),20, p1);
		Specialoffer sp3 = new Specialoffer("Offer3",LocalDate.now(),LocalDate.of(2023, 10, 7),25, p2);
		Specialoffer sp4 = new Specialoffer("Offer4",LocalDate.now(),LocalDate.of(2023, 10, 8),30, p2);
		Specialoffer sp5 = new Specialoffer("Offer5",LocalDate.now(),LocalDate.of(2023, 10, 9),35, p3);
		Specialoffer sp6 = new Specialoffer("Offer6",LocalDate.now(),LocalDate.of(2023, 10, 10),40, p4);
		Specialoffer sp7 = new Specialoffer("Offer7",LocalDate.now(),LocalDate.of(2023, 10, 11),45, p4);
		Specialoffer sp8 = new Specialoffer("Offer8",LocalDate.now(),LocalDate.of(2023, 10, 12),50, p4);
		specialofferService.save(sp1);
		specialofferService.save(sp2);
		specialofferService.save(sp3);
		specialofferService.save(sp4);
		specialofferService.save(sp5);
		specialofferService.save(sp6);
		specialofferService.save(sp7);
		specialofferService.save(sp8);
		
		Ingredient in1 = new Ingredient("Flour");
		Ingredient in2 = new Ingredient("Tomato");
		Ingredient in3 = new Ingredient("Cheese");
		Ingredient in4 = new Ingredient("Oil");
		Ingredient in5 = new Ingredient("Ham");
		Ingredient in6 = new Ingredient("Mushrooms");
		Ingredient in7 = new Ingredient("Olives");
		Ingredient in8 = new Ingredient("Peas");
		Ingredient in9 = new Ingredient("Rocket");
		Ingredient in10 = new Ingredient("Raw ham");
		
		ingredientService.save(in1);
		ingredientService.save(in2);
		ingredientService.save(in3);
		ingredientService.save(in4);
		ingredientService.save(in5);
		ingredientService.save(in6);
		ingredientService.save(in7);
		ingredientService.save(in8);
		ingredientService.save(in9);
		ingredientService.save(in10);
		
	}
}
