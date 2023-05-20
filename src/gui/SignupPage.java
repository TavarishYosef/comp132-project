package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;
import users.UserManager;
import users.UserTier;

/**
 * A Signup Page for the PhotoCloud application. Validates the user's
 * information to create a new account. Has a Login button
 * 
 * @author Yusuf
 *
 */
public class SignupPage extends JFrame {

	private JLabel loginLabel;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel ageLabel;
	private JLabel emailLabel;
	private JLabel NicknameLabel;
	private JLabel passwordLabel;
	private JLabel nameErrorLabel;
	private JLabel surnameErrorLabel;
	private JLabel ageErrorLabel;
	private JLabel emailErrorLabel;
	private JLabel nicknameErrorLabel;
	private JLabel passwordErrorLabel;
	private JTextField surnameField;
	private JTextField nameField;
	private JPanel panel;
	private JTextField ageField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signupButton;
	/**
	 * Creates a new {@link SignupPage} object
	 */
	public SignupPage() {
		// Set up the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		setLocationRelativeTo(null);
		setTitle("PhotoCloud Signup Page");
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		// This panel is for controlling the window borders
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		// Name-surname fields
		nameLabel = new JLabel("Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);

		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);
		nameField.setColumns(20);

		nameErrorLabel = new JLabel("");
		nameErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_nameErrorLabel = new GridBagConstraints();
		gbc_nameErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_nameErrorLabel.gridx = 2;
		gbc_nameErrorLabel.gridy = 1;
		add(nameErrorLabel, gbc_nameErrorLabel);

		surnameLabel = new JLabel("Surname");
		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.anchor = GridBagConstraints.EAST;
		gbc_surnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameLabel.gridx = 0;
		gbc_surnameLabel.gridy = 2;
		add(surnameLabel, gbc_surnameLabel);

		surnameField = new JTextField();
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.insets = new Insets(0, 0, 5, 5);
		gbc_surnameField.gridx = 1;
		gbc_surnameField.gridy = 2;
		add(surnameField, gbc_surnameField);
		surnameField.setColumns(20);

		surnameErrorLabel = new JLabel("");
		surnameErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_surnameErrorLabel = new GridBagConstraints();
		gbc_surnameErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_surnameErrorLabel.gridx = 2;
		gbc_surnameErrorLabel.gridy = 2;
		add(surnameErrorLabel, gbc_surnameErrorLabel);

		// Age
		ageLabel = new JLabel("Age");
		GridBagConstraints gbc_ageLabel = new GridBagConstraints();
		gbc_ageLabel.anchor = GridBagConstraints.EAST;
		gbc_ageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ageLabel.gridx = 0;
		gbc_ageLabel.gridy = 3;
		add(ageLabel, gbc_ageLabel);

		ageField = new JTextField();
		GridBagConstraints gbc_ageField = new GridBagConstraints();
		gbc_ageField.insets = new Insets(0, 0, 5, 5);
		gbc_ageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageField.gridx = 1;
		gbc_ageField.gridy = 3;
		add(ageField, gbc_ageField);
		ageField.setColumns(10);

		ageErrorLabel = new JLabel("");
		ageErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_ageErrorLabel = new GridBagConstraints();
		gbc_ageErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_ageErrorLabel.gridx = 2;
		gbc_ageErrorLabel.gridy = 3;
		add(ageErrorLabel, gbc_ageErrorLabel);

		// Email
		emailLabel = new JLabel("Email");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 4;
		add(emailLabel, gbc_emailLabel);

		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 4;
		add(emailField, gbc_emailField);
		emailField.setColumns(10);

		emailErrorLabel = new JLabel("");
		emailErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_emailErrorLabel = new GridBagConstraints();
		gbc_emailErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_emailErrorLabel.gridx = 2;
		gbc_emailErrorLabel.gridy = 4;
		add(emailErrorLabel, gbc_emailErrorLabel);

		// Nickname
		NicknameLabel = new JLabel("Nickname");
		GridBagConstraints gbc_NicknameLabel = new GridBagConstraints();
		gbc_NicknameLabel.anchor = GridBagConstraints.EAST;
		gbc_NicknameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_NicknameLabel.gridx = 0;
		gbc_NicknameLabel.gridy = 5;
		add(NicknameLabel, gbc_NicknameLabel);

		nicknameField = new JTextField();
		GridBagConstraints gbc_nicknameField = new GridBagConstraints();
		gbc_nicknameField.insets = new Insets(0, 0, 5, 5);
		gbc_nicknameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nicknameField.gridx = 1;
		gbc_nicknameField.gridy = 5;
		add(nicknameField, gbc_nicknameField);
		nicknameField.setColumns(10);

		nicknameErrorLabel = new JLabel("");
		nicknameErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_nicknameErrorLabel = new GridBagConstraints();
		gbc_nicknameErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_nicknameErrorLabel.gridx = 2;
		gbc_nicknameErrorLabel.gridy = 5;
		add(nicknameErrorLabel, gbc_nicknameErrorLabel);

		// Password
		passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.anchor = GridBagConstraints.EAST;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 6;
		add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);

		passwordErrorLabel = new JLabel("");
		passwordErrorLabel.setForeground(Color.red);
		GridBagConstraints gbc_passwordErrorLabel = new GridBagConstraints();
		gbc_passwordErrorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordErrorLabel.gridx = 2;
		gbc_passwordErrorLabel.gridy = 6;
		add(passwordErrorLabel, gbc_passwordErrorLabel);

		// Buttons
		loginLabel = new JLabel("Already have an account?");
		GridBagConstraints gbc_loginLabel = new GridBagConstraints();
		gbc_loginLabel.insets = new Insets(0, 0, 5, 0);
		gbc_loginLabel.gridx = 2;
		gbc_loginLabel.gridy = 7;
		add(loginLabel, gbc_loginLabel);

		signupButton = new JButton("Signup");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameErrorLabel.setText("");
				surnameErrorLabel.setText("");
				ageErrorLabel.setText("");
				emailErrorLabel.setText("");
				nicknameErrorLabel.setText("");
				passwordErrorLabel.setText("");

				String name = nameField.getText();
				String surname = surnameField.getText();
				String age = ageField.getText();
				String email = emailField.getText();
				String nickname = nicknameField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				boolean valid = true;
				UserManager userManager = new UserManager();

				// Validate name and surname
				name = name.strip();
				surname = surname.strip();
				if (name.equals("")) {
					valid = false;
					nameErrorLabel.setText("Please enter your name.");
				}

				if (surname.equals("")) {
					valid = false;
					surnameErrorLabel.setText("Please enter your surname.");
				}
				// Validate age
				try {
					int intAge = Integer.parseInt(age);
					if (intAge < 0) {
						ageErrorLabel.setText("Please enter a valid age");
						valid = false;
					}
				} catch (NumberFormatException error) {
					ageErrorLabel.setText("Please enter a valid age");
					valid = false;
				}

				// Validate email
				if (!email.matches("\\w+@.+\\..+")) { // Is email in the form user@mail.com
					emailErrorLabel.setText("Please enter a valid email");
					valid = false;
				} else if (!userManager.checkEmail(email)) { // Is email taken
					emailErrorLabel.setText("Email taken");
					valid = false;
				}

				// Validate nickname
				if (!nickname.matches("\\w+")) { // Is nickname alphanumeric
					nicknameErrorLabel.setText("Please enter a valid nickname");
					valid = false;
				} else if (!userManager.checkNickname(nickname)) { // Is nickname already taken
					nicknameErrorLabel.setText("Username taken");
					valid = false;
				}

				// Validate password
				if (password.length() < 4 || password.length() > 20) {
					passwordErrorLabel.setText("Password should be between 4 and 20 characters long");
					valid = false;
				} else if (!password.matches("\\w+")) {
					passwordErrorLabel.setText("Password must only contain alphanumeric characters");
					valid = false;
				}

				if (valid) {
					User user = new User(nickname, password, name, surname, Integer.parseInt(age), email,
							UserTier.FREE);
					userManager.addUser(user);
					userManager.writeUser(user);
					setVisible(false);
					MainFrame mainFrame = new MainFrame(user);
					mainFrame.setVisible(true);
					dispose();
				}
			}
		});
		GridBagConstraints gbc_signupButton = new GridBagConstraints();
		gbc_signupButton.insets = new Insets(0, 0, 0, 5);
		gbc_signupButton.gridx = 1;
		gbc_signupButton.gridy = 8;
		add(signupButton, gbc_signupButton);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				LoginPage loginPage = new LoginPage();
				loginPage.setVisible(true);
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.gridx = 2;
		gbc_loginButton.gridy = 8;
		add(loginButton, gbc_loginButton);
	}
}
