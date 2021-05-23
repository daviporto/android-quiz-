package ui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class RightAnswerPanel extends JPanel {
	ButtonGroup group;
	JRadioButton rb1, rb2, rb3, rb4;

	public RightAnswerPanel() {
		setBackground(Main.BACKGROUNDCOLOR);
		setLayout(null);
		var b = new JLabel("escolha a resposta correta");
		b.setBackground(Main.BACKGROUNDCOLOR);
		b.setForeground(Color.WHITE);
		b.setBounds(0, 0, 200, 20);
		add(b);

		group = new ButtonGroup();

		rb1 = new JRadioButton("1");
		rb1.setBounds(0, 20, 50, 30);
		rb1.setBackground(Main.BACKGROUNDCOLOR);
		rb1.setForeground(Color.WHITE);
		add(rb1);
		group.add(rb1);
		group.setSelected(rb1.getModel(), true);

		rb2 = new JRadioButton("2");
		rb2.setBounds(50, 20, 50, 30);
		rb2.setBackground(Main.BACKGROUNDCOLOR);
		rb2.setForeground(Color.WHITE);
		add(rb2);
		group.add(rb2);

		rb3 = new JRadioButton("3");
		rb3.setBounds(100, 20, 50, 30);
		rb3.setBackground(Main.BACKGROUNDCOLOR);
		rb3.setForeground(Color.WHITE);
		add(rb3);
		group.add(rb3);

		rb4 = new JRadioButton("4");
		rb4.setBounds(150, 20, 50, 30);
		rb4.setBackground(Main.BACKGROUNDCOLOR);
		rb4.setForeground(Color.WHITE);
		add(rb4);
		group.add(rb4);

	}

	public int getPressed() {
		if (rb1.isSelected())
			return 1;
		if (rb2.isSelected())
			return 2;
		if (rb3.isSelected())
			return 3;
		return 4;
	}

	public void setPressed(int button) {
		if (button == 4)
			group.setSelected(rb4.getModel(), true);
		else if (button == 3)
			group.setSelected(rb3.getModel(), true);
		else if (button == 2)
			group.setSelected(rb2.getModel(), true);
		else
			group.setSelected(rb1.getModel(), true);

	}
}
