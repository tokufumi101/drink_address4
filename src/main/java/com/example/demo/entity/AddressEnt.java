package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity




@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name="address_list")
public class AddressEnt{
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="register_date")
	private Timestamp registerDate;

	
	@Column(name="address")
	private String address;
	
	@ManyToOne
//	@JoinColumn(name="drinkName")
	private DrinkEnt drinkEnt;
	
	@Column(name="latitude")
	private double latitude;
	@Column(name="longitude")
	private double longitude;
	
}
