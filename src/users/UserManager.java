package users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {
	private static HashMap<String, User> users = new HashMap<>();
	private static ArrayList<String> nicknames = new ArrayList<>();
	private static ArrayList<String> emails = new ArrayList<>();
	private static HashMap<String, String> posts = new HashMap<>();

	public UserManager() {
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
			err.printStackTrace();
		}
		try (Scanner scanner = new Scanner(new File("posts.txt"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				String post = parts[0];
				String nickname = parts[1];
				posts.put(post, nickname);
			}
		} catch (Exception e) {
		}
	}

	public void addUser(User user) {
		String nickname = user.getNickname();
		String email = user.getEmail();

		users.put(nickname.toLowerCase(), user);
		nicknames.add(nickname.toLowerCase());
		emails.add(email.toLowerCase());
	}

	public boolean checkEmail(String email) {
		return !emails.contains(email.toLowerCase());
	}

	public boolean checkNickname(String nickname) {
		return !nicknames.contains(nickname.toLowerCase());
	}

	public String getOP(String postName) {
		return posts.get(postName);
	}

	public User getUser(String nickname) {
		return users.get(nickname.toLowerCase());
	}

	public void addPost(String fileName, String nickName) {
		posts.put(fileName, nickName);
	}

	public void writePost(String fileName, String nickName) {
		try (FileWriter writer = new FileWriter("posts.txt", true)) {
			writer.write(fileName + "," + nickName + "\n");
		} catch (IOException err) {
			System.err.println("Error saving user to users.txt");
			err.printStackTrace();
		}
	}

	public HashMap<String, User> getUserMap() {
		return users;
	}

	public HashMap<String, String> getPostMap() {
		return posts;
	}

	public boolean validateUser(String nickname, String password) {
		for (User user : users.values()) {
			if (user.getNickname().toLowerCase().equals(nickname.toLowerCase())
					&& user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

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
				writer.write(nickname.toLowerCase() + "," + password + "," + name + "," + surname + "," + age + ","
						+ email + "," + profilePhoto + "," + tier + "\n");
			} catch (IOException err) {
				System.err.println("Error saving user to users.txt");
				err.printStackTrace();
			}
		}
	}

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
