package users;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Comments class is used for interagting with comments left by user on posts
 * 
 * @author Yusuf
 *
 */
public class Comment {

	private static ArrayList<Comment> commentList = new ArrayList<>();
	private User user;
	private String text;
	private Post post;

	/**
	 * Constructs a new Comment object
	 * 
	 * @param user User who left the comment
	 * @param text Commented text
	 * @param post Post which the comment is on
	 */
	public Comment(User user, String text, Post post) {
		this.user = user;
		this.text = text;
		this.post = post;
	}

	/**
	 * Adds the comment to the list
	 */
	public void addComment() {
		commentList.add(this);
	}

	/**
	 * Writes comments in commentList to comments.txt
	 */
	public static void writeComments() {
		try (FileWriter writer = new FileWriter("comments.txt", false)) {
			for (Comment comment : commentList) {
				User user = comment.getUser();
				String text = comment.getText();
				Post post = comment.getPost();
				writer.write(user.getNickname() + "," + text + "," + post.getId() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error writing comment");
		}
	}

	/**
	 * Writes the comment information on comments.txt
	 */
	public void write() {
		try (FileWriter writer = new FileWriter("comments.txt", true)) {
			writer.write(user.getNickname() + "," + text + "," + post.getId() + "\n");
		} catch (IOException e) {
			System.err.println("Error writing comment");
		}
	}

	/**
	 * Removes comments of a post from commentList
	 * 
	 * @param post Post to clear comments on
	 */
	public static void deleteComments(Post post) {
		ArrayList<Comment> copyList = new ArrayList<Comment>(commentList);
		for (Comment comment : copyList) {
			if (comment.getPost().getId() == post.getId()) {
				commentList.remove(comment);
			}
		}
	}

	/**
	 * Reads comments from comments.txt
	 */
	public static void readComments() {
		commentList.clear();
		UserManager userManager = new UserManager();
		try (Scanner input = new Scanner(new File("comments.txt"))) {
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] parts = line.split(",");
				String userName = parts[0];
				String text = parts[1];
				int postId = Integer.parseInt(parts[2]);
				new Comment(userManager.getUser(userName), text, userManager.getPostMap().get(postId)).addComment();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return User who posted the comment
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return List of all comments
	 */
	public static ArrayList<Comment> getCommentList() {
		return commentList;
	}

	/**
	 * @return the Post which the comment is on
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * @return the commented text
	 */
	public String getText() {
		return text;
	}

}
