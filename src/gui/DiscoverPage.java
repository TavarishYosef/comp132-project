package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import image.ImageSecretary;
import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class DiscoverPage extends JScrollPane {

	public DiscoverPage(User user) {
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));
		setPreferredSize(new Dimension(800, 0));
		UserManager userManager = new UserManager();

		HashMap<String, String> images = userManager.getPostMap();
		int imageCount = images.size();
		for (String imageName : images.keySet()) {
			try {
				PhotoGridCell photoGridCell = new PhotoGridCell(ImageSecretary.readResourceImage(imageName), imageName,
						userManager.getOP(imageName));
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				gridPanel.add(photoGridCell);
			} catch (IOException e) {
				e.printStackTrace();
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
		setPreferredSize(new Dimension(800, 800));
		UserManager userManager = new UserManager();

		HashMap<String, String> images = userManager.getPostMap();
		for (String imageName : images.keySet()) {
			try {
				PhotoGridCell photoGridCell = new PhotoGridCell(ImageSecretary.readResourceImage(imageName), imageName,
						userManager.getOP(imageName));
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				gridPanel.add(photoGridCell);
			} catch (IOException e) {
				e.printStackTrace();
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