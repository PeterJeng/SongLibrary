package songLibrary.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class SongApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/SongLibrary.fxml"));

		GridPane root = (GridPane)loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Song Library");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}