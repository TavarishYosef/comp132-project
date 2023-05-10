package gui;

import javax.swing.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
	
    private JTextField nicknameField;
    private JPasswordField passwordField;

    public LoginPage() {
        // Set up the JFrame
        setTitle("PhotoCloud Login Page");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            	signupLabel.setText(nicknameField.getText());
            }
        });

        // Add the components to the JFrame
        JPanel panel = new JPanel();
        panel.add(nicknameLabel);
        panel.add(nicknameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupLabel);
        panel.add(signupButton);
        add(panel);

        // Display the JFrame
        setVisible(true);
    }

}
