package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
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

	public SignupPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
