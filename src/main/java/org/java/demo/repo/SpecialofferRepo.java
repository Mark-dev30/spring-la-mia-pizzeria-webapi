package org.java.demo.repo;

import org.java.demo.pojo.Specialoffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialofferRepo extends JpaRepository<Specialoffer, Integer>{
	
}

