package users;

import java.awt.image.BufferedImage;
import java.io.IOException;

import image.ImageMatrix;
import image.ImageSecretary;

/**
 * A user of the PhotoCloud application
 * 
 * @author Yusuf
 *
 */
public class User {
	private String nickname; // unique nickname
	private String password;
	private String name;
	private String surname;
	private int age;
	private String email; // unique email
	private String profilePhoto; // optional pfp
	private UserTier userTier;

	/**
	 * Constructs a new User object
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param profilePhoto
	 * @param userTier
	 */
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

	/**
	 * Constructs a new User object
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param userTier
	 */
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
	/**
	 * Equals method for the User class
	 * @param other the other user to compare with this user
	 * @return true if they have the same nickname
	 */
	public boolean equals(User other) {
		return nickname.equals(other.getNickname());
	}

	// getters and setters
	public int getAge() {
		return age;
	}
	/**
	 * Returns the user's email adress
	 * @return the user's email adress
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Returns the user's name
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns the user's nickname
	 * @return the user's nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Returns the user's pasword
	 * @return the user's pasword
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Returns the user's profile photo's name
	 * @return the user's profile photo's name
	 */
	public String getProfilePhoto() {
		return profilePhoto;
	}
	/**
	 * Returns the user's profile photo as a BufferedImage
	 * @return the user's profile photo as a BufferedImage
	 */
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
	/**
	 * Returns the user's surname
	 * @return the user's surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * Returns the user's tier
	 * @return the user's tier
	 */
	public UserTier getUserTier() {
		return userTier;
	}
	/**
	 * Sets the user's age to given value
	 * @param age
	 */
	public void setAge(int age) {
		this.age = Math.max(age, 0);
	}
	/**
	 * Sets the user's email adress to given value
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Sets the user's name to given value
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Sets the user's password to given value
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Sets the user's profile photo to given value
	 * @param profilePhoto
	 */
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	/**
	 * Sets the user's surname to given value
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
