package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.image.Image;

/**
 * The House class represent a House object, which contains house name, house
 * color, the name of a professor who is the Head of the house,and an array to
 * store the students in the house.It also has a logo associated with it.Houses
 * should sort their students by year first, then by last name, then first name.
 * 
 * @author Tiffany Tsai(tue170)
 * 
 */
public class House {

	private String houseName;
	private String houseColor;
	private String houseHead;
	private Image photo;
	private ArrayList<Student> students;
	private Comparator<Student> studentCompartor;

	/**
	 * Three-argument constructor initializes houseName, houseColor and student
	 * array.
	 * 
	 * @param name
	 *            The name of the house
	 * @param color
	 *            The color of the house
	 * @param head
	 *            The name of a professor who is the Head of the house
	 */

	public House(String name, String color, String head) {
		this.students = new ArrayList<Student>();
		this.houseName = name;
		this.houseColor = color;
		this.houseHead = head;
	}

	public String toString() {

		String printOut = this.houseName + " " + "(" + this.houseColor + ")" + " " + this.houseHead + "\n";
		return printOut;
	}

	/**
	 * Method to read in source files from file paths using BufferedReader.
	 * 
	 * @param fileName
	 *            the name of the file reading in
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public void parseFromFile(String fileName) throws IOException {

		BufferedReader buffer = new BufferedReader(new FileReader(new File(fileName)));
		String line = null;
		while ((line = buffer.readLine()) != null) {
			String studentInfo[] = line.split(",");
			Student student = new Student(studentInfo[0], Integer.valueOf(studentInfo[1]));
			this.students.add(student);
			// Sort the students by their year then last name and first name using the
			// thenComparing
			studentCompartor = Comparator.comparing(Student::getStudentYear).thenComparing(Student::getSudentLastName)
					.thenComparing(Student::getSudentFirstName);
			Collections.sort(this.students, studentCompartor);

		}
		buffer.close();
	}

	/**
	 * Method to set the house logo image.
	 * 
	 * @param picture
	 *            the image of the house logo
	 */
	public void setHouseImage(Image picture) {
		this.photo = picture;
	}

	/**
	 * Method to set the house name.
	 * 
	 * @param name
	 *            The name of the house
	 */
	public void setHouseName(String name) {
		this.houseName = name;
	}

	/**
	 * Method to set the house color.
	 * 
	 * @param color
	 *            The color of the house of the house
	 */
	public void setHouseColor(String color) {
		this.houseColor = color;
	}

	/**
	 * Method to set the house head.
	 * 
	 * @param head
	 *            Name of a professor who is the Head of the house
	 */
	public void setHouseHead(String head) {
		this.houseHead = head;
	}

	/**
	 * Method to retrieve the house name.
	 * 
	 * @return The name of the house
	 */
	public String getHouseName() {
		return houseName;
	}

	/**
	 * Method to retrieve the house color.
	 * 
	 * @return The color of the house
	 */
	public String getHouseColor() {
		return houseColor;
	}

	/**
	 * Method to retrieve the name of a professor who is the Head of the house.
	 * 
	 * @return Name of a professor who is the Head of the house
	 */
	public String getHouseHead() {
		return houseHead;
	}

	/**
	 * Method to retrieve the image of the house logo.
	 * 
	 * @return Image of the house logo
	 */
	public Image getHouseImage() {
		return photo;
	}

	/**
	 * Method to retrieve the list of the students in the house.
	 * 
	 * @return List of the students in the house
	 */
	public ArrayList<Student> getStudents() {
		return this.students;
	}
}
