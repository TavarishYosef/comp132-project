package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import image.ImageSecretary;
import users.User;
import users.UserManager;


public class DiscoverPage extends JScrollPane {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DiscoverPage discoverPage = new DiscoverPage(new UserManager().getUser("tavarishyosef"));

		frame.setSize(800, 800);
		frame.add(discoverPage);
		frame.setVisible(true);
	}

	public DiscoverPage(User user) {
		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));

		// Populate the gridPanel with PhotoGridCell instances
		setSize(840, 600);
		UserManager userManager = new UserManager();

		List<String> images = ImageSecretary.getRawImageNames();
		for (int i = 0; i < images.size(); i++) {
			String imageName = images.get(i);
			try {
				PhotoGridCell photoGridCell = new PhotoGridCell(ImageSecretary.readResourceImage(imageName), imageName,
						userManager.getOP(imageName));
				photoGridCell.setPreferredSize(new Dimension(160, 200));
				gridPanel.add(photoGridCell);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Add the gridPanel to a JScrollPane
		gridPanel.setBorder(new EmptyBorder(0, 0, 0, 40));
		gridPanel.setVisible(true);
		add(gridPanel);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);
		setVisible(true);
		setViewportView(gridPanel);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getVerticalScrollBar().setUnitIncrement(20);
	}
}