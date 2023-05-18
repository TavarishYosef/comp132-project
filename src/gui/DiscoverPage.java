package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import image.ImageSecretary;
import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class DiscoverPage extends JFrame {

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		DiscoverPage discoverPage = new DiscoverPage(userManager.getUser("tavarishyosef"));
		discoverPage.setVisible(true);
	}
	public DiscoverPage(User user) {
		setTitle("Discover Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(840, 600);

		JPanel discoverPanel = new JPanel(new BorderLayout());

		JPanel gridPanel = new JPanel(new GridLayout(0, 4, 5, 20));

		List<String> images = ImageSecretary.getRawImageNames();
		int imageCount = images.size();
		int height = (1 + imageCount / 4) * 200;
		for (int i = 0; i < images.size(); i++) {
			String imageName = images.get(i);
			try {
				PhotoGridCell photoGridCell = new PhotoGridCell(ImageSecretary.readResourceImage(imageName, ""),
						imageName, "TavarishYosef");
				photoGridCell.setPreferredSize(new Dimension(160, 200)); // Set fixed preferred size
				gridPanel.add(photoGridCell);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Add the gridPanel to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		discoverPanel.add(scrollPane, BorderLayout.CENTER);
		gridPanel.setPreferredSize(new Dimension(800, height));
		discoverPanel.setPreferredSize(new Dimension(800, 900));
		getContentPane().add(discoverPanel);
		setLocationRelativeTo(null);
	}
}
