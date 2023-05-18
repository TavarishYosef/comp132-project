package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.UserManager;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}


	public MainFrame() {
		UserManager userManager = new UserManager();
		setSize(950, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);


		JPanel controlPanel = new JPanel(new GridLayout(10, 0, 0, 20));
		controlPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		controlPanel.setBackground(Color.DARK_GRAY);
		JButton discoverButton = new JButton("Discover Page");
		JButton profileButton = new JButton("Profile");
		JButton searchButton = new JButton("Search user");
		DiscoverPage discoverPage = new DiscoverPage(userManager.getUser("tavarishyosef"));
		ProfilePage profilePage = new ProfilePage(userManager.getUser("tavarishyosef"));

		profileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		discoverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		controlPanel.add(discoverButton);
		controlPanel.add(searchButton);
		controlPanel.add(profileButton);
		getContentPane().add(controlPanel, BorderLayout.WEST);
		getContentPane().add(discoverPage, BorderLayout.EAST);

	}
}
