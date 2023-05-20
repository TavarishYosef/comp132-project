package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import users.Post;
import users.User;
import users.UserManager;

/**
 * PublicProfilePage shows the user's public information and lists the user's
 * public posts
 * 
 * @author Yusuf
 *
 */
public class PublicProfilePage extends JPanel {
	/**
	 * Creates a new {@link PublicProfilePage} object
	 * @param user Owner of the profile
	 * @param currentUser current User of the session
	 */
	public PublicProfilePage(User user, User currentUser) {
		UserManager userManager = new UserManager();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

		// Gridlayout userInfoPanel houses publicInfoPanel and postsPanel
		JPanel userInfoPanel = new JPanel(new GridLayout(2, 1));
		userInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Display the user's profile photo
		ImageIcon profilePhoto = new ImageIcon(
				user.getProfilePhotoImage().getScaledInstance(180, 180, Image.SCALE_FAST));
		JLabel profilePhotoLabel = new JLabel(profilePhoto);
		userInfoPanel.add(profilePhotoLabel);

		// Display user's public information
		JPanel publicInfoPanel = new JPanel(new GridLayout(5, 1));
		JLabel nameLabel = new JLabel("Name: " + user.getName());
		JLabel surnameLabel = new JLabel("Surname: " + user.getSurname());
		JLabel nicknameLabel = new JLabel("Nickname: " + user.getNickname());
		JLabel ageLabel = new JLabel("Age: " + user.getAge());
		JLabel tierLabel = new JLabel("Tier: " + user.getUserTier());
		publicInfoPanel.add(nameLabel);
		publicInfoPanel.add(surnameLabel);
		publicInfoPanel.add(nicknameLabel);
		publicInfoPanel.add(ageLabel);
		publicInfoPanel.add(tierLabel);
		userInfoPanel.add(publicInfoPanel);
		add(userInfoPanel, BorderLayout.NORTH);

		// Put posts in 4 column grid layout
		JPanel postsPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		JScrollPane scrollPane = new JScrollPane(postsPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane, BorderLayout.CENTER);

		// Get the user's posts
		ArrayList<Post> posts = userManager.getPostsByUser(user);
		for (Post post : posts) {
			if (post.isPublic()) {
				PhotoGridCell photoGridCell = new PhotoGridCell(post, currentUser);
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				postsPanel.add(photoGridCell);
			}
		}
	}
}
