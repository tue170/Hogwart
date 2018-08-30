package application;

import java.io.FileInputStream;
import java.io.IOException;

import application.model.School;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * Application class from which JavaFX applications extend.
 * 
 * @author Tiffany Tsai(tue170)
 *
 */
public class Main extends Application {
	public static Stage stage;
	public static Scene scene;
	public static School hogwarts;

	@Override
	public void start(Stage primaryStage) {
		Main.stage = primaryStage;
		try {
			Main.populateSchool();
			Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
			Main.scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Main.stage.getIcons().add(new
			// Image(String.valueOf(getClass().getResource("../hp.png"))));
			Main.stage.getIcons().add(new Image(new FileInputStream("logos/hp.png")));
			Main.stage.setTitle("Hogwarts: School of Witchcraft and Wizardry");

			primaryStage.setScene(Main.scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that create a school object and populate the houses and students in
	 * the school.
	 * 
	 * @throws IOException
	 *             If an input or output exception occurred
	 */
	public static void populateSchool() throws IOException {
		Image image;
		Main.hogwarts = new School("Hogwarts School of Witchcraft and Wizardry");
		Main.hogwarts.parseFromFile("houseHead.csv");
		for (int i = 0; i < Main.hogwarts.getHouses().size(); i++) {
			switch (Main.hogwarts.getHouses().get(i).getHouseName()) {
			case "Gryffindor":
				System.out.println("Gryffindor");
				image = new Image(new FileInputStream("logos/gryffindor.jpg"));
				Main.hogwarts.getHouses().get(i).parseFromFile("Gryffindor.csv");
				Main.hogwarts.getHouses().get(i).setHouseImage(image);
				break;
			case "Slytherin":
				System.out.println("Slytherin");
				image = new Image(new FileInputStream("logos/slytherin.jpg"));
				Main.hogwarts.getHouses().get(i).parseFromFile("Slytherin.csv");
				Main.hogwarts.getHouses().get(i).setHouseImage(image);
				break;
			case "Hufflepuff":
				System.out.println("Hufflepuff");
				image = new Image(new FileInputStream("logos/hufflepuff.jpg"));
				Main.hogwarts.getHouses().get(i).parseFromFile("Hufflepuff.csv");
				Main.hogwarts.getHouses().get(i).setHouseImage(image);
				break;
			case "Ravenclaw":
				System.out.println("Ravenclaw");
				image = new Image(new FileInputStream("logos/ravenclaw.jpg"));
				Main.hogwarts.getHouses().get(i).parseFromFile("Ravenclaw.csv");
				Main.hogwarts.getHouses().get(i).setHouseImage(image);
				break;
			}
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}
