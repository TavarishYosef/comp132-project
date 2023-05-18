package users;

import image.ImageMatrix;

@SuppressWarnings("unused")
public class User {
	private String nickname; // unique nickname
	private String password;
	private String name;
	private String surname;
	private int age;
	private String email; // unique email
	private String profilePhoto; // optional pfp

	public User(String nickname, String password, String name, String surname, int age, String email) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = "default_user";
	}

	public User(String nickname, String password, String name, String surname, int age, String email,
			String profilePhoto) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = profilePhoto;
	}

	// getters and setters
	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setAge(int age) {
		this.age = Math.max(age, 0);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
