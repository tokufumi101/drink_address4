package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AddressEnt;
import com.example.demo.entity.DrinkEnt;

@Repository
public interface AddressDao extends JpaRepository<AddressEnt,Long> {
	
	public List<AddressEnt> findByDrinkEnt(DrinkEnt drinkEnt);

	public void deleteById(long id);
}
