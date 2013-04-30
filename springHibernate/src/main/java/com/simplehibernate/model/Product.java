package com.simplehibernate.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name="px")
	private BigDecimal price;
	
	private BigDecimal tax;

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	// In class Team
	@ManyToOne()
	private Supplier supplier;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@Formula("px+tax")
	private double calculatedValue;
	
	public double getCalculatedValue() {
		return calculatedValue;
	}

	public void setCalculatedValue(double calculatedValue) {
		this.calculatedValue = calculatedValue;
	}

}
