package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import users.Post;
import users.User;
import users.UserManager;

/**
 * OwnPhotoPage shows the user's public and private photos. Each post is
 * displayed with a {@link ManagePhotoGridCell} object
 * 
 * @author Yusuf
 *
 */
public class OwnPhotoPage extends JFrame {

	private User user;
	private UserManager userManager;
	private JScrollPane scrollPane;

	/**
	 * Creates a new {@link OwnPhotoPage} object
	 * 
	 * @param user Owner of the OwnPhotoPage
	 */
	public OwnPhotoPage(User user) {
		this.user = user;
		// Construct the frame
		setTitle("Manage Your Photos");
		setLocationRelativeTo(null);
		setSize(800, 400);
		setResizable(false);
		scrollPane = new JScrollPane();
		// Contruct the gridpanel
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		userManager = new UserManager();
		HashMap<Integer, Post> posts = userManager.getPostMap();
		for (Post post : posts.values()) {
			if (post.getPoster().equals(user)) {
				ManagePhotoGridCell photoGridCell = new ManagePhotoGridCell(post, user);
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				gridPanel.add(photoGridCell);
			}
		}

		// Add the gridPanel to a DiscoverPage
		scrollPane.setViewportView(gridPanel);
		gridPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		getContentPane().add(scrollPane);
	}

	/**
	 * Refreshes the OwnPhotoPage
	 */
	public void refresh() {
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		UserManager userManager = new UserManager();

		HashMap<Integer, Post> posts = userManager.getPostMap();
		for (Post post : posts.values()) {
			if (post.isPublic()) {
				PhotoGridCell photoGridCell = new PhotoGridCell(post, user);
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				gridPanel.add(photoGridCell);
			}
		}

		// Add the gridPanel to a DiscoverPage
		scrollPane.setViewportView(gridPanel);
		gridPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	}
}
