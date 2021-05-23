package dataBase;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import components.Fields.FieldBoolean;
import components.Fields.FieldInt;
import components.Objects.DSObject;
import components.dataBase.DSDataBase;
import ui.Main;

public class Quiz {
	public static int NOTIME = 0;
	public static boolean NONAVIGABLE = false;
	private ArrayList<Question> questions;
	private boolean navigable;
	private int time;
	private Main mainWindow;

	public Quiz() {
		questions = new ArrayList<Question>();
		questions.add(new Question(0));
		questions.get(0).setQuestion("works partiali");
		mainWindow = new Main(questions);
	}

	public static void save(JFrame parent, List<Question> questions, Boolean shuffleQuestions, Boolean shuffleAnswers, int time) {
		var input = new FileDialog(parent, "selecione o local que deseja salvar", FileDialog.SAVE);
		input.setVisible(true);
		String directory = input.getDirectory() + input.getFile();
		boolean state = (new File(directory)).mkdir();
		if (!state) {
			JOptionPane.showMessageDialog(parent, "não foi possivel criar o arquivoz\"" + input.getFile()
					+ "\" no diretorio\"" + input.getDirectory());
			return;
		}
		
		
		DSDataBase dataBase = new DSDataBase(input.getFile());
		DSObject configs = new DSObject("configs");
		configs.pushField(new FieldBoolean("shuffleQuestions", shuffleQuestions));
		configs.pushField(new FieldBoolean("shuffleAnswers", shuffleAnswers));
		configs.pushField(new FieldInt("time", time));
		dataBase.pushObject(configs);
		ZipOutputStream zos ;
		try {
			zos = new ZipOutputStream(new FileOutputStream(new File(directory + ".quiz")));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(parent, "22não foi possivel criar o arquivoz\"" + input.getFile());
			e.printStackTrace();
			return;
		}
		
		for (var question :questions) {
			dataBase.pushObject(question.save(zos));
		}
		dataBase.enableDebbug();
		dataBase.serializeToZipOutputStream(zos,"dataBase.bin");
		
	}
	
	

}
