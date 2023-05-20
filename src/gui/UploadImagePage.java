package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import filter.BlurFilter;
import filter.BrightnessFilter;
import filter.ContrastFilter;
import filter.EdgeDetection;
import filter.GrayscaleFilter;
import filter.SharpenFilter;
import image.ImageMatrix;
import image.ImageSecretary;
import main.BaseLogger;
import users.Post;
import users.User;
import users.UserManager;
import users.UserTier;

/**
 * Previews the post and lets the user apply available filters before posting
 * 
 * @author Yusuf
 *
 */
public class UploadImagePage extends JFrame {
	private UserManager userManager;
	private ImageMatrix currentImage;
	private int width;
	private int height;

	/**
	 * Creates a new {@link UploadImagePage} object
	 * 
	 * @param currentUser user of current session
	 * @param image       uploaded image file
	 */
	public UploadImagePage(User currentUser, ImageMatrix image) {
		this.userManager = new UserManager();
		this.currentImage = image;
		// Set up the frame
		setTitle("Upload Image");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		currentImage = image;
		// Get image size and resize the window
		width = image.getWidth() > 1900 ? 1900 : image.getWidth();
		height = image.getHeight() > 1000 ? 1000 : image.getHeight();
		setSize(width < 750 ? 780 : width + 20, height + 80);
		setLocationRelativeTo(null);

		// Create panels
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel imagePanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		// Create image label
		JLabel imageLabel = new JLabel();

		// Create filter buttons
		Integer[] degrees = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		JComboBox<Integer> degreeBox = new JComboBox<>(degrees);

		JButton grayscaleButton = new JButton("Grayscale");
		JButton blurButton = new JButton("Blur");
		JButton brightnessButton = new JButton("Brightness");
		JButton edgeDetectionButton = new JButton("Edge Detection");
		JButton contrastButton = new JButton("Contrast");
		JButton sharpenButton = new JButton("Sharpen");
		JButton revertButton = new JButton("Revert");
		JButton postButton = new JButton("Upload");
		// Initialize filters
		BlurFilter blurFilter = new BlurFilter();
		GrayscaleFilter grayscaleFilter = new GrayscaleFilter();
		BrightnessFilter brightnessFilter = new BrightnessFilter();
		EdgeDetection edgeDetection = new EdgeDetection();
		ContrastFilter contrastFilter = new ContrastFilter();
		SharpenFilter sharpenFilter = new SharpenFilter();

		// Sharpen Button available to everyone
		sharpenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix sharpImage = sharpenFilter.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Sharpen filter applied to file, took: " + elapsedTime + "ms");
				imageLabel.setIcon(new ImageIcon(
						sharpImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
				updateImage(sharpImage);
			}
		});
		// Edge Detection Button available to PROFESSIONAL
		edgeDetectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentUser.getUserTier() == UserTier.HOBBYIST || currentUser.getUserTier() == UserTier.FREE) {
					JOptionPane.showMessageDialog(edgeDetectionButton,
							"This filter is only available to Professional users.", "Unvalid tier",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix edgeImage = edgeDetection.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Edge Detection filter applied to file, took: " + elapsedTime + "ms");
				imageLabel.setIcon(new ImageIcon(
						edgeImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
				updateImage(edgeImage);
			}
		});
		// Constast Button available to HOBBYIST and PROFESSINAL
		contrastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentUser.getUserTier() == UserTier.FREE) {
					JOptionPane.showMessageDialog(edgeDetectionButton,
							"This filter is only available to Professional and Hobbyist users.", "Unvalid tier",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix contrastImage = contrastFilter.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Contrast filter applied to file, took: " + elapsedTime + "ms");
				updateImage(contrastImage);
				imageLabel.setIcon(new ImageIcon(
						contrastImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		});
		// Grayscale button available to PROFESSIONAL
		grayscaleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentUser.getUserTier() == UserTier.HOBBYIST || currentUser.getUserTier() == UserTier.FREE) {
					JOptionPane.showMessageDialog(edgeDetectionButton,
							"This filter is only available to Professional users.", "Unvalid tier",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix grayscaleImage = grayscaleFilter.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Grayscale filter applied to file, took: " + elapsedTime + "ms");
				updateImage(grayscaleImage);
				imageLabel.setIcon(new ImageIcon(
						grayscaleImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		});
		// Blur Button available to everyone
		blurButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix blurredImage = blurFilter.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Blur filter applied to file, took: " + elapsedTime + "ms");
				updateImage(blurredImage);
				imageLabel.setIcon(new ImageIcon(
						blurredImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		});
		// Brightness button available to HOBBYIST and PROFESSIONAL
		brightnessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentUser.getUserTier() == UserTier.FREE) {
					JOptionPane.showMessageDialog(edgeDetectionButton,
							"This filter is only available to Professional and HOBBYIST users.", "Unvalid tier",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int degree = (int) degreeBox.getSelectedItem();
				long startTime = System.currentTimeMillis();
				ImageMatrix brightImage = brightnessFilter.apply(currentImage, degree);
				long endTime = System.currentTimeMillis();
				long elapsedTime = endTime - startTime;
				BaseLogger.info().log("Brightness filter applied to file, took: " + elapsedTime + "ms");
				updateImage(brightImage);
				imageLabel.setIcon(new ImageIcon(
						brightImage.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
			}
		});
		// Reverts all filters
		revertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLabel.setIcon(
						new ImageIcon(image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
				updateImage(image);
			}
		});
		// Creates a new Post
		postButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String description = JOptionPane.showInputDialog("Add description");
				Post post;
				SecureRandom random = new SecureRandom();
				int id = random.nextInt(10000);
				while (!userManager.isValid(id)) {
					id = random.nextInt(10000);
				}
				ImageSecretary.writeFilteredImageToResources(currentImage, "post_" + id + ".jpg");
				if (description == null || description.equals("")) {
					post = new Post(id, "post_" + id + ".jpg", currentUser);
				} else {
					post = new Post(id, "post_" + id + ".jpg", currentUser, description);
				}
				userManager.addPost(id, post);
				userManager.writePost(post);
				BaseLogger.info().log("User " + currentUser.getNickname() + " created post " + post.getId());
				setVisible(false);
				dispose();
			}
		});

		// Add components to panels
		imageLabel
				.setIcon(new ImageIcon(image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		imagePanel.add(imageLabel);
		buttonPanel.add(degreeBox);
		buttonPanel.add(revertButton);
		buttonPanel.add(grayscaleButton);
		buttonPanel.add(sharpenButton);
		buttonPanel.add(contrastButton);
		buttonPanel.add(edgeDetectionButton);
		buttonPanel.add(blurButton);
		buttonPanel.add(brightnessButton);
		buttonPanel.add(postButton);

		// Add panels to main panel
		mainPanel.add(imagePanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		getContentPane().add(mainPanel);

	}

	/**
	 * updates the displayed image
	 * 
	 * @param newImage new image to be displayed
	 */
	private void updateImage(ImageMatrix newImage) {
		currentImage = newImage;
	}
}