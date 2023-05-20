package users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Post class is for interacting with posts made by users
 * 
 * @author Yusuf
 *
 */
public class Post {
	private String imageName;
	private User poster;
	private boolean isPublic;
	private String description;
	private int id;
	private int likeCount;
	private Set<User> likedUsers;

	/**
	 * Constructs a new Post object
	 * 
	 * @param id        Unique ID of the post
	 * @param imageName Name of the image file
	 * @param poster    User who posted the post
	 */
	public Post(int id, String imageName, User poster) {
		this.isPublic = false;
		this.imageName = imageName;
		this.poster = poster;
		this.description = "No description.";
		this.id = id;
		this.likeCount = 0;
		this.likedUsers = new HashSet<>();
	}

	/**
	 * Constructs a new Post object
	 * 
	 * @param id          Unique ID of the post
	 * @param imageName   Name of the image file
	 * @param poster      User who posted the post
	 * @param description Description on the post
	 */
	public Post(int id, String imageName, User poster, String description) {
		this.isPublic = false;
		this.imageName = imageName;
		this.poster = poster;
		this.description = description;
		this.id = id;
		this.likeCount = 0;
		this.likedUsers = new HashSet<>();
	}

	/**
	 * @return Description on the post
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Unique ID of the post
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return Name of the image file
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @return User who posted the post
	 */
	public User getPoster() {
		return poster;
	}

	/**
	 * @return true if the post is public
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * 
	 * @return Like count of the post
	 */
	public int getLikes() {
		return likeCount;
	}
	public Set<User> getLikeSet() {
		return likedUsers;
	}

	/**
	 * Sets the post public or private
	 * 
	 * @param isPublic true if post is to be set public
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return All comments on the post as a string
	 */
	public String getComments() {
		String result = "";
		ArrayList<Comment> comments = Comment.getCommentList();
		for (Comment comment : comments) {
			if (comment.getPost().getId() == id) {
				result += comment.getUser().getNickname() + ": " + comment.getText() + "\n\n";
			}
		}
		return result;
	}

	/**
	 * @param user User to check if they liked the post
	 * @return true if they liked the post
	 */
	public boolean isLikedBy(User user) {
		return likedUsers.contains(user);
	}

	/**
	 * Adds a like to the post and incremens like count
	 * 
	 * @param user User who liked the post
	 */
	public void addLike(User user) {
		likedUsers.add(user);
		likeCount++;
	}

	/**
	 * Removes the like by the user
	 * 
	 * @param user User who unliked the post
	 */
	public void removeLike(User user) {
		likedUsers.remove(user);
		likeCount--;
	}
}
