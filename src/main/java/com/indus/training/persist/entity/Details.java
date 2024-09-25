package com.indus.training.persist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Column;

@Entity
@Table(name = "employees") // This maps the entity to the "employees" table in the database
public class Details {

	@Id
	@Column(name = "employeeID") // Maps the field to the employeeID column in the table
	private String employeeID;

	@Column(name = "firstName") // Maps the field to the firstName column
	private String firstName;

	@Column(name = "lastName") // Maps the field to the lastName column
	private String lastName;

	@Column(name = "employeeSalary") // Maps the field to the employeeSalary column
	private double employeeSalary;

	@Column(name = "employeeJobRole") // Maps the field to the employeeJobRole column
	private String employeeJobRole;

	@Column(name = "numberOfWorkingHours") // Maps the field to the numberOfWorkingHours column
	private int numberOfWorkingHours;

	public Details() {
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeJobRole() {
		return employeeJobRole;
	}

	public void setEmployeeJobRole(String employeeJobRole) {
		this.employeeJobRole = employeeJobRole;
	}

	public int getNumberOfWorkingHours() {
		return numberOfWorkingHours;
	}

	public void setNumberOfWorkingHours(int numberOfWorkingHours) {
		this.numberOfWorkingHours = numberOfWorkingHours;
	}
}
