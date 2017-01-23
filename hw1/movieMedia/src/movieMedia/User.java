package movieMedia;

import java.util.ArrayList;
import java.util.Map;

public class User {
	String username;
	String password;
	String firstName;
	String lastName;
	ArrayList<String> following = new ArrayList<String>();
	ArrayList<String> followers = new ArrayList<String>();
	ArrayList<Event> eventFeed = new ArrayList<Event>();

	public void setUsername(String uName) {
		username = uName;
	}

	public void setPassword(String pWord) {
		password = pWord;
	}

	public void setFName(String fName) {
		firstName = fName;
	}

	public void setLName(String lName) {
		lastName = lName;
	}

	public void addFollowing(String nFollowing) {
		following.add(nFollowing);
	}

	public void addEvent(String action, String movie, String rating) {
		Event tempEvent;
		if (rating != "") {
			tempEvent = new Event(action, movie, rating);
		} else {
			tempEvent = new Event(action, movie);
		}
		eventFeed.add(tempEvent);
	}

	public void addEvent(String action, String movie) {
		Event tempEvent = new Event(action, movie);
		eventFeed.add(tempEvent);
		tempEvent.setRating("-1");
	}

	public void addFollower(String nFollower) {
		followers.add(nFollower);
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<String> getFollowing() {
		return following;
	}

	public ArrayList<Event> getEventFeed() {
		return eventFeed;
	}

	public void printInfo() {
		System.out.println(this.firstName + " " + this.lastName);
		System.out.println("username: " + this.username);
		String stars = "";
		for (int i = 0; i < this.password.length() - 2; i++) {
			stars += "*";
		}
		System.out.println(
				"password: " + this.password.charAt(0) + stars + this.password.charAt(this.password.length() - 1));
		System.out.println("Following: ");
		for (String currFollower : this.following) {
			System.out.println("\t" + currFollower);
		}
		System.out.println("Followers: ");
		for (String currFollower : this.followers) {
			System.out.println("\t" + currFollower);
		}
	}

	public void printFeed(Map<String, ArrayList<User>> users) {
		System.out.println("Feed:");
		this.printActions();
		for (String currentFollowing : this.following) {
			ArrayList<User> currUserList = users.get(currentFollowing.toLowerCase());
			for (User currUser : currUserList) {
				if (currUser.getUsername().equals(currentFollowing)) {
					currUser.printActions();
				}
			}

		}
	}

	public void printActions() {
		for (Event currEvent : this.eventFeed) {
			if (currEvent.getRating() == -1) {
				System.out.println(this.username + " " + currEvent.getAction().toLowerCase() + " the movie "
						+ currEvent.getMovie() + ".");
			} else {
				System.out.println(this.username + " " + currEvent.getAction().toLowerCase() + " the movie "
						+ currEvent.getMovie() + " with a rating of " + currEvent.getRating());
			}

		}
	}
}
