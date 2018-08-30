package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * The HouseController class handle any events that occur when the user
 * interacts with House.fxml. The houseController will show the information of
 * the house on House.fxml based on the button clicked from Main.fxml.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */

public class HouseController implements Initializable {
	@FXML
	private AnchorPane housePane;
	@FXML
	private Button home;
	@FXML
	private Button enrollButton;
	@FXML
	private ImageView houseImage;
	@FXML
	private Label houseNameLabel;
	@FXML
	private Label houseHeadLabel;
	@FXML
	private TableView<Student> studentTable;
	@FXML
	private TableColumn<Student, String> studentNameCol;
	@FXML
	private TableColumn<Student, Integer> studentYearCol;

	public ObservableList<Student> studentList;
	
	/**
	 * Method called by the FXML loader after the button declared above are injected
	 * 
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		this.home.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Main.stage.setScene(Main.scene);
				Main.stage.show();
			}

		});
	}

	/**
	 * Method that displayed the information associated with the house.
	 * 
	 * @param house
	 *            The number representation of the house
	 */
	public void house(int house) {
		
		this.houseNameLabel.setText(Main.hogwarts.getHouses().get(house).getHouseName());
		this.houseHeadLabel.setText(Main.hogwarts.getHouses().get(house).getHouseHead());
		this.houseImage.setImage(Main.hogwarts.getHouses().get(house).getHouseImage());
		studentList = FXCollections.observableArrayList();
		studentList.addAll(Main.hogwarts.getHouses().get(house).getStudents());
		studentNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
		studentYearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentYear"));
		studentTable.setItems(studentList);
		this.enrollButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				showNextView(house);
			}
		});

	}

	/**
	 * Method that displayed Enroll.fxml with appropriate house information.
	 * 
	 * @param house
	 *            The number representation of the house
	 */
	public void showNextView(int house) {
		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("../Enroll.fxml"));
		try {
			root = (AnchorPane) fxmlLoader.load();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		EnrollementController controller = fxmlLoader.<EnrollementController>getController();
		switch (house) {
		case 0:
			controller.enrollStudent(0);
			break;
		case 1:
			controller.enrollStudent(1);
			break;
		case 2:
			controller.enrollStudent(2);
			break;
		case 3:
			controller.enrollStudent(3);
			break;
		}
		Scene scene = new Scene(root);
		switch (house) {
		case 0:
			scene.getStylesheets().add(getClass().getResource("gryffindor.css").toExternalForm());
			break;
		case 1:
			scene.getStylesheets().add(getClass().getResource("slytherin.css").toExternalForm());
			break;
		case 2:
			scene.getStylesheets().add(getClass().getResource("hufflepuff.css").toExternalForm());
			break;
		case 3:
			scene.getStylesheets().add(getClass().getResource("ravenclaw.css").toExternalForm());
			break;
		}
		Main.stage.setScene(scene);
		Main.stage.show();
	}

}
