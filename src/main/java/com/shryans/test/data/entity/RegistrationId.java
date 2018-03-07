package com.shryans.test.data.entity;

import java.io.Serializable;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RegistrationId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -210645884262157554L;

	@ManyToOne
	@JoinColumn(name="USER_ID", foreignKey=@ForeignKey(name="STUDENT"), referencedColumnName="ID")	
	private Student user;
	
	@ManyToOne
	@JoinColumn(name="COURSE_ID", foreignKey=@ForeignKey(name="COURSE"), referencedColumnName="ID")
	private Course course;

	public RegistrationId() {
		super();
	}

	public RegistrationId(Student user, Course course) {
		super();
		this.user = user;
		this.course = course;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null || this.getClass() != obj.getClass())
			return false;
		RegistrationId o = (RegistrationId) obj;
		
		return o.getStudent().equals(this.getStudent()) && o.getCourse().equals(this.getCourse());
	}
	
	@Override
	public int hashCode() {
		return this.getStudent().hashCode() ^ this.getCourse().hashCode();
	}
	
	public Student getStudent() {
		return user;
	}

	public void setStudent(Student Student) {
		this.user = Student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
