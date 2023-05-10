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
	private ImageMatrix profilePhoto; // optional pfp

	public User(String nickname, String password, String name, String surname, int age, String email,
			ImageMatrix profilePhoto) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = profilePhoto;
	}

	public User(String nickname, String password, String name, String surname, int age, String email) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = new ImageMatrix(320, 320);
	}

	// getters and setters
	public String getNickname() {
		return nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAge(int age) {
		this.age = Math.max(age, 0);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProfilePhoto(ImageMatrix profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

}
