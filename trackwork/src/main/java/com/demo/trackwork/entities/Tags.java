package com.demo.trackwork.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Tags {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn
	private Users username;
	
	@ManyToMany(mappedBy="tags")
	private Collection<Records> records = new ArrayList<Records>();
	
	public Collection<Records> getRecords() {
		return records;
	}
	public void setRecords(Collection<Records> records) {
		this.records = records;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Users getUsername() {
		return username;
	}
	public void setUsername(Users username) {
		this.username = username;
	}

}
