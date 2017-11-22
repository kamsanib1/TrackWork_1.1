package com.demo.trackwork.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Records {

	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private Time time;
	private String topic;
	private int type;
	private int status;
	private int priority;
	private int views;
	private String query;

	@ManyToOne
	@JoinColumn
	@JsonBackReference(value="records")
	private Users user;

	@OneToMany(mappedBy="record")
	@JsonManagedReference(value="solutions")
	private Collection<Solutions> solutions = new ArrayList<Solutions>();
	
	@ManyToMany(mappedBy="id")
	@JoinTable(name = "RECORD_TAGS", joinColumns= {@JoinColumn(name="id")}, inverseJoinColumns= {@JoinColumn(name="id")})
	private Collection<Tags> tags = new ArrayList<Tags>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Collection<Solutions> getSolutions() {
		return solutions;
	}

	public void setSolutions(Collection<Solutions> solutions) {
		this.solutions = solutions;
	}

	public Collection<Tags> getTags() {
		return tags;
	}

	public void setTags(Collection<Tags> tags) {
		this.tags = tags;
	}

}
