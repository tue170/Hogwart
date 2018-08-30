package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The School class represent a School object, which contains school name, an
 * array to store the houses in the school .
 * 
 * @author Tiffany Tsai(tue170)
 * 
 *
 */
public class School {

	private String schoolName;
	private ArrayList<House> houses;

	/**
	 * One-argument constructor initializes courseName and houses array.
	 * 
	 * @param name
	 *            The school name
	 */
	public School(String name) {
		schoolName = name;
		this.houses = new ArrayList<House>();
	}

	/**
	 * Method to set the school name.
	 * 
	 * @param The
	 *            name of the school
	 */
	public void setSchoolName(String name) {
		schoolName = name; // store the school name
	}

	/**
	 * Method to retrieve the school name.
	 * 
	 * @return the name of the school
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * Method to retrieve the list of the houses in the school.
	 * 
	 * @return List of the houses in the school
	 */
	public ArrayList<House> getHouses() {
		return this.houses;
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
			String houseInfo[] = line.split(",");
			House house = new House(houseInfo[0], houseInfo[1], houseInfo[2]);
			this.houses.add(house);
		}
		buffer.close();
		
	}

}
