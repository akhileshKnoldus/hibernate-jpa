package com.learn.hibrnate.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;
	
	public PartTimeEmployee() {
		// TODO Auto-generated constructor stub
	}
	
	public PartTimeEmployee(String name,BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage=hourlyWage;
	}
	
	
}
