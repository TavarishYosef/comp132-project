package gui;

import javax.swing.JFrame;

import users.User;

@SuppressWarnings("serial")
public class ProfilePage extends JFrame {

	public ProfilePage(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null);
        setTitle("PhotoCloud Discover Page");
		setResizable(false);
		
	}
}
