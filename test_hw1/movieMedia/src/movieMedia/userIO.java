package movieMedia;

import java.util.ArrayList;
import java.util.Scanner;

public class userIO {
	public static void main(String[] args) {
		User currentUser = new User();
		System.out.println(args[0]);
		xmlParser fileInfo = new xmlParser(args[0]);

		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\\n");
		boolean loginState = true;
		int menuState = 0;

		while (loginState) {
			while (menuState == 0) {
				System.out.println("Welcome to cineMate! What would you like to do?");
				System.out.println("1. Login");
				System.out.println("2. Exit");

				String temp = scan.next();

				if (temp.equals("1")) {
					System.out.println("Please enter your username");
					for (int j = 3; j > 0; j--) {
						String username = scan.next();
						if (fileInfo.users.containsKey(username.toLowerCase())) {
							j = 0;
							for (int i = 3; i > 0; i--) {
								for (int h = 0; h < fileInfo.users.get(username.toLowerCase()).size(); h++) {
									if (fileInfo.users.get(username.toLowerCase()).get(h).getUsername()
											.equals(username)) {
										System.out.println("Please enter your password");
										String enteredPW = scan.next();
										if (fileInfo.users.get(username.toLowerCase()).get(h).getPassword()
												.equals(enteredPW)) {
											i = 0;
											menuState = 1;
											currentUser = fileInfo.users.get(username.toLowerCase()).get(h);
										}
									}

								}
								if (menuState == 0) {
									if (i == 3) {
										System.out.println("Incorrect password. " + "You have 2 more chances to "
												+ "enter the correct password.");
									} else if (i == 2) {
										System.out.println("Incorrect password. " + "You have 1 more chance to "
												+ "enter the correct password.");
									}
								}

							}
						} else if (j == 3) {
							System.out.println(
									"Invalid username. You have 2 " + "more chances to enter a valid username.");
						} else if (j == 2) {
							System.out.println(
									"Invalid username. You have 1 " + "more chance to enter a valid username.");
						}
					}
				} else if (temp.equals("2")) {
					System.exit(0);
				} else {
					System.out.println("You have entered an invalid command, please try again.");
				}
			}
			while (menuState == 1) {
				System.out.println("1. Search Users");
				System.out.println("2. Search Movies");
				System.out.println("3. View Feed");
				System.out.println("4. View Profile");
				System.out.println("5. Logout");
				System.out.println("6. Exit");

				String choice = scan.next();
				switch (choice) {
				case "1": // search users
					boolean searching = true;
					while (searching) {
						System.out.println("Enter the username you'd like to search.");
						String searchFor = scan.next();
						if (fileInfo.users.containsKey(searchFor.toLowerCase())) {
							if (fileInfo.users.get(searchFor).size() == 1) {
								System.out.println("1 result");
								System.out.println(searchFor);
							} else {
								System.out.println(fileInfo.users.get(searchFor).size() + " results");
								for (int i = 0; i < fileInfo.users.get(searchFor).size(); i++) {
									System.out.println(fileInfo.users.get(searchFor).get(i));
								}
							}
						} else {
							System.out.println("0 results");
						}
						System.out.println("Please choose from the following options:");
						System.out.println("1. Back to Login Menu");
						System.out.println("2. Search Again");
						String afterChoice = scan.next();
						if (afterChoice.equals("1")) {
							searching = false;
						} else if (!(afterChoice.equals("2"))) {
							System.out.println("You have entered an invalid command, please try again.");
						}
					}
					break;
				case "2": // search movies
					boolean searchMovie = true;
					while (searchMovie) {
						System.out.println("1. Search by Actor");
						System.out.println("2. Search by Title");
						System.out.println("3. Search by Genre");
						System.out.println("4. Back to Login Menu");

						String searchBy = scan.next();
						switch (searchBy) {
						case "1":
							System.out.println("Please enter the name of the actor you wish to search by.");
							String actorName = scan.next();
							ArrayList<Movie> moviesByActor = new ArrayList<Movie>();
							for (int i = 0; i < fileInfo.movies.size(); i++) {
								ArrayList<String> movieActors = fileInfo.movies.get(i).getActors();

								for (int j = 0; j < movieActors.size(); j++) {
									if (movieActors.get(j).toLowerCase().equals(actorName.toLowerCase())) {
										moviesByActor.add(fileInfo.movies.get(i));
										break;
									}
								}
							}
							if (moviesByActor.size() == 1) {
								System.out.println("1 result");
								System.out.println(moviesByActor.get(0).getTitle());
							} else {
								System.out.println(moviesByActor.size() + " results");
								for (Movie i : moviesByActor) {
									System.out.println(i.getTitle());
								}
							}
							break;
						case "2":
							System.out.println("Please enter the name of" + " the movie title you wish to search by.");
							String movieName = scan.next();
							ArrayList<Movie> moviesByTitle = new ArrayList<Movie>();
							for (int i = 0; i < fileInfo.movies.size(); i++) {
								if (fileInfo.movies.get(i).getTitle().toLowerCase().equals(movieName.toLowerCase())) {
									moviesByTitle.add(fileInfo.movies.get(i));
								}
							}
							if (moviesByTitle.size() == 1) {
								System.out.println("1 result");
								System.out.println(moviesByTitle.get(0).getTitle());
							} else {
								System.out.println(moviesByTitle.size() + " results");
								for (Movie i : moviesByTitle) {
									System.out.println(i.getTitle());
								}
							}
							break;
						case "3":
							System.out.println("Please enter the name of" + " the genre you wish to search by.");
							String genreName = scan.next();
							ArrayList<Movie> moviesByGenre = new ArrayList<Movie>();
							for (int i = 0; i < fileInfo.movies.size(); i++) {
								if (fileInfo.movies.get(i).getGenre().toLowerCase().equals(genreName.toLowerCase())) {
									moviesByGenre.add(fileInfo.movies.get(i));
								}
							}
							if (moviesByGenre.size() == 1) {
								System.out.println("1 result");
								System.out.println(moviesByGenre.get(0).getTitle());
							} else {
								System.out.println(moviesByGenre.size() + " results");
								for (Movie i : moviesByGenre) {
									System.out.println(i.getTitle());
								}
							}
							break;
						case "4":
							searchMovie = false;
							break;
						default:
							System.out.println("You have entered an invalid command, please try again.");
							break;
						}
						while (true && searchMovie) {
							System.out.println("Please choose from the following options:");
							System.out.println("1. Back to Login Menu");
							System.out.println("2. Back to Search Movies Menu");
							String postChoice = scan.next();
							if (postChoice.equals("1")) {
								searchMovie = false;
							} else if (postChoice.equals("2")) {
								break;
							} else {
								System.out.println("You have entered an invalid command, please try again.");
							}
						}
					}
					break;

				case "3": // view feed
					currentUser.printFeed(fileInfo.users);

					System.out.println("To go back to the login menu, please type ‘0’");
					boolean decision = true;
					while (decision) {
						String response = scan.next();
						if (response.equals("0")) {
							decision = false;
						} else {
							System.out.print("Invalid command. To go back to the login menu, please type ‘0’");
						}
					}
					break;
				case "4": // view profile
					currentUser.printInfo();
					System.out.println("To go back to the login menu, please type ‘0’");
					while (true) {
						String response = scan.next();
						if (response.equals("0")) {
							break;
						} else {
							System.out.print("Invalid command. To go back to the login menu, please type ‘0’");
						}
					}
					break;

				case "5": // logout
					menuState = 0;
					break;
				case "6": // exit
					System.exit(0);
					break;
				default: // invalid response
					System.out.println("You have entered an invalid command, please try again.");
					break;
				}
			}
		}
	}
}