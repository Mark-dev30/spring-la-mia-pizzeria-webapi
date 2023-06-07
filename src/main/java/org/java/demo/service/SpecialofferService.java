package org.java.demo.service;

import java.util.List;
import java.util.Optional;


import org.java.demo.pojo.Specialoffer;
import org.java.demo.repo.SpecialofferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialofferService {
	
	@Autowired
	private SpecialofferRepo specialofferRepo;
	
	public List<Specialoffer> findAll(){
		
		return specialofferRepo.findAll();
	}
	
	public Optional<Specialoffer> findById(int id) {
		
		return specialofferRepo.findById(id);
	}
	
	public Specialoffer save(Specialoffer specialoffer) {
		
		return specialofferRepo.save(specialoffer);
	}
}
