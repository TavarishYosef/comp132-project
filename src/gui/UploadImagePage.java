package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import filter.BlurFilter;
import filter.BrightnessFilter;
import filter.ContrastFilter;
import filter.EdgeDetection;
import filter.Filter;
import filter.GrayscaleFilter;
import filter.SharpenFilter;
import image.ImageMatrix;
import image.ImageSecretary;
import users.User;
import users.UserManager;

public class UploadImagePage extends JFrame {
	private UserManager userManager;
    private User currentUser;
    private ImageMatrix currentImage;
    private ImageMatrix filteredImage;

    public static void main(String[] args) {
    	UserManager userManager = new UserManager();
		try {
			UploadImagePage page = new UploadImagePage(userManager.getUser("tavarishyosef"), ImageSecretary.readResourceImage("hard1.jpg"));
			page.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public UploadImagePage(User currentUser, ImageMatrix image) {
        this.currentUser = currentUser;
        currentImage = image;

        setTitle("Upload Image");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        currentImage = image;

        // Create panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel imagePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Create image label
        JLabel imageLabel = new JLabel();

        // Create filter buttons
        Integer[] degrees = {0,1,2,3,4,5,6,7,8,9,10};
        JComboBox<Integer> degreeBox = new JComboBox<>(degrees);
        
        JButton grayscaleButton = new JButton("Grayscale");
        JButton blurButton = new JButton("Blur");
        JButton brightnessButton = new JButton("Brightness");
        JButton edgeDetectionButton = new JButton("Edge Detection");
        JButton contrastButton = new JButton("Contrast");
        JButton sharpenButton = new JButton("Sharpen");
        JButton revertButton = new JButton("Revert");
        JButton postButton = new JButton("Post");

        BlurFilter blurFilter = new BlurFilter();
        GrayscaleFilter grayscaleFilter = new GrayscaleFilter();
        BrightnessFilter brightnessFilter = new BrightnessFilter();
        EdgeDetection edgeDetection = new EdgeDetection();
        ContrastFilter contrastFilter = new ContrastFilter();
        SharpenFilter sharpenFilter = new SharpenFilter();
        
        sharpenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix sharpImage = sharpenFilter.apply(currentImage, degree);
                imageLabel.setIcon(new ImageIcon(sharpImage.getBufferedImage()));
                updateImage(sharpImage);
			}
		});
        edgeDetectionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix edgeImage = edgeDetection.apply(currentImage, degree);
                imageLabel.setIcon(new ImageIcon(edgeImage.getBufferedImage()));
                updateImage(edgeImage);
			}
		});
        contrastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix contrastImage = contrastFilter.apply(currentImage, degree);
                updateImage(contrastImage);
                imageLabel.setIcon(new ImageIcon(contrastImage.getBufferedImage()));
			}
		});
        grayscaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix grayscaleImage = grayscaleFilter.apply(currentImage, degree);
                updateImage(grayscaleImage);
                imageLabel.setIcon(new ImageIcon(grayscaleImage.getBufferedImage()));
            }
        });

        blurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix blurredImage = blurFilter.apply(currentImage, degree);
                updateImage(blurredImage);
                imageLabel.setIcon(new ImageIcon(blurredImage.getBufferedImage()));
            }
        });

        brightnessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int degree = (int) degreeBox.getSelectedItem();
                ImageMatrix brightImage = brightnessFilter.apply(currentImage, degree);
                updateImage(brightImage);
                imageLabel.setIcon(new ImageIcon(brightImage.getBufferedImage()));
            }
        });
        revertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
				updateImage(image);
			}
		});

        // Add components to panels
        imageLabel.setIcon(new ImageIcon(image.getBufferedImage()));
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
    private void updateImage(ImageMatrix newImage) {
    	currentImage = newImage;
    }
}