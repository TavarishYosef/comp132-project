package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		userManager.updateUsers();
		MainFrame mainFrame = new MainFrame(userManager.getUser("tavarishyosef"));
		mainFrame.setVisible(true);
	}

	private JComponent displayedComponent;

	public MainFrame(User user) {
		UserManager userManager = new UserManager();
		setSize(950, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// The control panel and its buttons
		JPanel controlPanel = new JPanel(new GridLayout(10, 0, 0, 20));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBackground(Color.DARK_GRAY);
		JButton discoverButton = new JButton("Discover Page");
		JButton profileButton = new JButton("Profile");
		JButton searchButton = new JButton("Search user");
		JButton postButton = new JButton("Post");

		// The different pages
		DiscoverPage discoverPage = new DiscoverPage(userManager.getUser("tavarishyosef"));
		ProfilePage profilePage = new ProfilePage(userManager.getUser("tavarishyosef"));

		// Add the components
		controlPanel.add(discoverButton);
		controlPanel.add(searchButton);
		controlPanel.add(profileButton);
		controlPanel.add(postButton);
		add(controlPanel, BorderLayout.WEST);
		add(discoverPage, BorderLayout.EAST);
		displayedComponent = discoverPage;

		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayedComponent.setVisible(false);
				remove(displayedComponent);
				add(profilePage, BorderLayout.EAST);
				profilePage.setVisible(true);
				displayedComponent = profilePage;
			}
		});

		discoverButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayedComponent.setVisible(false);
				remove(displayedComponent);
				add(discoverPage, BorderLayout.EAST);
				discoverPage.setVisible(true);
				displayedComponent = discoverPage;
			}
		});
		postButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("./images");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG Images", "jpg", "png",
						"jpeg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					userManager.addPost(selectedFile.getName(), user.getNickname());
					userManager.writePost(selectedFile.getName(), user.getNickname());
					discoverPage.refresh();
				}
			}
		});
	}
}
