package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import image.ImageMatrix;
import image.ImageSecretary;
import main.BaseLogger;
import users.Post;
import users.User;
import users.UserManager;

/**
 * PhotoGridCell displays a thumbnail of a post and the poster's profile photo
 * and nickname
 * 
 * @author Yusuf
 *
 */
public class PhotoGridCell extends JPanel {
	/**
	 * Constructs a new PhotoGridCell object
	 * 
	 * @param post        Post to be displayed
	 * @param currentUser Current User of the session
	 */
	public PhotoGridCell(Post post, User currentUser) {
		String imageName = post.getImageName();
		ImageMatrix image = new ImageMatrix(150, 150);
		// Get image
		try {
			image = ImageSecretary.readResourceImage(imageName);
		} catch (IOException e) {
			BaseLogger.error().log("Failed to open file " + imageName);
		}
		// Get User information
		User poster = post.getPoster();
		String nickname = poster.getNickname();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(160, 200)); // Set preferred size
		UserManager userManager = new UserManager();

		// thumbnail label
		ImageIcon thumbnailImage = new ImageIcon(
				image.getBufferedImage().getScaledInstance(150, 150, Image.SCALE_FAST));
		JLabel thumbnailLabel = new JLabel(thumbnailImage);
		thumbnailLabel.setSize(new Dimension(100, 100));
		add(thumbnailLabel, BorderLayout.CENTER);

		// User profile photo and nickname are displayed on userPanel
		User user = userManager.getUser(nickname);
		ImageIcon profilePhoto = new ImageIcon();
		try {
			profilePhoto = new ImageIcon(ImageSecretary.readResourceImage(user.getProfilePhoto()).getBufferedImage()
					.getScaledInstance(30, 30, Image.SCALE_FAST));
		} catch (IOException e) {
			profilePhoto = new ImageIcon();
		}
		JPanel userPanel = new JPanel();
		JLabel photoLabel = new JLabel(profilePhoto);
		JLabel nicknameLabel = new JLabel(nickname);
		userPanel.add(photoLabel);
		userPanel.add(nicknameLabel);
		nicknameLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(userPanel, BorderLayout.SOUTH);
		setBackground(Color.LIGHT_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		// Add a mouse listener to open photo interaction page
		thumbnailLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (userManager.getPostMap().get(post.getId()) == null) {
					JOptionPane.showMessageDialog(null, "This post is deleted", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (currentUser.getNickname().equals("admin")) {
					ManagePhotoPage managePhotoPage = new ManagePhotoPage(userManager.getUser("admin"), post);
					managePhotoPage.setVisible(true);
				} else {
					PhotoInteraction photoInteraction = new PhotoInteraction(post, currentUser);
					photoInteraction.setVisible(true);
				}
			}
		});

		// Add a mouse listener to open profile page
		userPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frame = new JFrame(user.getNickname() + "'s Profile");
				PublicProfilePage profilePage = new PublicProfilePage(user, currentUser);
				frame.add(profilePage);
				frame.setSize(700, 600);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
