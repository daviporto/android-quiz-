package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dataBase.Question;
import dataBase.Quiz;

public class Main {

	private JFrame frame;
	private JTable table;
	private TableModel tableModel;
	private QuizPanel questionPanel;
	private List<Question> questions;
	private Configs configsFrame = new Configs();
	
	public final static Color BACKGROUNDCOLOR= Color.DARK_GRAY;

	public Main(ArrayList<Question> questions) {
		String[] title = { "pergunta" };
		Object[][] content = { { questions.get(0).getId() } };
		initialize(title, content);
		this.questions = questions;
		questionPanel.setQuestion(questions.get(0));
	}

	private void initialize(String[] title, Object[][] content) {
		frame = new JFrame();
		frame.getContentPane().setBackground(BACKGROUNDCOLOR);
		int i = 700 / 14 * 9;
		frame.setBounds(0, 0, 700, i);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 500 };
		gridBagLayout.rowHeights = new int[] { i - 60, 60 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		tableModel = new DefaultTableModel(content, title) {

			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int c) {
				return content[0][c].getClass();
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		JTableHeader header = new JTableHeader(table.getColumnModel());
		header.setForeground(Color.WHITE);
		header.setBackground(BACKGROUNDCOLOR);
		table.setTableHeader(header);
		table.setBackground(BACKGROUNDCOLOR);
		table.setGridColor(Color.CYAN);
		table.setForeground(Color.WHITE);
		table.setDefaultRenderer(String.class, new ui.Main.LineWrapCellRenderer());
		table.setRowHeight(50);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new CellSelectionListener());


		var sorter = new TableRowSorter<TableModel>(tableModel);
		sorter.setSortable(0, false);
		table.setRowSorter(sorter);

		GridBagConstraints gbc_table = new GridBagConstraints();
//		gbc_table.insets = new Insets(10, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		var tableSCRPanel = new JScrollPane(table);
		tableSCRPanel.getViewport().setBackground(BACKGROUNDCOLOR);
		frame.getContentPane().add(tableSCRPanel, gbc_table);

		questionPanel = new QuizPanel(frame);
		questionPanel.getDocument().addDocumentListener(new DocumentAction());
		var scrollQuestionsPanel = new JScrollPane(questionPanel);
		scrollQuestionsPanel.getViewport().setBackground(BACKGROUNDCOLOR);
		scrollQuestionsPanel.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				questionPanel.resizeImage(scrollQuestionsPanel.getWidth());
//				Logger.getGlobal().info("width =" + scrollQuestionsPanel.getWidth());
			}
		});
		scrollQuestionsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_panel = new GridBagConstraints();
//		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(scrollQuestionsPanel, gbc_panel);

		RightAnswerPanel rightAnswerPanel = new RightAnswerPanel();
		GridBagConstraints gbc_RAPanel = new GridBagConstraints();
		gbc_RAPanel.fill = GridBagConstraints.BOTH;
		gbc_RAPanel.gridx = 1;
		gbc_RAPanel.gridy = 1;
		frame.getContentPane().add(rightAnswerPanel, gbc_RAPanel);
		questionPanel.setRightAnswerPanel(rightAnswerPanel);

		var addDeletePanel = new JPanel();
		addDeletePanel.setLayout(null);
		var btnAdd = new JButton("add");
		btnAdd.addActionListener(new addAction());
		btnAdd.setBounds(0, 0, 100, 30);
		btnAdd.setBackground(new Color(0xb006d9));
		btnAdd.setForeground(Color.WHITE);
		addDeletePanel.setBackground(BACKGROUNDCOLOR);
		addDeletePanel.setForeground(Color.WHITE);
		addDeletePanel.add(btnAdd);
		
		var btnDelete = new JButton("delete");
		btnDelete.addActionListener(new deleteAction());
		btnDelete.setBounds(100, 0, 100, 30);
		btnDelete.setBackground(new Color(0xb006d9));
		btnDelete.setForeground(Color.WHITE);
		addDeletePanel.add(btnDelete);

		GridBagConstraints gbc_AddDeletePanel = new GridBagConstraints();
		gbc_AddDeletePanel.fill = GridBagConstraints.BOTH;
		gbc_AddDeletePanel.gridx = 0;
		gbc_AddDeletePanel.gridy = 1;
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0x8614b6));
		menuBar.setForeground(Color.WHITE);
		frame.setJMenuBar(menuBar);
		
		var btnSave = new JButton("salvar");
		btnSave.addActionListener((event) -> save());
		btnSave.setBackground(new Color(0xb006d9));
		btnSave.setForeground(Color.WHITE);
		menuBar.add(btnSave);
		
		var btnConfigs = new JButton("configurações");
		btnConfigs.addActionListener((JFrame) -> configsFrame.setVisible(true)); 
		btnConfigs.setBackground(new Color(0xb006d9));
		btnConfigs.setForeground(Color.WHITE);
		
		menuBar.add(btnConfigs);
		menuBar.setBorderPainted(false);
		
		
		frame.getContentPane().add(addDeletePanel, gbc_AddDeletePanel);
		frame.setVisible(true);
		frame.pack();
		
		
	}

	private void save() {
		questionPanel.prepareToSave();
		Quiz.save(frame, questions, 
				configsFrame.getShuffleQuestionsOrder(),
				configsFrame.getShuffleAnswersOrder(),
				configsFrame.getTime());
	}

	class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			this.setText((String) value);
			this.setWrapStyleWord(true);
			this.setLineWrap(true);
			return this;
		}

	}

	class addAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			var q = new Question(questions.size());
			questions.add(q);
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			model.insertRow(model.getRowCount(), new Object[] { " " + (q.getId()) });
			questionPanel.setQuestion(q);
		}
	}

	class deleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getRowCount() == 1) {
				JOptionPane.showMessageDialog(frame,
						"esse é a unica pergunta existente no quiz, crie mais para poder exclui-la");
				return;
			}

			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(frame,
						"para excluir alguma pergunta do quiz o selecione primeiro na lista a esquerda");
				return;
			}

			var text = "" + table.getValueAt(row, 0);
			int index = Integer.valueOf(text.substring(0, text.indexOf(" ")));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(row);
			Question.rearangeIds(questions, index);
			questions.remove(index - 1);
			rearangeTableIds(index);
		}

		public void rearangeTableIds(int row) {
			for (int i = row; i < questions.size(); i++) {
				var text = "" + table.getValueAt(i, 0);
				int index = Integer.valueOf(text.substring(0, text.indexOf(" ")));
				index--;
				table.setValueAt("" + index + text.substring(text.indexOf(" "), text.length()), i, 0);
			}
		}

	}

	class DocumentAction implements DocumentListener {
		public void insertUpdate(DocumentEvent e) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setValueAt(questionPanel.getCurrentId() + " -> " + questionPanel.getQuestionText(),
					questionPanel.getCurrentId(), 0);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setValueAt(questionPanel.getCurrentId() + " -> " + questionPanel.getQuestionText(),
					questionPanel.getCurrentId(), 0);

		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}

	}

	class CellSelectionListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int row = table.getSelectedRow();
			var text = "" + table.getValueAt(row, 0);
			int index = Integer.valueOf(text.substring(0, text.indexOf(" ")));
			if (index == questionPanel.getCurrentId())
				return;
			questionPanel.setQuestion(questions.get(index));

		}
	}
}
