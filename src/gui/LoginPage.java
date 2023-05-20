package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.BaseLogger;
import users.User;
import users.UserManager;

/**
 * Requests a username and password to log users in. Has a signup button
 * 
 * @author Yusuf
 *
 */
public class LoginPage extends JFrame {

	private JTextField nicknameField;
	private JPasswordField passwordField;

	/**
	 * Constructs a new {@link LoginPage} object
	 */
	public LoginPage() {
		// Set up the frame
		setTitle("PhotoCloud Login Page");
		setSize(450, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Create the nickname label and text field
		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameField = new JTextField(20);

		// Create the password label and password field
		JLabel passwordLabel = new JLabel("Password:");
		passwordField = new JPasswordField(20);

		// Create error label
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);

		// Create the login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validate the user and navigate to the discover page
				UserManager userManager = new UserManager();
				String nickname = nicknameField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();

				if (userManager.validateUser(nickname, password)) {
					setVisible(false);
					User user = userManager.getUser(nickname);
					BaseLogger.info().log("User " + user.getNickname() + " logged in");
					MainFrame mainFrame = new MainFrame(user);
					mainFrame.setVisible(true);
					dispose();
				} else {
					errorLabel.setText("Invalid nickname and password");
				}
			}
		});

		// Create the Signup link
		JLabel signupLabel = new JLabel("Don't have an account? ");
		JButton signupButton = new JButton("Signup");
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Navigate to the Signup page
				setVisible(false);
				dispose();
				SignupPage signupPage = new SignupPage();
				signupPage.setVisible(true);
			}
		});

		// Create JPanels
		JPanel nicknamePanel = new JPanel();
		nicknamePanel.add(nicknameLabel);
		nicknamePanel.add(nicknameField);

		JPanel passwordPanel = new JPanel(new BorderLayout());
		JPanel passPanel = new JPanel();
		passPanel.add(passwordLabel);
		passPanel.add(passwordField);
		passwordPanel.add(passPanel);
		passwordPanel.add(errorLabel, BorderLayout.SOUTH);

		JPanel buttonContainer = new JPanel(new GridLayout(1, 3));
		JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
		buttonsPanel.add(loginButton);
		buttonsPanel.add(new JLabel());
		buttonsPanel.add(signupLabel);
		buttonsPanel.add(signupButton);
		buttonContainer.add(new JPanel());
		buttonContainer.add(buttonsPanel);
		buttonContainer.add(new JPanel());
		// Add the components to the JFrame
		contentPane.add(nicknamePanel, BorderLayout.NORTH);
		contentPane.add(passwordPanel, BorderLayout.CENTER);
		contentPane.add(buttonContainer, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}

}
