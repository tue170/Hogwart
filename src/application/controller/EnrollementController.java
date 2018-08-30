package application.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The HouseController class handle any events that occur when the user
 * interacts with house.fxml. The houseController will show the information of
 * the house on house.fxml based on the button clicked from main.fxml.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */
public class EnrollementController implements Initializable {
	@FXML
	private Button home;
	@FXML
	private TextField enrollStudentName;
	@FXML
	private TextField enrollStudentYear;
	@FXML
	private Button enroll;
	@FXML
	private Text enrolledMessage;
	@FXML
	private Label studentNameLabel;
	@FXML
	private Label studentYearLabel;

	/**
	 * Method called by the FXML loader after the button declared above are injected
	 * 
	 */
	public void initialize(URL url, ResourceBundle rb) {

		this.home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					Main.populateSchool();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Main.stage.setScene(Main.scene);
				Main.stage.show();
			}

		});
	}

	/**
	 * Method that check if the grade user input is a valid number.
	 * 
	 * @param input
	 *            The user input
	 * @return True if the input is a number, false otherwise
	 */
	public boolean isNumber(String input) {
		if (input == null || input.isEmpty())
			return false;
		if (input.charAt(0) == '-')
			return false;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i)) == false)
				return false;
			if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 7)
				return false;
		}
		return true;
	}

	/**
	 * Method that check if the user input is valid
	 * 
	 * @param input
	 *            The user input that's read in from textfield
	 * @return True if the input is valid, false otherwise
	 */
	public boolean isValidInput(String input) {
		if (input == null || input.isEmpty())
			return false;
		String inputTokens[] = input.split(" ");
		if (inputTokens.length < 2)
			return false;
		for (int i = 0; i < inputTokens.length; i++) {
			for (int j = 0; j < inputTokens[i].length(); j++) {
				switch (inputTokens[i].charAt(j)) {
				case '!':
				case '?':
				case '@':
				case '#':
				case '*':
				case '/':
				case '<':
				case '>':
				case '&':
				case '%':
				case '~':
				case ';':
				case ':':
				case '[':
				case ']':
				case '{':
				case '}':
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Method that update the student information that was read from the textfield
	 * to the appropriate file.
	 * 
	 * @param fileName
	 *            The name of the file
	 * @param name
	 *            The student name that has been enrolled
	 * @param year
	 *            The student year count that has been enrolled
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public void writeTofile(String fileName, String name, String year) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(fileName, true));
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(',');
		sb.append(year);
		sb.append('\n');
		pw.write(sb.toString());
		pw.flush();
		pw.close();
	}

	/**
	 * Method that scan in the student information from the textfield and call the
	 * method to store it into appropriate file.
	 * 
	 * @param house
	 *            the number representation of the house
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public void getstudentInformation(int house) throws IOException {
		Boolean studentYearValidity = true, studentNameValidity = true;
		String studentName = "";
		String studentYear = "";
		studentYearValidity = isNumber(this.enrollStudentYear.getText());
		studentNameValidity = isValidInput(this.enrollStudentName.getText());
		if (studentYearValidity == false) {
			this.enrolledMessage.setFill(Color.RED);
			this.studentYearLabel.setStyle("-fx-text-fill: red");
			this.enrolledMessage.setText("Invalid student information, please try again.");
		}
		if (studentNameValidity == false) {
			this.enrolledMessage.setFill(Color.RED);
			this.studentNameLabel.setStyle("-fx-text-fill: red");
			this.enrolledMessage.setText("Invalid student information, please try again.");
		} else if (studentNameValidity == true && studentYearValidity == true) {
			setValid();
			this.enrolledMessage.setFill(Color.web("rgb(8, 122, 46)"));
			studentName = this.enrollStudentName.getText();
			studentYear = this.enrollStudentYear.getText();
			switch (house) {
			case 0:
				writeTofile("Gryffindor.csv", studentName, studentYear);
				break;
			case 1:
				writeTofile("Slytherin.csv", studentName, studentYear);
				break;
			case 2:
				writeTofile("Hufflepuff.csv", studentName, studentYear);
				break;
			case 3:
				writeTofile("Ravenclaw.csv", studentName, studentYear);
				break;
			}
			this.enrolledMessage.setText("Student successfully enrolled: " + this.enrollStudentName.getText()
					+ ", year " + this.enrollStudentYear.getText());
			this.enrollStudentName.clear();
			this.enrollStudentYear.clear();
		}

	}
	
	public void enrollStudent(int n) {

		this.enroll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					getstudentInformation(n);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

	/**
	 * Method that reset the color of the labels when all inputs are valid.
	 */
	public void setValid() {

		this.studentNameLabel.setStyle("-fx-text-fill: black");
		this.studentYearLabel.setStyle("-fx-text-fill: black");
	}

}