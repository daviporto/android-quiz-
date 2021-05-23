package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import dataBase.Question;
import dataBase.Quiz;

public class QuizPanel extends JPanel{
	public  static BufferedImage defaultImage = null;
	private PicturePanel imagePanel;
	private RightAnswerPanel rightAnswerPanel;
	private Question currentQuestion;
	private JTextField txtAnswer1, txtAnswer2, txtAnswer3,txtAnswer4;
	private JTextArea txtQuestion;
	static{
		try {
			defaultImage = ImageIO.read(QuizPanel.class.getResource("/defaultImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public QuizPanel(JFrame parent) {
		setBounds(0, 0, 300, 500);
		setBackground(Main.BACKGROUNDCOLOR);
		setForeground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{70,200,40,40,40,40,30};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{3.0,6.0,6.0,6.0,6.0,6.0,6.0};
		setLayout(gbl_panel);
		
		txtQuestion = new JTextArea();
		txtQuestion.setText("digite a pergunta aqui");
		txtQuestion.setBackground(Main.BACKGROUNDCOLOR);
		txtQuestion.setForeground(Color.WHITE);
		txtQuestion.setBorder(new LineBorder(Color.WHITE, 1));
		txtQuestion.setLineWrap(true);
		GridBagConstraints gbc_txtrQuestiona = new GridBagConstraints();
		gbc_txtrQuestiona.insets = new Insets(5, 5, 5, 5);
		gbc_txtrQuestiona.fill = GridBagConstraints.BOTH;
		gbc_txtrQuestiona.gridx = 0;
		gbc_txtrQuestiona.gridy = 0;
		add(txtQuestion, gbc_txtrQuestiona);
		
		imagePanel = new PicturePanel(defaultImage);
		imagePanel.addImageInputListener(parent);
		imagePanel.setBackground(Main.BACKGROUNDCOLOR);

		
		GridBagConstraints gbc_btnImage = new GridBagConstraints();
		gbc_btnImage.insets = new Insets(0, 0, 5, 0);
		gbc_btnImage.fill = GridBagConstraints.BOTH;
		gbc_btnImage.gridx = 0;
		gbc_btnImage.gridy = 1;
		
	
		
		add(imagePanel, gbc_btnImage);
		
		txtAnswer1 = new JTextField("digite a resposta 1");
		txtAnswer1.setBackground(Main.BACKGROUNDCOLOR);
		txtAnswer1.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAnswer1 = new GridBagConstraints();
		gbc_btnAnswer1.insets = new Insets(0, 0, 5, 0);
		gbc_btnAnswer1.fill = GridBagConstraints.BOTH;
		gbc_btnAnswer1.gridx = 0;
		gbc_btnAnswer1.gridy = 2;
		add(txtAnswer1, gbc_btnAnswer1);
		
		txtAnswer2 = new JTextField("digite a resposta 2");
		txtAnswer2.setBackground(Main.BACKGROUNDCOLOR);
		txtAnswer2.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAnswer2 = new GridBagConstraints();
		gbc_btnAnswer2.insets = new Insets(0, 0, 5, 0);
		gbc_btnAnswer2.fill = GridBagConstraints.BOTH;
		gbc_btnAnswer2.gridx = 0;
		gbc_btnAnswer2.gridy = 3;
		add(txtAnswer2, gbc_btnAnswer2);
		
		txtAnswer3 = new JTextField("digite a resposta 3");
		txtAnswer3.setBackground(Main.BACKGROUNDCOLOR);
		txtAnswer3.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAnswer3 = new GridBagConstraints();
		gbc_btnAnswer3.insets = new Insets(0, 0, 5, 0);
		gbc_btnAnswer3.fill = GridBagConstraints.BOTH;
		gbc_btnAnswer3.gridx = 0;
		gbc_btnAnswer3.gridy = 4;
		add(txtAnswer3, gbc_btnAnswer3);
		
		txtAnswer4 = new JTextField("digite a resposta 4");
		txtAnswer4.setBackground(Main.BACKGROUNDCOLOR);
		txtAnswer4.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAnswer4 = new GridBagConstraints();
		gbc_btnAnswer4.fill = GridBagConstraints.BOTH;
		gbc_btnAnswer4.gridx = 0;
		gbc_btnAnswer4.gridy = 5;
		add(txtAnswer4, gbc_btnAnswer4);
	}
	
	public void setRightAnswerPanel(RightAnswerPanel rightAnswerPanel) {
		this.rightAnswerPanel = rightAnswerPanel;
	}

	public void setQuestion(Question question) {
		updateCurrentQuestion();
		currentQuestion = question;
		if (!question.getQuestion().isBlank() ) {
			txtQuestion.setText(question.getQuestion());			
		}
		else
			txtQuestion.setText("digite a pergunta aqui");
		
		if (!question.getAnswer1().isBlank())
			txtAnswer1.setText(question.getAnswer1());
		else
			txtAnswer1.setText("digite a resposta 1 aqui");
		
		if (!question.getAnswer2().isBlank())
			txtAnswer2.setText(question.getAnswer2());
		else
			txtAnswer2.setText("digite a resposta 2 aqui");
		
		if (!question.getAnswer3().isBlank())
			txtAnswer3.setText(question.getAnswer3());
		else
			txtAnswer3.setText("digite a resposta 3 aqui");
		
		if (!question.getAnswer4().isBlank())
			txtAnswer4.setText(question.getAnswer4());
		else
			txtAnswer4.setText("digite a resposta 4 aqui");
			
		if(!question.getImage().isBlank()) {
			try {
				imagePanel.setPicture(ImageIO.read(new File(question.getImage())));
				imagePanel.setPath(question.getImage());
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}else {
			imagePanel.setPicture(defaultImage);
			imagePanel.setPath("/home/davi/Documents/Documents/eclipse/quiz/Quiz Generator/res/defaultImage.png");
		}
		rightAnswerPanel.setPressed(question.getRightAnswer());
	}
	
	private void updateCurrentQuestion() {
		if (currentQuestion == null) return;
		currentQuestion.setQuestion(txtQuestion.getText());
		currentQuestion.setAnswer1(txtAnswer1.getText());
		currentQuestion.setAnswer2(txtAnswer2.getText());
		currentQuestion.setAnswer3(txtAnswer3.getText());
		currentQuestion.setAnswer4(txtAnswer4.getText());
		currentQuestion.setRightAnswer(rightAnswerPanel.getPressed());
		currentQuestion.setImage(imagePanel.getPath());
	}

	public Document getDocument() {
		return txtQuestion.getDocument();
	}

	public int getCurrentId() {
		return currentQuestion.getId();
	}

	public String getQuestionText() {
		return txtQuestion.getText();
	}
	
	public void resizeImage(int width) {
		imagePanel.setWidth(width);
		imagePanel.resizeImage();
	}

	public void prepareToSave() {
		currentQuestion.setImage(imagePanel.getPath());
		
	}

}
