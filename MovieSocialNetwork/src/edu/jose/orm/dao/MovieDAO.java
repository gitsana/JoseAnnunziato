package edu.jose.orm.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.jose.orm.model.Actor;
import edu.jose.orm.model.Movie;

public class MovieDAO {

	// entity manager is magical object for JPA
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MovieSocialNetwork");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	// create all the CRUDs: 1) create movie, 2) read movie by Id, 3) read all movies, 4) update movie, 5) delete movie
	
	// CRUD OPERATION #1 : create movie
	public Movie createMovie(Movie movie) {		
		entityManager.getTransaction().begin(); // start a transaction; inserts, updates, and deletes must be inside of a transaction; can configure to do it in one line, but not right now
		entityManager.persist(movie);	// now becomes a managed object; framework is aware of it and is now tracking it for any changes; it's representing a record in the DB 
		entityManager.getTransaction().commit();	// commit transaction changes
		return null;
	}
	
	// CRUD OPERATION #2 : read movie by Id
	public Movie readMovieById(Integer id) {			// "find" is part of the API, "findAll" is not
		return entityManager.find(Movie.class, id); 	// tell entityManager to create an instance of Movie.class and populate it with the movie whose id is of Integer id		
	} 													// find returns a movie already; no need for transaction bc this already exists
	
	// CRUD OPERATION #3 : read ALL movies
	public List<Movie> readAllMovies() {
		Query query = entityManager.createQuery("select m from Movie m order by m.title"); // This is JPQL (java persistence query language), THIS IS NOT SQL!!! It's object, not table
		return (List<Movie>) query.getResultList(); // a list representation of executing this query; returns a list of objects, and then CAST IT as a List of Movies
	}
	
	// CRUD OPERATION #4 : update a movie. Movie movie must already exist; all fields compares and ONLY fields that are different will be updated
	public Movie updateMovie(Movie movie) {		
		entityManager.getTransaction().begin();
		entityManager.merge(movie);			// need to wrap in a transaction bc updating DB
		entityManager.getTransaction().commit();
		return movie;
	}
	
	// CRUD OPERATION #5 : delete a movie
	public void deleteMovie(int id) {
		entityManager.getTransaction().begin();				// need to wrap in a transaction bc updating DB
		Movie movie = entityManager.find(Movie.class, id);	// first retrieve it (can also use our method readMovieById here, but will just entityManager anyways for tutorial)
		entityManager.remove(movie);
		entityManager.getTransaction().commit();
	}
	
	// extra CRUD operations for relationship maintenance
	// add an actor to the actor list in a movie
	public void addActor(Integer movieId, Actor actor) {
		entityManager.getTransaction().begin();		// wrapped in transaction bc it's modifying DB
		Movie movie = entityManager.find(Movie.class, movieId);
		actor.setMovie(movie);
		movie.getActors().add(actor);
		entityManager.merge(movie);
		entityManager.getTransaction().commit();	// wrapped in transaction bc it's modifying DB
	}
	
	public void deleteActor(int id) {
		entityManager.getTransaction().begin();
		Actor actor = entityManager.find(Actor.class, id);
		entityManager.remove(actor);
		entityManager.getTransaction().commit();
	}
	
	// MAIN
	public static void main(String[] args) {
		MovieDAO movieDao = new MovieDAO();
		// # 1 -------------- dao.createMovie(movie)
//		Movie movie = new Movie("The Wizard of Oz","Dorothy goes to Oz","the Poster", "T&L id");
//		dao.createMovie(movie);
//		System.out.println("movie id = " + movie.getId());
//		System.out.println("movie getMovieId = " + movie.getMovieId());
		
		// # 2 -------------- dao.readMovieById(i)
//		Map<Integer, String> movieMap = new HashMap<Integer,String>();
//		
//		Movie movie = dao.readMovieById(3);		
//		Movie movie2 = dao.readMovieById(4);
//		
//		movieMap.put(3, movie.getTitle());
//		movieMap.put(4, movie2.getTitle());
//		
//		System.out.println(movieMap);
			
		
		// # 5 -------------- deleteMovie(int id)
//		dao.deleteMovie(2);
		
		// # 3 -------------- dao.readAllMovies()
		List<Movie> movies = movieDao.readAllMovies();
		for(Movie m : movies) {
			System.out.println(m.getTitle() + " " + m.getImdbId());
		}
		
		// # 4 -------------- updateMovie(movie)
//		Movie legalBlondMovie = dao.readMovieById(3);
//		System.out.println("legalBlondMovie :: " + legalBlondMovie.getTitle());
//		legalBlondMovie.setPlot("A girl from California goes to Harvard Law"); // setPlot is a sister object created by JPA API and now keeps track of the different fields
//		dao.updateMovie(legalBlondMovie);										// has different implementation that Movie.setPlot (sister object)
		
		// look at a particular movie/actors
		Movie devilWearsPrada = movieDao.readMovieById(4);	// here, actors do NOT exist bc it's lazy
		System.out.println(devilWearsPrada.getTitle());
		System.out.println("DWPrada actors.size = " + devilWearsPrada.getActors().size());
		
		// add an actor
		//Actor actorEmily = new Actor("Emily","Blunt", new Date());
		//movieDao.addActor(4, actorEmily);
		
		Actor actorMeryl = new Actor("Meryl","Streep",new Date());
		movieDao.addActor(4, actorMeryl);
		
		System.out.println("DWPrada *new* actors.size = " + devilWearsPrada.getActors().size());
		
		List<Actor> dwpActors = devilWearsPrada.getActors();		// ONLY when we explicity ask for it, we get the actors populated
		for(Actor a : dwpActors) {
			System.out.println(a.getFirstname() + " " + a.getLastname());
		}
		
	}// end main

}
