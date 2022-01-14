package dad.geofx.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	private MainController mainController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainController = new MainController();

		Scene scene = new Scene(mainController.getView());

		primaryStage.setTitle("GeoFx");
		primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/Globe-icon.png")));
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);

	}

}
