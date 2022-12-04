package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="drink_list")
@Entity
public class DrinkEnt {
	


	@Column(name="id")
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@OneToMany(mappedBy="address_list")
	private long id;
	
	
//	@JoinColumn(name="name",referencedColumnName="drinkName")
	private String name;
	
	@OneToMany(mappedBy="drinkEnt")
	
	private List<AddressEnt> addressEntList;
	
    public DrinkEnt(String name) {
		
		this.name = name;
	}
    
}
