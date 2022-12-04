package com.example.demo.connection;

import lombok.Data;

@Data
public class RequestGeocoder {
	private Geometry geometry;
	private String type;
	private Properties properties;
}
