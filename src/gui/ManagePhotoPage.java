package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import image.ImageMatrix;
import image.ImageSecretary;
import main.BaseLogger;
import users.Comment;
import users.Post;
import users.User;
import users.UserManager;

/**
 * Lets the user manage their post. Allows setting the post public or private,
 * deleting the post, or modifying it to make a new post
 * 
 * @author Yusuf
 *
 */
public class ManagePhotoPage extends JFrame {

	private ImageMatrix image;
	private UserManager userManager;

	/**
	 * Contructs a new ManagePhotoPage object
	 * 
	 * @param user Owner of the post
	 * @param post Post to be managed
	 */
	public ManagePhotoPage(User user, Post post) {
		setLayout(new BorderLayout());
		setSize(950, 750);
		setLocationRelativeTo(null);

		JLabel imageLabel;
		JPanel buttonPanel;
		JButton privateButton;
		JButton publicButton;
		JButton deleteButton;
		JButton modifyButton;

		userManager = new UserManager();

		// Display image
		image = new ImageMatrix(800, 600);
		try {
			image = ImageSecretary.readResourceImage(post.getImageName());
			BaseLogger.info().log("User " + user.getNickname() + " opened file " + post.getImageName());
		} catch (IOException e) {
			BaseLogger.error().log("User " + user.getNickname() + " failed to open file " + post.getImageName());
		}
		ImageIcon fullImage = new ImageIcon(image.getBufferedImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		imageLabel = new JLabel(fullImage);

		// Set up buttons
		buttonPanel = new JPanel();
		privateButton = new JButton("Set private");
		publicButton = new JButton("Set public");
		deleteButton = new JButton("Delete");
		modifyButton = new JButton("Modify photo");

		// Private Button
		privateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userManager.getPostMap().get(post.getId()).setPublic(false);
				userManager.updatePosts();
				JOptionPane.showMessageDialog(null, "Post set to private");
				BaseLogger.info().log("User " + user.getNickname() + " set post " + post.getId() + " to private");
			}
		});
		// Public Button
		publicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userManager.getPostMap().get(post.getId()).setPublic(true);
				userManager.updatePosts();
				JOptionPane.showMessageDialog(null, "Post set to public");
				BaseLogger.info().log("User " + user.getNickname() + " set post " + post.getId() + " to public");
			}
		});
		// Delete Button
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userManager.deletePost(post);
				userManager.updatePosts();
				userManager.readPosts();
				Comment.deleteComments(post);
				Comment.writeComments();
				setVisible(false);
				BaseLogger.info().log("User " + user.getNickname() + " deleted post " + post.getId());
				dispose();
			}
		});
		// Modify Button
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UploadImagePage uploadImagePage = new UploadImagePage(user, image);
				uploadImagePage.setVisible(true);
				setVisible(false);
				dispose();
			}
		});

		// Add all components
		buttonPanel.add(publicButton);
		buttonPanel.add(privateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(modifyButton);
		getContentPane().add(imageLabel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.NORTH);
	}
}
