package com.shryans.test.data.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
@PrimaryKeyJoinColumn(name="ID")
public class Student extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3275482181330528700L;
}
