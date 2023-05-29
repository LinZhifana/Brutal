package classes.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class ControllerVueCore {
	public static ImageIcon createImageIcon(String path, int width, int height) {
		ImageIcon originalImage = new ImageIcon(ControllerVueCore.class.getResource(path));
		Image image = originalImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	public static ImageIcon createImageIcon(String path) {
		return new ImageIcon(ControllerVueCore.class.getResource(path));
	}

	public static JLabel createIconLabel(ImageIcon imageIcon) {
		JLabel lbl = new JLabel();
		lbl.setIcon(imageIcon);
		lbl.setOpaque(false);
		return lbl;
	}

	public static JLabel createIconLabel(Color color) {
		JLabel lbl = new JLabel();
		lbl.setBackground(color);
		lbl.setOpaque(true);
		return lbl;
	}

	public static JButton createIconButton(ImageIcon imageIcon) {
		JButton button = new JButton();
		button.setIcon(imageIcon);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		return button;
	}

	public static ImageIcon flipImageHorizontally(ImageIcon imageIcon) {
		Image originalImage = imageIcon.getImage();
		int w = originalImage.getWidth(null);
		int h = originalImage.getHeight(null);
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d = img.createGraphics();
		graphics2d.drawImage(originalImage, w, 0, 0, h, 0, 0, w, h, null);
		graphics2d.dispose();
		return new ImageIcon(img);
	}
}
