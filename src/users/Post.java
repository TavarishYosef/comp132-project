package users;

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
}
