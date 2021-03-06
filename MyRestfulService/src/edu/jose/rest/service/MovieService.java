package edu.jose.rest.service;

import java.util.List;

import edu.jose.rest.dao.MovieDAO;
import edu.jose.rest.model.Movie;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/movie")
public class MovieService {

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createMovie(Movie movie) {
		MovieDAO movieDAO = new MovieDAO();
		movieDAO.createMovie(movie);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> findAllMovies() {
		MovieDAO movieDAO = new MovieDAO();
		List<Movie> movies = movieDAO.findAllMovies();
		return movies;
	}
	
	@GET
	@Path("/{theId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie findMovie(@PathParam("theId") int id) {
		MovieDAO movieDAO = new MovieDAO();
		Movie movie = movieDAO.findMovie(id);
		return movie;
	}
}
