package com.simplehibernate.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Parent implements Serializable
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@JoinTable(name = "parent_child",
	joinColumns = @JoinColumn(name = "parent_id"),
	inverseJoinColumns = @JoinColumn(name = "child_id"))
	/* List of contracts for which this user has registered. */
	private Set<Child> children;
	
    public Long getId() {
		return id;
	}


	public Set<Child> getChildren() {
		return children;
	}


	public void setChildren(Set<Child> children) {
		this.children = children;
	}


	public void setId(Long id) {
		this.id = id;
	}

}