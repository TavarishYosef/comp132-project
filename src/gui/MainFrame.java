package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.User;
import users.UserManager;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static void main(String[] args) {
		UserManager userManager = new UserManager();
		MainFrame mainFrame = new MainFrame(userManager.getUser("tavarishyosef"));
		mainFrame.setVisible(true);
	}

	private JComponent displayedComponent;

	public MainFrame(User user) {
		UserManager userManager = new UserManager();
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

		// The different pages
		DiscoverPage discoverPage = new DiscoverPage(userManager.getUser("tavarishyosef"));
		PrivateProfilePage privateProfilePage = new PrivateProfilePage(user);

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
				add(privateProfilePage, BorderLayout.CENTER);
				privateProfilePage.setVisible(true);
				displayedComponent = privateProfilePage;
				setSize(951, 600);
				setSize(950, 600);
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
				discoverPage.refresh();
			}
		});
		postButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
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
					PublicProfilePage profilePage = new PublicProfilePage(user);
					frame.add(profilePage);
					frame.setSize(700, 600);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
	}
}
