package gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
	
    private JTextField nicknameField;
    private JPasswordField passwordField;

    public LoginPage() {
        // Set up the JFrame
        setTitle("PhotoCloud Login Page");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the nickname label and text field
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameField = new JTextField(20);

        // Create the password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Create the login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Validate the user and navigate to the profile or discover page
            }
        });

        // Create the Signup link
        JLabel signupLabel = new JLabel("Don't have an account? ");
        JButton signupButton = new JButton("Signup");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Navigate to the Signup page
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
        
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        
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
        add(nicknamePanel, BorderLayout.NORTH);
        add(passwordPanel, BorderLayout.CENTER);
        add(buttonContainer, BorderLayout.SOUTH);
    }

}
