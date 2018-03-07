package com.shryans.test.data.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
	
@Entity
@Table(name="REGISTRATIONS")
public class Registration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5029934817681965963L;

	@EmbeddedId
	private RegistrationId id;
	
	@Column(name="DATE_REGISTERED")
	private Date registeredOn;
	
	@Column(name="DATE_ATTEMPTED")
	private Date attemptedOn;
	
	@Column(name="STATUS")
	private CourseStatus status = CourseStatus.PENDING;
	
	public Student getStudent() {
		return id.getStudent();
	}
	
	public Course getCourse() {
		return id.getCourse();
	}
	
	public Date getRegisteredOn() {
		return registeredOn;
	}

	public Date getAttemptedOn() {
		return attemptedOn;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public void setRegistrationId(RegistrationId id) {
		this.id = id;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	public void setAttemptedOn(Date attemptedOn) {
		this.attemptedOn = attemptedOn;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}
}