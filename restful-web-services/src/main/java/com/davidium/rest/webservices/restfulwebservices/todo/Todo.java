package com.davidium.rest.webservices.restfulwebservices.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {

@Id
@GeneratedValue
private Long id;
private Date date;
private String description;
private String username;
private boolean completion;

//default constructor for auto-purposes, or PUT requests won't work
protected Todo() {
	
}

public Todo(Long id, String username,  String description, Date date, boolean completion) {
	super();
	this.id = id;
	this.date = date;
	this.description = description;
	this.username = username;
	this.completion = completion;
}


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Date getDate() {
	//return new SimpleDateFormat("MM-dd-yyyy").format(date);
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public boolean isCompletion() {
	return completion;
}

public void setCompletion(boolean completion) {
	this.completion = completion;
}

public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
	return result;
}


public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Todo other = (Todo) obj;
	if (id != other.id)
		return false;
	return true;
}


}
