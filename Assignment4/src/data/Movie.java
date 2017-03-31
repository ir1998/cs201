package data;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	private String title;
	private String director;
	private String description;
	private int year;
	private List<Actor> actors;
	private List<String> writers;
	private double rating;
	private double totalRating;
	private int countRatings;
	private String genre;
	private String image;
	
	public Movie() {
		actors = new ArrayList<>();
		writers = new ArrayList<>();
		totalRating = 0;
		countRatings = 0;
	}
	
	//METHODS
	public void incrRating(double rating, DataStorage ds){
		this.totalRating += rating;
		this.countRatings++;
		ds.updateMovieRating(this);
	}
	
	public void setTotalRatings(String rating){
		this.totalRating = Double.parseDouble(rating);
	}
	public void setCountRatings(String count){
		this.countRatings = Integer.parseInt(count);
	}
	
	public double getAvgRating(){
		return (totalRating/countRatings);
	}
	public double getTotalRatings(){
		return totalRating;
	}
	public int getCountRatings(){
		return countRatings;
	}
	
	public void addActor(Actor actor){
		actors.add(actor);
	}
	
	public void addWriter(String actor){
		writers.add(actor);
	}
	
	//GETTERS
	public String getGenre(){
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public String getDescription() {
		return description;
	}

	public int getYear() {
		return year;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public List<String> getWriters() {
		return writers;
	}
	

	public double getRating() {
		return rating;
	}
	
	//SETTERS
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void setWriters(List<String> writers) {
		this.writers = writers;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
