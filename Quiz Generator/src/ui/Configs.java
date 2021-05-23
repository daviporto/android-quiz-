package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class Configs extends JFrame{

	private JCheckBox chckbxEmbaralharOrdemDasQuestoes;
	private JCheckBox chckbxEmbaralharOrdemDasRespostas;
	private JSpinner spinner;
	private JLabel lblTempoParaResponder;
	private JLabel lblCasoNoQueria ;
	
	public Configs() {
		setBounds(100, 100, 450, 250);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Main.BACKGROUNDCOLOR);
		
		chckbxEmbaralharOrdemDasQuestoes = new JCheckBox("embaralhar ordem das questoes");
		chckbxEmbaralharOrdemDasQuestoes.setBounds(10, 10, 400, 30);
		chckbxEmbaralharOrdemDasQuestoes.setBackground(Main.BACKGROUNDCOLOR);
		chckbxEmbaralharOrdemDasQuestoes.setForeground(Color.WHITE);
		getContentPane().add(chckbxEmbaralharOrdemDasQuestoes);
		
		chckbxEmbaralharOrdemDasRespostas = new JCheckBox("embaralhar ordem das respostas");
		chckbxEmbaralharOrdemDasRespostas.setBounds(10, 40, 400, 30);
		chckbxEmbaralharOrdemDasRespostas.setBackground(Main.BACKGROUNDCOLOR);
		chckbxEmbaralharOrdemDasRespostas.setForeground(Color.WHITE);
		getContentPane().add(chckbxEmbaralharOrdemDasRespostas);
		
		spinner = new JSpinner();
		spinner.setBounds(10, 80, 90, 70);
		spinner.setBackground(Main.BACKGROUNDCOLOR);
		spinner.setForeground(Color.WHITE);
		getContentPane().add(spinner);
		
		lblTempoParaResponder = new JLabel("tempo para responder o questionario(minutos)");
		lblTempoParaResponder.setBounds(110, 80, 360, 30);
		lblTempoParaResponder.setBackground(Main.BACKGROUNDCOLOR);
		lblTempoParaResponder.setForeground(Color.WHITE);
		getContentPane().add(lblTempoParaResponder);
		
		lblCasoNoQueria = new JLabel("caso não queria tempo deixe em 0");
		lblCasoNoQueria.setBounds(110, 110, 268, 30);
		lblCasoNoQueria.setBackground(Main.BACKGROUNDCOLOR);
		lblCasoNoQueria.setForeground(Color.WHITE);
		getContentPane().add(lblCasoNoQueria);
		
		JButton btnPronto = new JButton("Pronto");
		btnPronto.setBackground(new Color(0xb006d9));
		btnPronto.setForeground(Color.WHITE);
		btnPronto.setBounds(160, 160, 100, 30);
		btnPronto.addActionListener((JFrame) -> setVisible(false));
		getContentPane().add(btnPronto);
		
		setLocationRelativeTo(null);
	}

	
	public Boolean getShuffleQuestionsOrder() {
		return chckbxEmbaralharOrdemDasQuestoes.isSelected();
	}
	
	public Boolean getShuffleAnswersOrder() {
		return chckbxEmbaralharOrdemDasRespostas.isSelected();
	}
	
	public int getTime() {
		return (int) spinner.getValue();
	}
}
