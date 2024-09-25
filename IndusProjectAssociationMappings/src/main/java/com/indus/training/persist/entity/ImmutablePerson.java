package com.indus.training.persist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "immutable_person")
public class ImmutablePerson {
	@Id
	private Long id;
	private final String name;
	private final int age;

	// Constructor
	public ImmutablePerson(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	// Getters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
