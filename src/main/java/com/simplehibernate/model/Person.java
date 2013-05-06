package com.simplehibernate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@NamedQueries({
	@NamedQuery(
	name = "findPersonByName",
	query = "from Person p where p.name = :name"
	)
})


@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
  
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "sender",cascade=CascadeType.ALL)
	private List<Email> sent;

	@ManyToMany(mappedBy = "recipients")
	private List<Email> received;

	public Person(){
		super();
	}
	public Person(String string) {
		this.name = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Email> getSent() {
		return sent;
	}

	public void setSent(List<Email> sent) {
		this.sent = sent;
	}

	public List<Email> getReceived() {
		return received;
	}

	public void setReceived(List<Email> received) {
		this.received = received;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
