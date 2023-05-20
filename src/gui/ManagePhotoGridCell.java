package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import image.ImageMatrix;
import image.ImageSecretary;
import users.Post;
import users.User;
import users.UserManager;

/**
 * ManagePhotoGridCell is a version of PhotoGridCell for OwnPhotoPage
 * 
 * @author Yusuf
 *
 */
public class ManagePhotoGridCell extends JPanel {

	private UserManager userManager;

	/**
	 * Constructs a new {@link ManagePhotoGridCell} object
	 * 
	 * @param post        the post to be displayed
	 * @param currentUser current user of the session
	 */
	public ManagePhotoGridCell(Post post, User currentUser) {
		userManager = new UserManager();
		String imageName = post.getImageName();
		// Retrieve the image
		ImageMatrix image = new ImageMatrix(150, 150);
		try {
			image = ImageSecretary.readResourceImage(imageName);
		} catch (IOException e) {
			System.err.println("Image" + imageName + "does not exist");
		}
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(160, 200)); // Set preferred size
		// thumbnail label
		ImageIcon thumbnailImage = new ImageIcon(
				image.getBufferedImage().getScaledInstance(150, 150, Image.SCALE_FAST));
		JLabel thumbnailLabel = new JLabel(thumbnailImage);
		thumbnailLabel.setSize(new Dimension(100, 100));
		add(thumbnailLabel, BorderLayout.CENTER);

		setBackground(Color.LIGHT_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		// Add a mouse listener to open photo interaction page
		thumbnailLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Ensure that post still exists
				if (userManager.getPostMap().get(post.getId()) == null) {
					JOptionPane.showMessageDialog(null, "This post is deleted", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Open ManagePhotoPage
				ManagePhotoPage managePhotoPage = new ManagePhotoPage(currentUser, post);
				managePhotoPage.setVisible(true);
			}
		});
	}
}
