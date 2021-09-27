package edu.fa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Course_Name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@ElementCollection
	private List<Syllabus> syllabuses = new ArrayList<Syllabus>();
	
	public Course() {
		super();
	}
	
	
	
	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	



	public List<Syllabus> getSyllabuses() {
		return syllabuses;
	}



	public void setSyllabuses(List<Syllabus> syllabuses) {
		this.syllabuses = syllabuses;
	}


	
	
	public Course(String name) {
		super();
		this.name = name;
	}



	public Course(String name, Date createDate, List<Syllabus> syllabuses) {
		super();
		this.name = name;
		this.createDate = createDate;
		this.syllabuses = syllabuses;
	}



	public Course(String name, Date createDate) {
		super();
		this.name = name;
		this.createDate = createDate;
	}



	



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
