package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import image.ImageMatrix;
import image.ImageSecretary;
import users.Post;
import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class PublicProfilePage extends JPanel {

	private User user;
	private UserManager userManager;
	private JPanel postsPanel;

	public PublicProfilePage(User user) {
		this.user = user;
		this.userManager = new UserManager();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		// Gridlayout userInfoPanel houses publicInfoPanel and postsPanel
		JPanel userInfoPanel = new JPanel(new GridLayout(2, 1));
		userInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Display the user's profile photo
		ImageIcon profilePhoto = getUserProfilePhoto(user);
		JLabel profilePhotoLabel = new JLabel(profilePhoto);
		userInfoPanel.add(profilePhotoLabel);

		// Display user's public information
		JPanel publicInfoPanel = new JPanel(new GridLayout(4, 1));
		JLabel nameLabel = new JLabel("Name: " + user.getName());
		JLabel surnameLabel = new JLabel("Surname: " + user.getSurname());
		JLabel nicknameLabel = new JLabel("Nickname: " + user.getNickname());
		JLabel ageLabel = new JLabel("Age: " + user.getAge());
		publicInfoPanel.add(nameLabel);
		publicInfoPanel.add(surnameLabel);
		publicInfoPanel.add(nicknameLabel);
		publicInfoPanel.add(ageLabel);
		userInfoPanel.add(publicInfoPanel);
		add(userInfoPanel, BorderLayout.NORTH);

		// Put posts in 4 column grid layout
		postsPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		JScrollPane scrollPane = new JScrollPane(postsPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane, BorderLayout.CENTER);

		// Get the user's posts
		ArrayList<Post> posts = userManager.getPostsByUser(user);
		for (Post post : posts) {
			if (post.isPublic()) {
			PhotoGridCell photoGridCell = new PhotoGridCell(post);
			photoGridCell.setPreferredSize(new Dimension(160, 200));
			postsPanel.add(photoGridCell);
			}
		}
	}
	private ImageIcon getUserProfilePhoto(User user) {
		ImageIcon profilePhoto = new ImageIcon();
		try {
			ImageMatrix profilePhotoImage = ImageSecretary.readResourceImage(user.getProfilePhoto());
			Image scaledProfilePhoto = profilePhotoImage.getBufferedImage().getScaledInstance(200, 200,
					Image.SCALE_FAST);
			profilePhoto = new ImageIcon(scaledProfilePhoto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return profilePhoto;
	}
}
