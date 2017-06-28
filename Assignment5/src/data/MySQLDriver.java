package data;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.Driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class MySQLDriver {
	public String ipaddress;
	public String db;
	public String user;
	public String password;
	public Connection conn;
	public String loggedInUser;
	private final static String selectUser = "SELECT * FROM USERS WHERE NAME=?";
	private final static String addUser = "INSERT INTO USER(USERNAME, PASSWORD, FNAME, LNAME, FULLNAME, IMAGEURL) VALUES(?,?,?,?,?,?)";
	private final static String getName = "SELECT * FROM USERS WHERE USERNAME=?&";
	// private final static String updateProduct = "UPDATE FACTORYORDERS SET
	// created = created + 1 WHERE NAME=?";

	public MySQLDriver() {
		this.parseConfig();
		try {
			new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void parseConfig() {
		try {
			FileReader input = new FileReader("/Users/Nick/Desktop/cs201/" + "Assignment5/rsrc/config.txt");
			BufferedReader buf = new BufferedReader(input);
			String line = null;
			while ((line = buf.readLine()) != null) {
				String[] arr = line.split(":");
				for (int i = 0; i < arr.length; i++) {
					if (arr[0].equals("ipaddress"))
						this.ipaddress = arr[1];
					else if (arr[0].equals("db"))
						this.db = arr[1];
					else if (arr[0].equals("user"))
						this.user = arr[1];
					else if (arr[0].equals("password"))
						this.password = arr[1];
					else {
						System.out.println("error parsing config.txt");
						System.out.println(arr[0] + " , " + arr[1]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void connect() {
		try {
			conn = DriverManager.getConnection("Jdbc:mysql://" + this.ipaddress + ":3306/" + this.db + "?" + "user="
					+ this.user + "&password=" + this.password + "&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setLoggedInUser(String username) {
		this.loggedInUser = username;
	}

	public User getLoggedInUser() {
		return getUserByUsername(this.loggedInUser);
	}

	// returns username of users who given username is following
	public Set<String> getFollowing(String username) {
		Set<String> following = new HashSet<String>();
		this.connect();

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT following FROM " + this.db + ".Follows WHERE follower=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				following.add(rs.getString(1));
				// System.out.println(username+" is following: " +
				// rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.stop();
		return following;
	}

	// returns usernames who currently follow given user
	public Set<String> getFollowers(String username) {
		Set<String> followers = new HashSet<String>();
		this.connect();

		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT follower FROM " + this.db + ".Follows WHERE following=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				followers.add(rs.getString(1));
				// System.out.println(username+" has follower: " +
				// rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.stop();
		return followers;
	}
	
	public void addEvent(String action, String title) {
		this.connect();
		String imdbId = TMDBapi.getIMDBid(title);
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `" + this.db + "`.`Events` (username, imdbID, "
							+ "actionTitle, createTime) VALUES (?, ?, ?, ?)");
			ps.setString(1, this.loggedInUser);
			ps.setString(2, imdbId);
			ps.setString(3, action);
			ps.setTimestamp(4, MySQLDriver.getCurrentTimeStamp());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.stop();
	}

	public void changeRating(String imdbId, int rating) {
		this.connect();
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `" + this.db + "`.`Events` (username, imdbID, "
							+ "actionTitle, rating, createTime) VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, this.loggedInUser);
			ps.setString(2, imdbId);
			ps.setString(3, "rated");
			ps.setInt(4, rating);
			ps.setTimestamp(5, MySQLDriver.getCurrentTimeStamp());
			ps.execute();

			// update movies table
			ps = conn.prepareStatement("SELECT * FROM `" + this.db + "`.`Movies` WHERE imdbID=?");
			ps.setString(1, imdbId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int sum = rs.getInt(2);
				int count = rs.getInt(3);
				
				// update movie totals
				PreparedStatement psu = conn.prepareStatement("UPDATE `" + this.db
						+ "`.`Movies` SET sumRatings=? , countRatings=? WHERE imdbID=?");
				sum += rating;
				count++;
				psu.setInt(1, sum);
				psu.setInt(2, count);
				psu.setString(3, imdbId);
				psu.execute();
			} else {
				// add new movie to database
				PreparedStatement psi = conn.prepareStatement("INSERT INTO `" + this.db + "`.`Movies` (imdbID, "
						+ "sumRatings, countRatings) VALUES (?, ?, ?)");
				psi.setString(1, imdbId);
				psi.setInt(2, rating);
				psi.setInt(3, 1);
				psi.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.stop();
	}

	public Movie getMovieByTitle(String title) {
		Movie m = new Movie();
		String imdbId = TMDBapi.getIMDBid(title);
		String id = null;
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + this.db + ".Movies WHERE imdbID=?");
			ps.setString(1, imdbId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getString(1);
				m.setImdbId(id);
				long totalRating = Long.valueOf(rs.getInt(2));
				m.setRatingTotal(totalRating);
				int countRating = rs.getInt(3);
				m.setRatingCount(countRating);
			} else {
				id = TMDBapi.getIMDBid(title);
				m.setImdbId(id);
			}
			m.setActors(TMDBapi.getActorsById(id));
			m.setDescription(TMDBapi.getPlotById(id));
			m.setDirector(TMDBapi.getDirectorById(id));
			m.setGenres(TMDBapi.getGenresById(id));
			m.setImage(TMDBapi.getImageById(id));
			m.setTitle(TMDBapi.getTitleById(id));
			m.setWriters(TMDBapi.getWriterById(id));
			m.setYear(Integer.parseInt(TMDBapi.getYearById(id)));
			m.setIMDBrating(Double.parseDouble(TMDBapi.getRatingById(id)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stop();
		return m;
	}
	
	public Set<Movie> searchByTitle(String title){
		Set<Movie> movies = new HashSet<Movie>();
		List<String> movieIds = TMDBapi.searchMovieByTitle(title);
		String resultTitle;
		for(String id: movieIds){
			resultTitle = TMDBapi.getTitleById(id);
			movies.add(this.getMovieByTitle(resultTitle));
		}
		return movies;
	}
	
	public Set<Movie> searchByActor(String actor){
		Set<Movie> movies = new HashSet<Movie>();
		List<String> movieTitles = TMDBapi.searchMovieByActor(actor);
		for(String resultTitle: movieTitles){
			if(resultTitle != null && !resultTitle.equals(""))
				movies.add(this.getMovieByTitle(resultTitle));
		}
		return movies;
	}
	
	public Set<User> searchForUser(String name){
		Set<User> users = new HashSet<User>();
		try {
			this.connect();

			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + this.db + ".Users WHERE username=? OR fname=? OR lname=? OR fullname=?");
			ps.setString(1, name);
			ps.setString(2, name);
			ps.setString(3, name);
			ps.setString(4, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setFName(rs.getString(3));
				u.setLName(rs.getString(4));
				u.setImage(rs.getString(6));
				users.add(u);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	

	public List<Event> getFeedByUsername(String username, Set<String> following) {
		List<Event> feed = new ArrayList<Event>();

		// query for all events from users in following
		try {
			this.connect();

			// get query string from following list
			String query = getQueryFromFollowing(following);
			if(query != null){
				PreparedStatement ps = conn
						.prepareStatement("SELECT * FROM " + this.db + ".Events WHERE " + query + " ORDER BY createTime");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Event e = new Event();
	
					// create new movie with info from api
					Movie m = new Movie();
					this.setMovieInfo(rs.getString(3), m);
	
					// add movie to event
					e.setMovie(m);
					e.setAction(rs.getString(4));
					if (rs.getInt(5) != -1) {
						e.setRating(rs.getInt(5));
					}
					e.setUsername(rs.getString(2));
	
					Timestamp t = rs.getTimestamp(6);
					e.setTime(this.getTimeString(t));
	
					feed.add(e);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stop();
		return feed;
	}

	// function to get query string from set of following
	public String getQueryFromFollowing(Set<String> following) {
		String query = "";
		for (String s : following) {
			query += "username=\"" + s + "\" OR ";
		}
		if(query.equals(""))
			return null;
		return query.substring(0, query.length() - 4);
	}

	public String getTimeString(Timestamp t) {
		String s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(t);
		return s;
	}

	public String test() {
		return "hello from sql";
	}

	public User getUserByUsername(String username) {
		this.connect();
		User u = new User();
		u.setUsername(username);

		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + this.db + ".Users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			rs.next();
			u.setPassword(rs.getString(2));
			u.setFName(rs.getString(3));
			u.setLName(rs.getString(4));
			u.setImage(rs.getString(6));

			u.setFollowers(getFollowers(username));
			u.setFollowing(getFollowing(username));
			u.setFeed(getProfileFeed(u));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.stop();
		return u;
	}

	private List<Event> getProfileFeed(User u) {
		List<Event> feed = new ArrayList<Event>();
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM " + this.db + ".Events WHERE username=? ORDER BY createTime DESC");
			ps.setString(1, u.getUsername());
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Event e = new Event();
				e.setUsername(u.getUsername());
				String title = TMDBapi.getTitleById(result.getString(3));
				Movie m = getMovieByTitle(title);
				e.setMovie(m);
				e.setAction(result.getString(4));
				e.setRating(result.getInt(5));
				e.setTime(result.getTimestamp(6).toString());
				feed.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.stop();
		return feed;
	}
	
	//current user follows parameter user, update database
	public void addFollowing(String username){
		this.connect();
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `" + this.db + "`.`Follows` (`follower`, `following`) VALUES (?, ?)");
			ps.setString(1, this.loggedInUser);
			ps.setString(2, username);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}
	
	//current user unfollows parameter user, update database
	public void removeFollowing(String username){
		this.connect();
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM `" + this.db + "`.`Follows` WHERE follower=? AND following=?");
			ps.setString(1, this.loggedInUser);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	/* isValidLogin return true if the login is valid else false */
	public boolean isValidLogin(String username, String password) {
		this.connect();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT username, password FROM " + this.db + ".Users WHERE username=? AND password=? ");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				this.stop();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("invalid login");
		this.stop();
		return false;
	}

	/*
	 * creates new UserAccount object, insert row into database
	 */
	public void createNewUser(String username, String password, String fname, String lname, String imageURL) {
		this.connect();
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO " + this.db + ".Users (username, password, "
							+ "fname, lname, fullname, imageURL) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, fname);
			ps.setString(4, lname);
			String fullname = fname + " " + lname;
			ps.setString(5, fullname);
			ps.setString(6, imageURL);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
	}

	/*
	 * return true if username is available (it doesn't exist in the database)
	 */
	public boolean isUsernameAvailable(String username) {
		try {
			this.connect();
			PreparedStatement ps = conn
					.prepareStatement("SELECT username FROM " + this.db + ".Users WHERE BINARY username=?");
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				this.stop();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.stop();
		return true;
	}

	public void setMovieInfo(String id, Movie m) {
		m.setTitle(TMDBapi.getTitleById(id));
		m.setActors(TMDBapi.getActorsById(id));
		m.setDescription(TMDBapi.getPlotById(id));
		m.setDirector(TMDBapi.getDirectorById(id));
		m.setWriters(TMDBapi.getWriterById(id));
		m.setGenres(TMDBapi.getGenresById(id));
		m.setYear(Integer.parseInt(TMDBapi.getYearById(id)));
		m.setIMDBrating(Double.parseDouble(TMDBapi.getIMDBratingById(id)));
		m.setImage(TMDBapi.getImageById(id));
	}

	// public void updateBalance(String username, double newBalance) {
	// try {
	// PreparedStatement ps = conn
	// .prepareStatement("UPDATE `finalproject`.`users` SET `balance`=? WHERE
	// `username`=?;");
	// ps.setDouble(1, newBalance);
	// ps.setString(2, username);
	// ps.execute();
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// }

	// public void buy(Transaction trans){
	// try {
	// PreparedStatement ps = conn.prepareStatement(
	// "INSERT INTO `finalproject`.`transactions` "
	// + "(`stock_symbol`, `stock_price`, `stock_amount`, `isSell`, `timestamp`,
	// `user_id`, `isSuccess`) VALUES "
	// + "(?, ?, ?, ?, ?, ?, 1);");
	// ps.setString(1, trans.getTicker());
	// ps.setDouble(2, trans.getPrice());
	// ps.setInt(3, trans.getAmount());
	// ps.setInt(4, 0);
	// ps.setString(5, trans.getTime());
	// ps.setString(6, trans.getUser());
	//
	// ps.execute();
	//
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// public void sell(Transaction trans){
	// try {
	// PreparedStatement ps = conn.prepareStatement(
	// "INSERT INTO `finalproject`.`transactions` "
	// + "(`stock_symbol`, `stock_price`, `stock_amount`, `isSell`, `timestamp`,
	// `user_id`, `isSuccess`) VALUES "
	// + "(?, ?, ?, ?, ?, ?, 1);");
	// ps.setString(1, trans.getTicker());
	// ps.setDouble(2, trans.getPrice());
	// ps.setInt(3, trans.getAmount());
	// ps.setInt(4, 1);
	// ps.setString(5, trans.getTime());
	// ps.setString(6, trans.getUser());
	//
	// ps.execute();
	//
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	// public static void main(String[] args) {
	// MySQLDriver msql = new MySQLDriver();
	// msql.connect();
	//
	// }
	public static void main(String[] args) {

		
	}
	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
}
