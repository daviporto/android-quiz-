package ui;

import static util.ResizeIcon.ResizeImageToPanel;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PicturePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image picture;
	private Image resized;
	private int lastWdth, lastHeight;
	private boolean ImageInputON = false;
	private FileDialog input;
	private JButton newImageButton;
	private JFrame parent;
	private String path = "/home/davi/Documents/Documents/eclipse/quiz/Quiz Generator/res/defaultImage.png";
	// all this thing to support scroll panel
	private int _width;

	public void setPicture(BufferedImage picture) {
		this.picture = picture;
		resized = ResizeImageToPanel(picture, this);
		resizeImage();
		repaint();
	}

	public Image getPicture() {
		return picture;
	}

	public PicturePanel() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		lastHeight = lastWdth = 0;
		repaint();
		_width = getWidth();
	}

	public PicturePanel(Image picture) {
		this.picture = picture;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		resized = ResizeImageToPanel(picture, this);
		lastHeight = resized.getHeight(null);
		lastWdth = resized.getWidth(null);
		_width = getWidth();
		repaint();
		addMouseListener(new ImageListener());
	}

	public void resizeImage() {
		if (picture == null)
			return;
		int deltaHeight = Math.abs(getHeight() - lastHeight);
		int deltaWidth = Math.abs(_width - lastWdth);
		System.out.println("w = " + _width + "h = " + getHeight());
		if (deltaHeight > 20 || deltaWidth > 20) {
			resized = ResizeImageToPanel(picture, this);
			lastHeight = getHeight();
			lastWdth = _width;
			repaint();
		}
	}

	public void setWidth(int width) {
		_width = width;
	}

	public void paintComponent(Graphics g) {
		g.drawRect(0, 0, _width, getHeight());
		g.setColor(Main.BACKGROUNDCOLOR);
		g.fillRect(0, 0, _width, getHeight());
		if (resized == null)
			return;
		resizeImage();
		g.drawImage(resized, _width / 2 - resized.getWidth(null) / 2, getHeight() / 2 - resized.getHeight(null) / 2,
				this);
	}

	public void addImageInputListener(JFrame parent) {
		input = new FileDialog(parent, "selecione a imagens desejada", FileDialog.LOAD);
		this.parent = parent;
		ImageInputON = true;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	class ImageListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (ImageInputON) {
				input.setVisible(true);
				String fn = input.getFile();
				if (fn != null) {
					BufferedImage image = null;
					try {
						var _path = input.getDirectory() + "/" + fn;
						image = ImageIO.read(new File(_path));
						path = _path;
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(parent, "O arquivo escolhido e inválido");
						return;
					}

					if (image != null) {
						setPicture(image);
					} else {
						JOptionPane.showMessageDialog(parent, "O arquivo escolhido e inválido");
					}
				}

			}
		}
	}

}