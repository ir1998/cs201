package data;

public class Event {

	
	public String time;
	private String action;
	private Movie movie;
	private String movieTitle;
	private String username;
	private int rating;
	private String actionIcon;
	
	
	public Event(){
		rating = -1;
	}
	
	//SETTERS
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setAction(String action){
		this.action = action.toLowerCase();
	}

	public void setActionIcon(String actionIcon){
		this.actionIcon = actionIcon.toLowerCase();
	}
	
	public void setRating(int rating){
		this.rating = rating;
	}
	
	public void setMovie(Movie movie){
		this.movie = movie;
		this.movieTitle = movie.getTitle();
	}
	
	//GETTERS
	public String getAction() {
		return action.toLowerCase();
	}

	public String getActionIcon(){
		if(this.action.equals("liked")){
			return "liked.png";
		}
		else if(this.action.equals("disliked")){
			return "disliked.png";
		}
		else if(this.action.equals("watched")){
			return "watched.png";
		}
		else if(this.action.equals("rated")){
			if(this.rating < 1){
				return "rating0.png";
			}
			else if(this.rating < 3){
				return "rating1.png";
			}
			else if(this.rating < 5){
				return "rating2.png";
			}
			else if(this.rating < 7){
				return "rating3.png";
			}
			else if(this.rating < 9){
				return "rating4.png";
			}
			else{
				return "rating5.png";
			}
		}
		return "ratings.png";
	}
	
	public Movie getMovie() {
		return movie;
	}

	public String getUsername() {
		return username;
	}
	
	public double getRating(){
		return rating;
	}
	
	public String getMovieTitle(){
		return movieTitle;
	}
	
	public String getRatingToDisplay(){
		Double halvedRating = (double)rating/ (double)2;
		return Long.toString(Math.round(halvedRating));
	}
	
	public void setTime(String t){
		this.time = t;
	}
	
	public String getTime(){
		return this.time;
	}
}
