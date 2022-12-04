package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AddressDto {
	private long addressId;
	private String address;
	private String drinkName;
	private Timestamp registerDate;
}
