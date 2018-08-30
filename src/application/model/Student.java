package application.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The Student class represent a Student object, which contains student's name
 * and the school year the student is in.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */
public class Student {

	private  final SimpleStringProperty studentName;
	private final SimpleIntegerProperty studentYear;

	/**
	 * Two-argument constructor initializes studentName and year.
	 * 
	 * @param name
	 *            The name of the student
	 * @param year
	 *            The year that's the student is in
	 */
	public Student(String name, Integer year) {
		this.studentName = new SimpleStringProperty(name);
		this.studentYear= new SimpleIntegerProperty (year);
	}
	/**
	 * Method that parse the student name as a string from the line that was read
	 * from a source file, split by space, and return the first name.
	 * @return student's first name
	 */
	public String getSudentFirstName() {
		String name[] = this.studentName.toString().split(" ");
		return name[0];
	}
	/**
	 * Method that parse the student name as a string from the line that was read
	 * from a source file, split by space, and return the last name.
	 * @return student's last name
	 */
	public String getSudentLastName() {
		String name[] = this.studentName.toString().split(" ");
		return name[1];
	}

	/**
	 * Method to set the student name.
	 * 
	 * @param name
	 *            The name of the student.
	 */
	public void SetStudentName(String name) {
		this.studentName.set(name);
	}

	/**
	 * Method to set the school year.
	 * 
	 * @param year
	 *            The school year that student is in
	 */
	public void SetStudentYear(Integer year) {
		this.studentYear.set(year);
	}

	/**
	 * Method to retrieve the student's name.
	 * 
	 * @return the student name
	 */
	public String getStudentName() {
		return studentName.get();
	}

	/**
	 * Method to retrieve the school year
	 * 
	 * @return the year that student is in
	 */
	public Integer getStudentYear() {
		return studentYear.get();
	}

	public String toString() {
		return String.format("  %s,  %d", studentName.get(), studentYear.get());
	}

}
