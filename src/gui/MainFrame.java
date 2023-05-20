package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import image.ImageSecretary;
import main.BaseLogger;
import users.User;
import users.UserManager;

/**
 * Main frame of the PhotoCloud application. Includes the {@link DiscoverPage},
 * allows searching users, and lets the users see their private profile
 * 
 * @author Yusuf
 *
 */
public class MainFrame extends JFrame {

	private JComponent displayedComponent;

	/**
	 * Constructs a new MainFrame object
	 * 
	 * @param currentUser User that is logged in
	 */
	public MainFrame(User currentUser) {
		UserManager userManager = new UserManager();
		// Set up the frame
		setTitle("PhotoCloud");
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
		JButton logoutButton = new JButton("Logout");

		// The different pages
		DiscoverPage discoverPage = new DiscoverPage(currentUser);
		PrivateProfilePage privateProfilePage = new PrivateProfilePage(currentUser);

		// Add the components
		controlPanel.add(discoverButton);
		controlPanel.add(searchButton);
		controlPanel.add(profileButton);
		controlPanel.add(postButton);
		controlPanel.add(logoutButton);
		add(controlPanel, BorderLayout.WEST);
		add(discoverPage, BorderLayout.EAST);
		displayedComponent = discoverPage;
		// Profile Button Action
		profileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayedComponent.setVisible(false);
				remove(displayedComponent);
				add(privateProfilePage, BorderLayout.CENTER);
				privateProfilePage.setVisible(true);
				displayedComponent = privateProfilePage;
				setSize(951, 600);
				setSize(950, 600);
			}
		});
		// Discover Button Action
		discoverButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayedComponent.setVisible(false);
				remove(displayedComponent);
				add(discoverPage, BorderLayout.EAST);
				discoverPage.setVisible(true);
				displayedComponent = discoverPage;
				discoverPage.refresh();
			}
		});
		// Post Button Action
		postButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the image file
				JFileChooser chooser = new JFileChooser("./images");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG Images", "jpg", "jpeg",
						"png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(postButton);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String imageName = chooser.getSelectedFile().getName();
					try {
						UploadImagePage uploadImagePage = new UploadImagePage(currentUser,
								ImageSecretary.readResourceImage(imageName));
						uploadImagePage.setVisible(true);
						BaseLogger.info().log("User " + currentUser.getNickname() + " opened file " + imageName);
					} catch (IOException io) {
						JOptionPane.showMessageDialog(postButton, "File does not exist", "Error",
								JOptionPane.ERROR_MESSAGE);
						BaseLogger.error().log("User " + currentUser.getNickname() + " failed to open " + imageName);
					} catch (NullPointerException nullPo) {
						return;
					}
				}
			}
		});
		// Search Button Action
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(searchButton,
						"Enter the nickname of the user you want to search", "Search user",
						JOptionPane.QUESTION_MESSAGE);
				if (input == null) {
					return;
				}
				User user = userManager.getUser(input);
				if (user == null || input.toLowerCase().equals("admin")) {
					JOptionPane.showMessageDialog(searchButton, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JFrame frame = new JFrame(user.getNickname() + "'s Profile");
					PublicProfilePage profilePage = new PublicProfilePage(user, currentUser);
					frame.add(profilePage);
					frame.setSize(700, 600);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
		// Logout Button Action
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BaseLogger.info().log("User " + currentUser.getNickname() + " logged out.");
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
				dispose();
			}
		});
	}
}
