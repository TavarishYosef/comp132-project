package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import image.ImageMatrix;
import image.ImageSecretary;
import users.Post;
import users.User;

@SuppressWarnings("serial")
/**
 * PhotoInteraction opens a new frame to show the photo in full size
 * and displays poster's profile photo, name
 * and shows the description
 * 
 * @author Yusuf
 *
 */
public class PhotoInteraction extends JDialog {

	public PhotoInteraction(Post post) {
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
			System.err.println("Photo not found.");
		}
		ImageIcon fullImage = new ImageIcon(
				image.getBufferedImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
		JLabel fullImageLabel = new JLabel(fullImage);
		panel.add(fullImageLabel, BorderLayout.CENTER);

		// Display profile photo, nickname, post description
		ImageIcon profilePhoto = new ImageIcon();
		try {
			profilePhoto = new ImageIcon(
					ImageSecretary.readResourceImage(poster.getProfilePhoto()).getBufferedImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			profilePhoto = new ImageIcon();
		}
		JLabel profilePhotoLabel = new JLabel(profilePhoto);
		JLabel nicknameLabel = new JLabel(poster.getNickname());
		JTextArea descriptionTextArea = new JTextArea(post.getDescription());
		descriptionTextArea.setEditable(false);

		JPanel userInfoPanel = new JPanel();
		userInfoPanel.add(profilePhotoLabel);
		userInfoPanel.add(nicknameLabel);
		panel.add(userInfoPanel, BorderLayout.NORTH);
		panel.add(descriptionTextArea, BorderLayout.SOUTH);

		getContentPane().add(panel);
	}
}
