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
				String email = parts[5].strip();

				addUser(new User(nickname, password, name, surname, age, email));
			}
		} catch (FileNotFoundException err) {
			System.err.println("Error reading user file");
			err.printStackTrace();
		}
	}

	public HashMap<String, User> getUserMap() {
		return users;
	}

	public void writeUser(User user) {
		String nickname = user.getNickname();
		String password = user.getPassword();
		String name = user.getName();
		String surname = user.getSurname();
		int age = user.getAge();
		String email = user.getEmail();
		
		try (FileWriter writer = new FileWriter("users.txt", true)) {
			writer.write(nickname.toLowerCase() + "," + password + "," + name + "," + surname + "," + age + "," + email + "\n");
		} catch (IOException err) {
			System.err.println("Error saving user to users.txt");
			err.printStackTrace();
		}

	}
	public void addUser(User user) {
		String nickname = user.getNickname();
		String email = user.getEmail();

		users.put(nickname.toLowerCase(), user);
		nicknames.add(nickname.toLowerCase());
		emails.add(email.toLowerCase());
	}

	public User getUser(String nickname) {
		return users.get(nickname);
	}

	public boolean checkEmail(String email) {
		return !emails.contains(email.toLowerCase());
	}

	public boolean checkNickname(String nickname) {
		return !nicknames.contains(nickname.toLowerCase());
	}

	public boolean validateUser(String nickname, String password) {
		for (User user : users.values()) {
			if (user.getNickname().toLowerCase().equals(nickname.toLowerCase()) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}