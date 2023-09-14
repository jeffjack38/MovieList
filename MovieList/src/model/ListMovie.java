/**
 * @author jeade - jeaden
 * CIS175 - Fall 2023
 *Sep 13, 2023
 */
package model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movies")
public class ListMovie {
	@Id
	@GeneratedValue
	
	//Attributes/instance variables
	@Column(name="ID")
	private int id;
	@Column(name="MOVIE")
	private String movie;
	@Column(name="GENRE")
	private String genre;
	
	//default no arg constructor
	public ListMovie() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	//constructor with movie and genre
	public ListMovie(String movie, String genre) {
		this.movie = movie;
		this.genre = genre;
	}
	
	//print method movie - genre
	public String returnMovieDetails() {
		return this.id + "-" + this.movie + "-" + this.genre;
	}
	
	@Override
	public String toString() {
		return "ListMovie [id=" + id + ", movie=" + movie + ", genre=" + genre + "]";
	}

}
