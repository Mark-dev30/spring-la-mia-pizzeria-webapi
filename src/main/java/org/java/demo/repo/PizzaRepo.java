package org.java.demo.repo;

import java.util.List;

import org.java.demo.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer>{
	public List<Pizza> findByNameContaining(String name);
}

//Un repository fornisce metodi per recuperare, salvare, 
//aggiornare ed eliminare dati dalla sorgente di dati persistente, 
//come un database. Questa interazione avviene attraverso 
//le operazioni CRUD (Create, Read, Update, Delete) che il repository offre.
