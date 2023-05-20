package users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class handles various operations for {@link User} and {@link Post}
 * objects
 * 
 * @author Yusuf
 *
 */
public class UserManager {
	private static HashMap<String, User> users = new HashMap<>();
	private static ArrayList<String> nicknames = new ArrayList<>();
	private static ArrayList<String> emails = new ArrayList<>();
	private static HashMap<Integer, Post> posts = new HashMap<>();

	/**
	 * Constructs a new {@link UserManager} object reads info from users.txt and
	 * posts.txt files
	 */
	public UserManager() {
		// Get users
		try (Scanner scanner = new Scanner(new File("users.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				String nickname = parts[0];
				String password = parts[1];
				String name = parts[2];
				String surname = parts[3];
				int age = Integer.parseInt(parts[4]);
				String email = parts[5];
				String profilePhoto = parts[6];
				String tier = parts[7].strip();
				UserTier userTier = UserTier.FREE;
				if (tier.equals("HOBBYIST")) {
					userTier = UserTier.HOBBYIST;
				} else if (tier.equals("PROFESSIONAL")) {
					userTier = UserTier.PROFESSIONAL;
				} else if (tier.equals("ADMIN")) {
					userTier = UserTier.ADMIN;
				}
				addUser(new User(nickname, password, name, surname, age, email, profilePhoto, userTier));
			}
		} catch (FileNotFoundException err) {
			System.err.println("Error reading user file");
		}
		// Get posts
		try (Scanner scanner = new Scanner(new File("posts.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				int id = Integer.parseInt(parts[0]);
				String status = parts[1];
				String imageName = parts[2];
				String nickname = parts[3];
				String description = parts[4].strip();
				
				Post post = new Post(id, imageName, getUser(nickname), description);
				
				if (status.equals("public"))
					post.setPublic(true);
				posts.put(id, post);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error reading posts.txt");
		}
	}

	/**
	 * Reads user information from users.txt
	 */
	public void readUsers() {
		try (Scanner scanner = new Scanner(new File("users.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				String nickname = parts[0];
				String password = parts[1];
				String name = parts[2];
				String surname = parts[3];
				int age = Integer.parseInt(parts[4]);
				String email = parts[5];
				String profilePhoto = parts[6];
				String tier = parts[7].strip();
				UserTier userTier = UserTier.FREE;
				if (tier.equals("HOBBYIST")) {
					userTier = UserTier.HOBBYIST;
				} else if (tier.equals("PROFESSIONAL")) {
					userTier = UserTier.PROFESSIONAL;
				} else if (tier.equals("ADMIN")) {
					userTier = UserTier.ADMIN;
				}
				addUser(new User(nickname, password, name, surname, age, email, profilePhoto, userTier));
			}
		} catch (FileNotFoundException err) {
			System.err.println("Error reading user file");
		}
	}

	/**
	 * Reads post information from posts.txt
	 */
	public void readPosts() {
		try (Scanner scanner = new Scanner(new File("posts.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				int id = Integer.parseInt(parts[0]);
				String status = parts[1];
				String imageName = parts[2];
				String nickname = parts[3];
				String description = parts[4].strip();
				
				Post post = new Post(id, imageName, getUser(nickname), description);
				if (status.equals("public"))
					post.setPublic(true);
				posts.put(id, post);
			}
		} catch (Exception e) {
			System.err.println("Error reading posts.txt");
		}
	}

	/**
	 * Checks if id is taken
	 * 
	 * @param id ID of the new post
	 * @return true if ID is free
	 */
	public boolean isValid(int id) {
		return !posts.keySet().contains(id);
	}

	/**
	 * Adds the post to the Post Map
	 * 
	 * @param id   ID of the new Post
	 * @param post the new {@link Post} object
	 */
	public void addPost(int id, Post post) {
		posts.put(id, post);
	}

	/**
	 * Adds a new user Updates user, nickname and email lists
	 * 
	 * @param user the new {@link User} object
	 */
	public void addUser(User user) {
		String nickname = user.getNickname();
		String email = user.getEmail();

		users.put(nickname.toLowerCase(), user);
		nicknames.add(nickname.toLowerCase());
		emails.add(email.toLowerCase());
	}

	/**
	 * Checks if email is available
	 * 
	 * @param email email of new user
	 * @return true if email is free
	 */
	public boolean checkEmail(String email) {
		return !emails.contains(email.toLowerCase());
	}

	/**
	 * Checks if nickname is available
	 * 
	 * @param nickname nickname of new user
	 * @return true if nickname is available
	 */
	public boolean checkNickname(String nickname) {
		return !nicknames.contains(nickname.toLowerCase());
	}

	/**
	 * Removes the {@link Post} object from the Post Map
	 * 
	 * @param post Post to be removed
	 */
	public void deletePost(Post post) {
		posts.remove(post.getId());
	}

	/**
	 * @return The map of the posts
	 */
	public HashMap<Integer, Post> getPostMap() {
		return posts;
	}

	/**
	 * Returns an ArrayList of posts made by the user
	 * 
	 * @param user
	 * @return ArrayList of {@link Post} objects
	 */
	public ArrayList<Post> getPostsByUser(User user) {
		ArrayList<Post> result = new ArrayList<>();
		for (Post post : posts.values()) {
			if (post.getPoster().equals(user)) {
				result.add(post);
			}
		}
		return result;
	}

	/**
	 * Finds user by nickname
	 * 
	 * @param nickname Nickname of the user
	 * @return {@link User} object
	 */
	public User getUser(String nickname) {
		return users.get(nickname.toLowerCase());
	}

	/**
	 * 
	 * @return HashMap of nicknames to Users
	 */
	public HashMap<String, User> getUserMap() {
		return users;
	}

	/**
	 * Removes the user from users ArrayList updates nickname and mail lists
	 * 
	 * @param user User to be removed
	 */
	public void removeUser(User user) {
		String nickname = user.getNickname().toLowerCase();
		users.remove(nickname);
		nicknames.remove(nickname);
		emails.remove(user.getEmail().toLowerCase());
	}

	/**
	 * Rewrites the posts.txt file
	 */
	public void updatePosts() {
		new File("posts.txt").delete();
		try (FileWriter writer = new FileWriter("posts.txt", true)) {
			for (Post post : posts.values()) {
				int id = post.getId();
				String status = post.isPublic() ? "public" : "private";
				String imageName = post.getImageName();
				String poster = post.getPoster().getNickname();
				String description = post.getDescription();
				
				writer.write(id + "," + status + "," + imageName + "," + poster + "," + description + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error writing post");
		}

	}

	/**
	 * Rewrites the users.txt file
	 */
	public void updateUsers() {
		new File("users.txt").delete();
		for (User user : users.values()) {
			String nickname = user.getNickname();
			String password = user.getPassword();
			String name = user.getName();
			String surname = user.getSurname();
			int age = user.getAge();
			String email = user.getEmail();
			String profilePhoto = user.getProfilePhoto();
			UserTier tier = user.getUserTier();

			try (FileWriter writer = new FileWriter("users.txt", true)) {
				writer.write(nickname + "," + password + "," + name + "," + surname + "," + age + "," + email + ","
						+ profilePhoto + "," + tier + "\n");
			} catch (IOException err) {
				System.err.println("Error saving user to users.txt");
				err.printStackTrace();
			}
		}
	}

	/**
	 * Validates user credentials for logging in
	 * 
	 * @param nickname Nickname field
	 * @param password Password filed
	 * @return true if access is granted
	 */
	public boolean validateUser(String nickname, String password) {
		for (User user : users.values()) {
			if (user.getNickname().toLowerCase().equals(nickname.toLowerCase())
					&& user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Writes the post to the posts.txt file
	 * 
	 * @param post {@link Post} to be added
	 */
	public void writePost(Post post) {
		try (FileWriter writer = new FileWriter("posts.txt", true)) {
			int id = post.getId();
			String status = post.isPublic() ? "public" : "private";
			String imageName = post.getImageName();
			String poster = post.getPoster().getNickname();
			String description = post.getDescription();
			writer.write(id + "," + status + "," + imageName + "," + poster + "," + description + ",:\n");
		} catch (IOException err) {
			System.err.println("Error saving post to posts.txt");
			err.printStackTrace();
		}
	}

	/**
	 * Writes the user to the users.txt file
	 * 
	 * @param user {@link User} to be added
	 */
	public void writeUser(User user) {
		String nickname = user.getNickname();
		String password = user.getPassword();
		String name = user.getName();
		String surname = user.getSurname();
		int age = user.getAge();
		String email = user.getEmail();
		String profilePhoto = user.getProfilePhoto();
		UserTier tier = user.getUserTier();

		try (FileWriter writer = new FileWriter("users.txt", true)) {
			writer.write(nickname.toLowerCase() + "," + password + "," + name + "," + surname + "," + age + "," + email
					+ "," + profilePhoto + "," + tier + "\n");
		} catch (IOException err) {
			System.err.println("Error saving user to users.txt");
			err.printStackTrace();
		}

	}
}
