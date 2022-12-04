package com.example.demo.connection;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Geometry {
	private List<Coordinates> coordinates=new ArrayList<>();
	private String type;
}
