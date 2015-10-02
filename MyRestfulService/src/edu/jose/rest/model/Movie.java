package edu.jose.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "orm", name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // it generates the ID itself
	private int id;
	private String title;
	private String plot;
	private String urlPoster;
	private String imdbId;

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getPlot() {
		return plot;
	}
	public String getUrlPoster() {
		return urlPoster;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	
	
}
