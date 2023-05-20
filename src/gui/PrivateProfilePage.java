package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import users.User;
import users.UserManager;

/**
 * Shows the user's private information and lets them change it
 * 
 * @author Yusuf
 *
 */
public class PrivateProfilePage extends JPanel {
	private UserManager userManager;
	private JLabel nicknameLabel;
	private JLabel tierLabel;
	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel emailLabel;
	private JLabel ageLabel;

	private JButton passwordButton;
	private JButton nameButton;
	private JButton surnameButton;
	private JButton emailButton;
	private JButton ageButton;
	private JButton postsButton;
	private JButton profilePhotoButton;

	/**
	 * Creates a new {@link PrivateProfilePage} object
	 * 
	 * @param user Owner of the profile page
	 */
	public PrivateProfilePage(User user) {
		userManager = new UserManager();
		// Initialize Jlabels
		nicknameLabel = new JLabel("Nickname:   " + user.getNickname());
		tierLabel = new JLabel("User tier: " + user.getUserTier());
		nameLabel = new JLabel("Name:   " + user.getName());
		surnameLabel = new JLabel("Surname:   " + user.getSurname());
		emailLabel = new JLabel("Email:   " + user.getEmail());
		ageLabel = new JLabel("Age:   " + user.getAge());
		// Initialize buttons
		passwordButton = new JButton("Change Password");
		nameButton = new JButton("Change");
		surnameButton = new JButton("Change");
		emailButton = new JButton("Change");
		ageButton = new JButton("Change");
		profilePhotoButton = new JButton("Change Profile Photo");
		postsButton = new JButton("Manage your posts");

		// Configure GridBay Layout and grid cells
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 1, 1, 1, 1, 1, 1, 0.0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_nicknameLabel = new GridBagConstraints();
		gbc_nicknameLabel.insets = new Insets(0, 5, 0, 5);
		gbc_nicknameLabel.gridx = 0;
		gbc_nicknameLabel.gridy = 0;
		add(nicknameLabel, gbc_nicknameLabel);

		GridBagConstraints gbc_tierLabel = new GridBagConstraints();
		gbc_nicknameLabel.insets = new Insets(0, 5, 0, 5);
		gbc_nicknameLabel.gridx = 0;
		gbc_nicknameLabel.gridy = 1;
		add(tierLabel, gbc_tierLabel);

		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 5, 0, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 2;
		add(nameLabel, gbc_nameLabel);

		GridBagConstraints gbc_nameButton = new GridBagConstraints();
		gbc_nameButton.insets = new Insets(0, 5, 0, 5);
		gbc_nameButton.gridx = 1;
		gbc_nameButton.gridy = 2;
		add(nameButton, gbc_nameButton);

		GridBagConstraints gbc_surnameLabel = new GridBagConstraints();
		gbc_surnameLabel.insets = new Insets(0, 5, 0, 5);
		gbc_surnameLabel.gridx = 0;
		gbc_surnameLabel.gridy = 3;
		add(surnameLabel, gbc_surnameLabel);

		GridBagConstraints gbc_surnameButton = new GridBagConstraints();
		gbc_surnameButton.insets = new Insets(0, 5, 0, 5);
		gbc_surnameButton.gridx = 1;
		gbc_surnameButton.gridy = 3;
		add(surnameButton, gbc_surnameButton);

		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 5, 0, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 4;
		add(emailLabel, gbc_emailLabel);

		GridBagConstraints gbc_emailButton = new GridBagConstraints();
		gbc_emailButton.insets = new Insets(0, 5, 0, 5);
		gbc_emailButton.gridx = 1;
		gbc_emailButton.gridy = 4;
		add(emailButton, gbc_emailButton);

		GridBagConstraints gbc_ageLabel = new GridBagConstraints();
		gbc_ageLabel.insets = new Insets(0, 5, 0, 5);
		gbc_ageLabel.gridx = 0;
		gbc_ageLabel.gridy = 5;
		add(ageLabel, gbc_ageLabel);

		GridBagConstraints gbc_ageButton = new GridBagConstraints();
		gbc_ageButton.insets = new Insets(0, 5, 0, 5);
		gbc_ageButton.gridx = 1;
		gbc_ageButton.gridy = 5;
		add(ageButton, gbc_ageButton);

		JLabel profilePhotoLabel = new JLabel("Profile Photo: ");
		GridBagConstraints gbc_profilePhotoLabel = new GridBagConstraints();
		gbc_profilePhotoLabel.insets = new Insets(1, 5, 0, 5);
		gbc_profilePhotoLabel.gridx = 0;
		gbc_profilePhotoLabel.gridy = 6;
		add(profilePhotoLabel, gbc_profilePhotoLabel);

		JLabel photoLabel = new JLabel(
				new ImageIcon(user.getProfilePhotoImage().getScaledInstance(150, 150, Image.SCALE_FAST)));
		GridBagConstraints gbc_photoLabel = new GridBagConstraints();
		gbc_photoLabel.gridx = 1;
		gbc_photoLabel.gridy = 6;
		add(photoLabel, gbc_photoLabel);

		GridBagConstraints gbc_profilePhotoButton = new GridBagConstraints();
		gbc_profilePhotoButton.insets = new Insets(5, 5, 5, 5);
		gbc_profilePhotoButton.gridx = 1;
		gbc_profilePhotoButton.gridy = 7;
		add(profilePhotoButton, gbc_profilePhotoButton);

		GridBagConstraints gbc_passwordButton = new GridBagConstraints();
		gbc_passwordButton.gridx = 1;
		gbc_passwordButton.gridy = 9;
		add(passwordButton, gbc_passwordButton);

		GridBagConstraints gbc_postsButton = new GridBagConstraints();
		gbc_postsButton.gridx = 1;
		gbc_postsButton.gridy = 11;
		add(postsButton, gbc_postsButton);

		// Add actions to buttons
		nameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName = JOptionPane.showInputDialog(nameButton, "Enter new Name", "Change name",
						JOptionPane.QUESTION_MESSAGE);
				if (newName == null)
					return;
				else if (newName.equals(""))
					return;
				user.setName(newName);
				userManager.removeUser(user);
				userManager.addUser(user);
				userManager.updateUsers();
				nameLabel.setText("Name:   " + user.getName());
			}
		});

		surnameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newSurname = JOptionPane.showInputDialog(surnameButton, "Enter new Surname", "Change surname",
						JOptionPane.QUESTION_MESSAGE);
				if (newSurname == null)
					return;
				else if (newSurname.equals(""))
					return;
				user.setSurname(newSurname);
				userManager.removeUser(user);
				userManager.addUser(user);
				userManager.updateUsers();
				surnameLabel.setText("Surname:   " + user.getSurname());
			}
		});
		ageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newAge = JOptionPane.showInputDialog(ageButton, "Enter new Age", "Change age",
						JOptionPane.QUESTION_MESSAGE);
				if (newAge == null)
					return;
				else if (!newAge.matches("\\d+")) {
					JOptionPane.showMessageDialog(ageButton, "Invalid value for age", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (Integer.parseInt(newAge) <= 0) {
					JOptionPane.showMessageDialog(ageButton, "Invalid value for age", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				user.setAge(Integer.parseInt(newAge));
				userManager.removeUser(user);
				userManager.addUser(user);
				userManager.updateUsers();
				ageLabel.setText("Age:   " + user.getAge());
			}
		});
		emailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newEmail = JOptionPane.showInputDialog(emailButton, "Enter new Email", "Change email",
						JOptionPane.QUESTION_MESSAGE);
				if (newEmail == null)
					return;
				else if (!newEmail.matches("\\w+@.+\\..+")) { // Is email in the form user@mail.com
					JOptionPane.showMessageDialog(emailButton, "Invalid email", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (!userManager.checkEmail(newEmail)) { // Is email taken
					JOptionPane.showMessageDialog(ageButton, "Email taken", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				user.setEmail(newEmail);
				userManager.removeUser(user);
				userManager.addUser(user);
				userManager.updateUsers();
				emailLabel.setText("Email:   " + user.getEmail());
			}
		});
		profilePhotoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG Images", "jpg", "jpeg",
						"png");
				chooser.setFileFilter(filter);
				chooser.setCurrentDirectory(new File("./images"));
				int returnVal = chooser.showOpenDialog(profilePhotoButton);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String newPhoto = chooser.getSelectedFile().getName();
					user.setProfilePhoto(newPhoto);
					userManager.removeUser(user);
					userManager.addUser(user);
					userManager.updateUsers();
					photoLabel.setIcon(
							new ImageIcon(user.getProfilePhotoImage().getScaledInstance(150, 150, Image.SCALE_FAST)));
				}
			}
		});
		passwordButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newPassword = JOptionPane.showInputDialog(passwordButton, "Enter new password",
						"Change password", JOptionPane.QUESTION_MESSAGE);
				if (newPassword == null)
					return;
				if (newPassword.length() < 4 || newPassword.length() > 20) {
					JOptionPane.showMessageDialog(passwordButton, "Password should be between 4 and 20 characters long",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!newPassword.matches("\\w+")) {
					JOptionPane.showMessageDialog(passwordButton, "Password must only contain alphanumeric characters",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				user.setPassword(newPassword);
				userManager.removeUser(user);
				userManager.addUser(user);
				userManager.updateUsers();
			}
		});
		postsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OwnPhotoPage ownPhotoPage = new OwnPhotoPage(user);
				ownPhotoPage.setVisible(true);
			}
		});
	}
}
