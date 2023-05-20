package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import image.ImageMatrix;
import image.ImageSecretary;
import main.BaseLogger;
import users.Comment;
import users.Post;
import users.User;

/**
 * PhotoInteraction opens a new frame to show the photo in full size and
 * displays poster's profile photo, name and shows the description and comments.
 * Users can also add comments to the photo.
 * 
 * @author Yusuf
 *
 */
public class PhotoInteraction extends JFrame {
	private JTextArea commentsArea;
	/**
	 * Creates a new {@link PhotoInteraction} object
	 * @param post {@link Post} to be displayed
	 * @param user Current {@link User} of the session
	 */
	public PhotoInteraction(Post post, User user) {
		// Set up the frame
		setTitle("Photo Interaction");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setLocationRelativeTo(null);
		User poster = post.getPoster();

		// Create new JPanel
		JPanel panel = new JPanel(new BorderLayout());

		// Display image
		ImageMatrix image = new ImageMatrix(800, 600);
		try {
			image = ImageSecretary.readResourceImage(post.getImageName());
		} catch (IOException e) {
			BaseLogger.error().log("Failed to open file" + post.getImageName());
		}
		ImageIcon fullImage = new ImageIcon(image.getBufferedImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		JLabel fullImageLabel = new JLabel(fullImage);
		panel.add(fullImageLabel, BorderLayout.CENTER);

		// Display profile photo, nickname, post description and likes
		ImageIcon profilePhoto = new ImageIcon();
		try {
			profilePhoto = new ImageIcon(ImageSecretary.readResourceImage(poster.getProfilePhoto()).getBufferedImage()
					.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			profilePhoto = new ImageIcon();
		}
		JLabel profilePhotoLabel = new JLabel(profilePhoto);
		JLabel nicknameLabel = new JLabel(poster.getNickname());
		JTextArea descriptionTextArea = new JTextArea(post.getDescription() + "\n\n\nLikes: " + post.getLikes());
		descriptionTextArea.setEditable(false);

		JPanel userInfoPanel = new JPanel();
		userInfoPanel.add(profilePhotoLabel);
		userInfoPanel.add(nicknameLabel);
		panel.add(userInfoPanel, BorderLayout.NORTH);
		panel.add(descriptionTextArea, BorderLayout.WEST);

		// Display comments section
		commentsArea = new JTextArea();
		commentsArea.setEditable(false);
		Comment.readComments();
		commentsArea.setText(post.getComments());

		JScrollPane commentsScrollPane = new JScrollPane(commentsArea);
		panel.add(commentsScrollPane, BorderLayout.EAST);

		// Add comment input field and button
		JPanel commentPanel = new JPanel(new BorderLayout());
		JTextField commentField = new JTextField();
		JButton commentButton = new JButton("Add Comment");
		JButton likeButton = new JButton();
		if (post.isLikedBy(user)) {
			likeButton.setText("Unlike");
		} else {
			likeButton.setText("Like");
		}
		// Comment Button Action
		commentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String commentText = commentField.getText();
				if (!commentText.isEmpty()) {
					Comment comment = new Comment(user, commentText, post);
					comment.addComment();
					comment.write();
					commentsArea.setText(post.getComments());
					commentField.setText("");
					commentsArea.setText(post.getComments());
					setSize(1000, 801);
					setSize(1000, 800);
					BaseLogger.info().log("User " + user.getNickname() + " left a comment on post " + post.getId());
				}
			}
		});
		// Like Button Action
		likeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (post.isLikedBy(user)) {
					post.removeLike(user);
					likeButton.setText("Like");
					BaseLogger.info().log("User " + user.getNickname() + " liked post " + post.getId());
				} else {
					post.addLike(user);
					likeButton.setText("Unlike");
					BaseLogger.info().log("User " + user.getNickname() + " unliked post " + post.getId());
				}
				
				descriptionTextArea.setText(post.getDescription() + "\n\n\nLikes: " + post.getLikes());
			}
		});
		commentPanel.add(likeButton, BorderLayout.WEST);
		commentPanel.add(commentField, BorderLayout.CENTER);
		commentPanel.add(commentButton, BorderLayout.EAST);
		panel.add(commentPanel, BorderLayout.SOUTH);

		getContentPane().add(panel);
	}

}
