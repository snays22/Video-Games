package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * @author Charles T.
 *
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root  = FXMLLoader.load(getClass().getResource("/app/view.fxml"));
      Scene      scene = new Scene(root);

      primaryStage.setResizable(false);
      primaryStage.setTitle("Pong Game");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
