package edu.jose.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.jose.rest.model.Movie;

public class MovieDAO {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyRestfulService");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public List<Movie> findAllMovies() {
		Query selectAllQuery = entityManager.createQuery("select m from Movie m");
		return selectAllQuery.getResultList();
	}
	
	// this is the DB's id, not imdb id
	public Movie findMovie(int id) {
		return entityManager.find(Movie.class, id);
	}
	
	public void createMovie(Movie movie) {
		entityManager.getTransaction().begin();
		entityManager.persist(movie);
		entityManager.getTransaction().commit();		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello...");
		MovieDAO movieDAO = new MovieDAO();
		List<Movie> movies = movieDAO.findAllMovies();
		for(Movie m : movies) {
			System.out.println(m.getId() + " " + m.getImdbId() + " " + m.getTitle());
		}
		System.out.println("Finding 1 movie:");
		Movie movie = movieDAO.findMovie(3);
		System.out.println(movie.getId() + " " + movie.getImdbId() + " " + movie.getTitle());
		System.out.println("end");
	}
}

