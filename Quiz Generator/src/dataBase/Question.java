package dataBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFrame;

import components.Fields.FieldInt;
import components.Objects.DSObject;
import components.string.DSString;

public class Question {
	private int id;
	private String question;
	private String image;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int rightAnswer;

	public Question(int id, String question, String image, String answer1, String answer2, String answer3,
			String answer4, int rightAnswer) {
		this.id = id;
		this.question = question;
		this.image = image;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.rightAnswer = rightAnswer;
	}

	public Question() {
		this(-1, "", "", "", "", "", "", 1);
	}

	public Question(int id) {
		this(id, "", "", "", "", "", "", 1);
	}

	public static void rearangeIds(List<Question> questions, int rowRemoved) {
		for (int i = rowRemoved; i < questions.size(); i++) {
			questions.get(i).idMinorsMinors();
		}
	}

	public void idMinorsMinors() {
		id--;
	}

	public DSObject save(ZipOutputStream directory) {
		var object = new DSObject("question " + id);
		object.pushString(new DSString("question", question));
		object.pushString(new DSString("answer1", answer1));
		object.pushString(new DSString("answer2", answer2));
		object.pushString(new DSString("answer3", answer3));
		object.pushString(new DSString("answer4", answer4));
		object.pushField(new FieldInt("id", id));
		object.pushField(new FieldInt("rightAnswer", rightAnswer));
		var fileName = image.substring(image.lastIndexOf('/') + 1, image.length());

		if (image.isEmpty() || fileName.equals("defaultImage.png"))
			object.pushString(new DSString("image", ""));
		else {
			object.pushString(new DSString("image", fileName));
			copyImage(image, directory, fileName);
		}

		return object;
	}

	public static void copyImage(String src, ZipOutputStream zip, String fileName) {
		InputStream in;
		var out = new ZipEntry(fileName);
		try {
			in = new FileInputStream(new File(src));
			byte[] buf = new byte[1024];
			int len;
			zip.putNextEntry(out);
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			in.close();
			zip.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public int getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

}
