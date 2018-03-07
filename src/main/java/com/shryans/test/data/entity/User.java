package com.shryans.test.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6347773010937527628L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
	
	@Column(name="USERNAME")
	protected String username;
	
	@Column(name="PASSWORD")
	protected String password;

	@Column(name="ROLE")
	protected Role role;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		
		if(obj == null || obj.getClass() != this.getClass()) return false;
		
		User o = (User) obj;
		
		return o.getId() == this.getId();
	}
	
	@Override
	public int hashCode(){
		return this.getId();
	}
	
	public int getId() {
		return id;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
