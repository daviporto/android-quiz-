package util;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;


public class StringtoPixels {
	
	
	public static int getWidthStringPixels(String text, Font font ) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
		
		return textwidth;
	}
	
	public static int getHeightStringPixels(String text, Font font ) {
		AffineTransform affinetransform = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
		int textheight = (int)(font.getStringBounds(text, frc).getHeight());
		
		return textheight;
	}
	
	public static String getStringFormated(Font font, String text, int MAXLINETWIDTH) {
		String result = "";
		String aux;
		int positionOnText = 0;
		int lastWord;
		while (positionOnText < text.length()) {
			aux = text.substring(positionOnText, text.length());
			int textWidth = getWidthStringPixels(aux, font);
			while(textWidth > MAXLINETWIDTH) {
				lastWord = aux.lastIndexOf(" ");
				lastWord += positionOnText;
				aux = text.substring(positionOnText, lastWord);
				textWidth = getWidthStringPixels(aux, font);
			}
			if(aux.indexOf(" ") == 0) { aux = aux.substring(1, aux.length());
				positionOnText += aux.length();
				positionOnText++;
				result+= aux + "\n";
			}else {
				positionOnText += aux.length();
				result+= aux + "\n";
			}
		}
			
		return result;
		}
}
