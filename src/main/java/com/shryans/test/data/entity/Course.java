package com.shryans.test.data.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1573581829311017468L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="COST")
	private int cost;
	
	@Column(name="DATE_CREATED")
	private Date createdOn;
	
	@Column(name="DATE_MODIFIED")
	private Date modifiedOn;
	
	@ManyToOne
	@JoinColumn(name="CREATOR", foreignKey=@ForeignKey(name="USERS"), referencedColumnName="ID")
	private User creator;
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		
		if(obj == null || obj.getClass() != this.getClass()) return false;
		
		Course o = (Course) obj;
		
		return o.getId() == this.getId();
	}
	
	@Override
	public int hashCode(){
		return this.getId();
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
