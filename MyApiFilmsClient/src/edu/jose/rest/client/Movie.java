package edu.jose.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	private String idIMDB;
	private String plot;
	private String urlPoster;
	private String title;
	
	public String getIdIMDB() {
		return idIMDB;
	}
	public String getPlot() {
		return plot;
	}
	public String getUrlPoster() {
		return urlPoster;
	}
	public String getTitle() {
		return title;
	}
	public void setIdImdb(String idIMDB) {
		this.idIMDB = idIMDB;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
