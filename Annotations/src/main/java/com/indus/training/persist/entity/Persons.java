package com.indus.training.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "person")
public class Persons {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int PersonId;
	  
	    @Column(name = "personname")
	    private String PersonName;
	    @Column(name = "personcontact")
	    private String PersonContact;
	    
		public int getPersonId() {
			return PersonId;
		}
		public void setPersonId(int personId) {
			PersonId = personId;
		}
		public String getPersonName() {
			return PersonName;
		}
		public void setPersonName(String personName) {
			PersonName = personName;
		}
		public String getPersonContact() {
			return PersonContact;
		}
		public void setPersonContact(String personContact) {
			PersonContact = personContact;
		}
		public Persons() {
			super();
			// TODO Auto-generated constructor stub
		} 
}
