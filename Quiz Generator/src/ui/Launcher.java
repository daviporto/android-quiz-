package ui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Launcher extends JFrame{
	public static final int FRAMEWIDTH = 300;
	public static final int FRAMEHIGHT = 200;
	public static final int BUTTONWIDTH = FRAMEWIDTH;
	public static final int BUTTONHIGHT = FRAMEHIGHT / 2 - 18;
	
	public Launcher() {
		setLayout(null);
		setSize(FRAMEWIDTH, FRAMEHIGHT);
		setLocationRelativeTo(null);
		setTitle("Laucher");
		createUI();
		setResizable(false);
		setVisible(true);
	}
	
	private void createUI() {
		var b = new  JButton("novo Quiz");
		b.setBounds(0, 0, BUTTONWIDTH, BUTTONHIGHT);
		getContentPane().add(b);
		
		b = new  JButton("Carregar existente");
		b.setBounds(0, BUTTONHIGHT, BUTTONWIDTH, BUTTONHIGHT);
		getContentPane().add(b);
	}

}
