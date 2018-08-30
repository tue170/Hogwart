package application.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * The MainController class handle any events that occur when the user interacts
 * with Main.fxml. If the user chooses one of the house buttons, MainController
 * will replace the current scene on the stage with House.fxml. The controller
 * should call on the model of the application to populate House.fxml with the
 * appropriate house information, based upon which button was pressed.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */
public class MainController implements Initializable {
	
	@FXML
	private Label hogwarts;
	@FXML
	private Button gryffindorHouse;
	@FXML
	private Button slytherinHouse;
	@FXML
	private Button hufflepuffHouse;
	@FXML
	private Button ravenclawHouse;
	
	/**
	 * Method called by the FXML loader after the button declared above are injected
	 * 
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		this.gryffindorHouse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				showNextView(0);
			}
		});
		this.slytherinHouse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				showNextView(1);
			}
		});
		this.hufflepuffHouse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				showNextView(2);
			}
		});
		this.ravenclawHouse.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				showNextView(3);
			}
		});
	}

	/**
	 * Method that populate House.fxml with the appropriate house information and
	 * displayed House.fxml.
	 * 
	 * @param house  The number representation of the house
	 */
	public void showNextView(int house) {

		AnchorPane root = null;
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource("../House.fxml"));
		try {
			root = (AnchorPane) fxmlLoader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HouseController controller = fxmlLoader.<HouseController>getController();
		switch (house) {
		case 0:
			controller.house(0);
			break;
		case 1:
			controller.house(1);
			break;
		case 2:
			controller.house(2);
			break;
		case 3:
			controller.house(3);
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
