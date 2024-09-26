package com.indus.training.persist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "events")
public class Events {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		private int EventId;
	    @Column(name = "eventname")
		private String EventName;
	    @Column(name = "eventdate")
		private String EventDate;
	    @OneToOne
	    @JoinColumn(name = "personid")
		private Persons Person;
		
		public int getEventId() {
			return EventId;
		}
		public void setEventId(int eventId) {
			EventId = eventId;
		}
		public String getEventName() {
			return EventName;
		}
		public void setEventName(String eventName) {
			EventName = eventName;
		}
		public String getEventDate() {
			return EventDate;
		}
		public void setEventDate(String eventDate) {
			EventDate = eventDate;
		}


		public Persons getPerson() {
			return Person;
		}
		public void setPerson(Persons person) {
			Person = person;
		}


		
		public Events() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
