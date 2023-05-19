package users;

import java.awt.image.BufferedImage;
import java.io.IOException;

import image.ImageMatrix;
import image.ImageSecretary;

public class User {
	private String nickname; // unique nickname
	private String password;
	private String name;
	private String surname;
	private int age;
	private String email; // unique email
	private String profilePhoto; // optional pfp
	private UserTier userTier;

	public User(String nickname, String password, String name, String surname, int age, String email,
			String profilePhoto, UserTier userTier) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = profilePhoto;
		this.userTier = userTier;
	}

	public User(String nickname, String password, String name, String surname, int age, String email,
			UserTier userTier) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.profilePhoto = "default_user.png";
		this.userTier = userTier;
	}

	public boolean equals(User other) {
		return nickname.equals(other.getNickname());
	}

	public int getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	// getters and setters
	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public String getSurname() {
		return surname;
	}

	public UserTier getUserTier() {
		return userTier;
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

	public BufferedImage getProfilePhotoImage() {
		BufferedImage profilePhoto = null;
		try {
			ImageMatrix profilePhotoImage = ImageSecretary.readResourceImage(getProfilePhoto());
			profilePhoto = profilePhotoImage.getBufferedImage();
		} catch (IOException e) {
			System.err.println("Photo " + getProfilePhoto() + " not found");
		}
		return profilePhoto;
	}

	@Override
	public String toString() {
		return super.toString() + "User [nickname=" + nickname + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", age=" + age + ", email=" + email + ", profilePhoto=" + profilePhoto + "]";
	}
}
