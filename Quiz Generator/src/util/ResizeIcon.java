package util;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class ResizeIcon {
	
	public static ImageIcon ResizeImage(ImageIcon image, int width, int height) {

		Image i = image.getImage();
		double escala = determineImageScale(image.getIconWidth(), image.getIconHeight(), width, height);
		if(width <= 0 || height <= 0 ) return image;
		var newIcon = i.getScaledInstance((int)(image.getIconWidth() * escala),(int) (image.getIconHeight() * escala)
										, Image.SCALE_SMOOTH);
		return new ImageIcon(newIcon);
	}
	
	public static Image ResizeImage(Image image, int width, int height) {
		double escala = determineImageScale(image.getWidth(null), image.getHeight(null), width, height);
		if(width <= 0 || height <= 0 ) return image;
		var newImage = image.getScaledInstance((int)(image.getWidth(null) * escala),(int) (image.getHeight(null) * escala)
										, Image.SCALE_SMOOTH);
		return newImage;
	}
	
	private static double determineImageScale(int sourceWidth, int sourceHeight, int targetWidth, int targetHeight) {
		double scalex = (double) targetWidth / sourceWidth;
		double scaley = (double) targetHeight / sourceHeight;
		return Math.min(scalex, scaley);
	}
	
	public static ImageIcon ResizeImageToPanel(ImageIcon picture, JPanel panel){
		int resizeX = 0, resizeY = 0;
		if(picture == null || panel == null) return picture;
		if(picture.getIconWidth() != panel.getWidth()) { 
			resizeX = panel.getWidth();
			resizeY = picture.getIconHeight();
		}
		if(picture.getIconHeight() != panel.getHeight()) { 
			resizeY =  panel.getHeight();
			if(resizeX == 0) resizeX = picture.getIconWidth();
		
		}
		if(resizeX != 0 || resizeY != 0)  return  ResizeImage(picture, resizeX, resizeY);
		return picture;
	}
	
	public static Image ResizeImageToPanel(Image picture, JPanel panel){
		int resizeX = 0, resizeY = 0;
		if(picture == null || panel == null) return picture;
		if(picture.getWidth(null) != panel.getWidth()) { 
			resizeX = panel.getWidth();
			resizeY = picture.getHeight(null);
		}
		if(picture.getHeight(null) != panel.getHeight()) { 
			resizeY =  panel.getHeight();
			if(resizeX == 0) resizeX = picture.getWidth(null);
		
		}
		if(resizeX != 0 || resizeY != 0)  return  ResizeImage(picture, resizeX, resizeY);
		return picture;
	}
	
}
