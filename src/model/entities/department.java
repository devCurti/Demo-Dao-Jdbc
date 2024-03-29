package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class department implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public department() {};
	
	public department(String name) {
		this.name = name;
	}
	
	public department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		department other = (department) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "department [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
