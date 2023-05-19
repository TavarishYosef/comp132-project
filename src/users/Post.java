package users;

import java.util.ArrayList;

public class Post {
	private String imageName;
	private User poster;
	private boolean isPublic;
	private String description;
	private int id;

	public Post(int id, String imageName, User poster) {
		this.isPublic = false;
		this.imageName = imageName;
		this.poster = poster;
		this.description = "No description.";
		this.id = id;
	}

	public Post(int id, String imageName, User poster, String description) {
		this.isPublic = false;
		this.imageName = imageName;
		this.poster = poster;
		this.description = description;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getImageName() {
		return imageName;
	}

	public User getPoster() {
		return poster;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
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
}
