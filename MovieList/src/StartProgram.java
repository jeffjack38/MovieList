
 
import java.util.List;
import java.util.Scanner;

import control.ListMovieHelper;
import model.ListMovie;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListMovieHelper mov = new ListMovieHelper();

		private static void addMovie() {
			// TODO Auto-generated method stub
			System.out.print("Enter a movie title: ");
			String movie = in.nextLine();
			System.out.print("Enter an movie genre: ");
			String genre = in.nextLine();
			//create ListItem object and send to persist
			ListMovie toAdd = new ListMovie(movie, genre);
			//put item
			mov.insertMovie(toAdd);

		}

		private static void deleteMovie() {
			// TODO Auto-generated method stub
			System.out.print("Enter the movie to delete: ");
			String movie = in.nextLine();
			System.out.print("Enter the genre to delete: ");
			String genre = in.nextLine();
			//call the deleteItem() from the ListMovieHelper
			ListMovie toDelete = new ListMovie(movie, genre);
			mov.deleteMovie(toDelete);

		}

		private static void editMovie() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Movie");
			System.out.println("2 : Search by Genre");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListMovie> foundMovies;
			if (searchBy == 1) {
				System.out.print("Enter the movie name: ");
				String movieName = in.nextLine();
				foundMovies = mov.searchForMoviebyMovie(movieName);
				
			} else {
				System.out.print("Enter the genre: ");
				String genreName = in.nextLine();
				foundMovies = mov.searchforMoviebyGenre(genreName);
				

			}

			if (!foundMovies.isEmpty()) {
				System.out.println("Found Results.");
				for (ListMovie l : foundMovies) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListMovie toEdit = mov.searchForMovieById(idToEdit);
				System.out.println("Retrieved " + toEdit.getMovie() + " from " + toEdit.getMovie());
				System.out.println("1 : Update Movie");
				System.out.println("2 : Update Genre");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Movie: ");
					String newMovie = in.nextLine();
					toEdit.setMovie(newMovie);
				} else if (update == 2) {
					System.out.print("New Genre: ");
					String newGenre = in.nextLine();
					toEdit.setGenre(newGenre);
				}

				mov.updateMovie(toEdit);

			} else {
				System.out.println("---- No results found");
			}
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Movie list! ---");
			while (goAgain) {
				System.out.println("*  Select an action:");
				System.out.println("*  1 -- Add a movie");
				System.out.println("*  2 -- Edit a movie");
				System.out.println("*  3 -- Delete a movie");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addMovie();
				} else if (selection == 2) {
					editMovie();
				} else if (selection == 3) {
					deleteMovie();
				} else if (selection == 4) {
					viewTheList();
				} else {
					mov.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListMovie>allMovies = mov.showAllMovies();
			for(ListMovie singleMovie : allMovies) {
				System.out.println(singleMovie.returnMovieDetails());
			}
		}
	}