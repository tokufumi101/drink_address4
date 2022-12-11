package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AddressEnt;

@Repository
public interface AddressDao extends JpaRepository<AddressEnt,Long> {
	
	  @Query(value = "SELECT t FROM AddressEnt t WHERE drink_ent_ID = :id")
	  List<AddressEnt> findByDrinkEnt(@Param("id")String id);
	  
	  
	  public void deleteById(long id);
}
