package com.indus.training.persist.entity;

import jakarta.persistence.Embeddable;

@Embeddable
	public class Name {
	    private char initial;
	    private String first;
	    private String last;

	    // Getters and Setters
	    public char getInitial() {
	        return initial;
	    }

	    public void setInitial(char initial) {
	        this.initial = initial;
	    }

	    public String getFirst() {
	        return first;
	    }

	    public void setFirst(String first) {
	        this.first = first;
	    }

	    public String getLast() {
	        return last;
	    }

	    public void setLast(String last) {
	        this.last = last;
	    }
	}



