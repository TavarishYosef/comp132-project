package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import users.Post;
import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class DiscoverPage extends JScrollPane {

	private User user;
	public DiscoverPage(User user) {
		this.user = user;
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		setPreferredSize(new Dimension(800, 0));
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
		setViewportView(gridPanel);
		gridPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);
	}

	public void refresh() {
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		setPreferredSize(new Dimension(800, 0));
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
		setViewportView(gridPanel);
		gridPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);
	}
}