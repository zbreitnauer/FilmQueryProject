package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {


	private int id;
	private String first_name;
	private String last_name;
	
	public Actor() {}
	
	public Actor(int id, String first_name, String last_name) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public String toString() {
		return "Actor Id: " + id +"\n"+ "First Name: " + first_name +"\n"+ "Last Name: " + last_name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first_name, id, last_name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
//		return Objects.equals(first_name, other.first_name) && id == other.id
//				&& Objects.equals(last_name, other.last_name);
	}

}
