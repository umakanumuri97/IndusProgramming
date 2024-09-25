package com.indus.training.persist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
	@Table(name = "SpaceShip")
	public class Spaceship {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String model;
	    private double speed;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) {
	        this.model = model;
	    }

	    public double getSpeed() {
	        return speed;
	    }

	    public void setSpeed(double speed) {
	        this.speed = speed;
	    }
	}


