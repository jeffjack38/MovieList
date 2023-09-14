/**
 * @author jeade - jeaden
 * CIS175 - Fall 2023
 *Sep 13, 2023
 */
package control;
  
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListMovie;

public class ListMovieHelper {
	
	//global instance of the EntityManagerFactory
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("MovieList");
	
	//method to accept a ListMovie to add
	public void insertMovie(ListMovie mo) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(mo);
		em.getTransaction().commit();
		em.close();
	}
	
	//method showAllItems() that will return a generic list of all the items in the database
	public List<ListMovie> showAllMovies() {
		EntityManager em = emfactory.createEntityManager();
		//return objects with an alias i (this is JPQL - Java Persistence Query Language
		//this JPA query is going to slect every ListMovie from the database and turn them into ListMovie objects
		//.getResultList() tells JPA that we want the whole list
		List<ListMovie> allMovies = em.createQuery("SELECT i FROM ListMovie i").getResultList();
		return allMovies;		
	}
	
	//remove item
	// takes the ListMovie that is passed into it and searches for it by looking for the listmovie by the movie and genre passed in 
	//more than one it selects the first
	//grabs result and stores it into the result ListMovie, then removes with remove() method
	public void deleteMovie(ListMovie toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListMovie>typedQuery = em.createQuery("SELECT mo FROM ListMovie mo WHERE mo.movie = :selectedMovie and mo.genre = :selectedGenre", ListMovie.class);
		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedMovie", toDelete.getMovie());
		typedQuery.setParameter("selectedGenre", toDelete.getGenre());
		
		//get one result
		typedQuery.setMaxResults(1);
		
		//get the result and save it into a new list item
		ListMovie result = typedQuery.getSingleResult();
		
		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	
	}
	
	
	public ListMovie searchForMovieById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListMovie found = em.find(ListMovie.class, idToEdit);
		em.close();
		return found;

	}


	
	//search for movie by movie
	public List<ListMovie> searchForMoviebyMovie(String movieName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListMovie> typedQuery = em.createQuery("select mo from ListMovie mo where mo.movie = :selectedMovie", ListMovie.class);
		typedQuery.setParameter("selectedMovie", movieName);
		
		List<ListMovie>foundMovies = typedQuery.getResultList();
		em.close();
		
		return foundMovies;
	}
	
	//search for movie by genre
	public List<ListMovie> searchforMoviebyGenre(String genreName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListMovie> typedQuery = em.createQuery("select mo from ListMovie mo where mo.genre = :selectedGenre", ListMovie.class);
		typedQuery.setParameter("selectedGenre", genreName);
		List<ListMovie> foundMovies = typedQuery.getResultList();
		em.close();
		return foundMovies;
	}

	//method to take in new movie details and update the movie in the database
	// keeps the ID the same from when it found the movie
	public void updateMovie(ListMovie toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	//closing the EntityManagerFactory
	public void cleanUp() {
		emfactory.close();
	}
}
