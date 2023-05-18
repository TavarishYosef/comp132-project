package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import image.ImageMatrix;

@SuppressWarnings("serial")
public class PhotoGridCell extends JPanel {

	public PhotoGridCell(ImageMatrix image, String imageName, String nickname) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(160, 200)); // Set preferred size

		// thumbnail label
		ImageIcon thumbnailImage = new ImageIcon(
				image.getBufferedImage().getScaledInstance(150, 150, Image.SCALE_FAST));
		JLabel thumbnailLabel = new JLabel(thumbnailImage);
		thumbnailLabel.setSize(new Dimension(100, 100));
		add(thumbnailLabel, BorderLayout.CENTER);

		// nickname label
		JLabel nicknameLabel = new JLabel(nickname);
		nicknameLabel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(nicknameLabel, BorderLayout.SOUTH);
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));

		// Add a mouse listener to open photo interaction page
		thumbnailLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked on photo " + imageName);
			}
		});
		
		// Add a mouse listener to open profile page
		nicknameLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked user " + nickname);
			}
		});
	}
}
