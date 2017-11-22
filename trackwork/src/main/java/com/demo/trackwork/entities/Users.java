package com.demo.trackwork.entities;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Users {
	@Id
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String mailid;

	@OneToMany(mappedBy="user")
	@JsonBackReference(value="users")
	private Collection<Records> records = new ArrayList<Records>(); 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public Collection<Records> getRecords() {
		return records;
	}
	public void setRecords(Collection<Records> records) {
		this.records = records;
	}
	
}

