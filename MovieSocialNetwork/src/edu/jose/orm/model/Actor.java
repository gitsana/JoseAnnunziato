package edu.jose.orm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "orm", name = "actor")
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstname;
	private String lastname;
	
	@Temporal(TemporalType.DATE)	// unambiguously tell me what representation of date I want (it could be time, timestamp, or date)
	private Date dob;
	
	@ManyToOne(fetch = FetchType.LAZY) // only if you ask for it, then it will go and fetch the actors, otherwise won't go fetch them (EAGER will load them into memory even if not specified)
	@JoinColumn(name="movieId") // what implements the join on these two records
	private Movie movie; // notice this is NOT a foreign key (int), like in a table. THis is a higher-level way of creating the relationship
	
	// setters and getters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Actor(String firstname, String lastname, Date dob) {
		super();		
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;		
	}
	
	public Actor() {
		super();
	}
	
		
}
