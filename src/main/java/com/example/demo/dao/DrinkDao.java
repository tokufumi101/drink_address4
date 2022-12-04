package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DrinkEnt;

@Repository
public interface DrinkDao extends JpaRepository<DrinkEnt,Long>{
	public  DrinkEnt findByName(String string);

	public boolean existsByName(String string);
	public DrinkEnt findById(long id);
	

}
