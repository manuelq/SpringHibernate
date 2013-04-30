package com.simplehibernate.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String subject;

	@ManyToOne
	private Person sender;

	@ManyToMany
	private List<Person> recipients;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public List<Person> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Person> recipients) {
		this.recipients = recipients;
	}

}