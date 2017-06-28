package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
	private String title;
	private String image;
	private String director;
	private String description;
	private int year;
	private String actors;
	private String writers;
	private int numRatings;
	private long totalRating;
	private String genres;
	private double imdbRating;
	public String imdbId;
	
	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Movie() {
		this.numRatings = 0;
		this.totalRating = 0;
	}
	
	//METHODS
	public void incrementRatingCount(){
		numRatings++;
	}
	
	public void updateRatingTotal(int rating){
		totalRating += rating;
	}
	
	//GETTERS
	
	public long getAverageRating(){
		return (numRatings == 0 ? 0 : (totalRating/numRatings));
	}
	
	public long getTotalRating(){
		return totalRating;
	}
	
	public int getRatingCount(){
		return numRatings;
	}
	
	public String getGenres(){
		return genres;
	}
	
	public List<String> getWriters(){
		String[] writerArray = this.writers.split(", ");
		List<String> writerNameList = Arrays.asList(writerArray);
		return writerNameList;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImage(){
		return image;
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
	public String getActorString() {
		return actors;
	}
	public List<Actor> getActors(){
		String[] actorArray = this.actors.split(", ");
		List<String> actorNameList = Arrays.asList(actorArray);
		List<Actor> actorList = new ArrayList<Actor>();
		for(String s: actorNameList){
			String[] names = s.split(" ");
			actorList.add(new Actor(names[0], names[1], TMDBapi.getActorImage(s)));
		}
		return actorList;
	}
	
	public double getIMDBrating(){
		return this.imdbRating;
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

	public void setActors(String actors){
		this.actors = actors;
	}
	
	public void setWriters(String writers){
		this.writers = writers;
	}

	public void setRatingTotal(long rating) {
		totalRating = rating;
	}
	
	public void setRatingCount(int num) {
		numRatings = num;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void setIMDBrating(double r){
		this.imdbRating = r;
	}
}
