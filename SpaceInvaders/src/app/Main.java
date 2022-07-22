package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

/**
 * @author Charles T.
 *
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      VBox  root  = FXMLLoader.load(getClass().getResource("view.fxml"));
      Scene scene = new Scene(root);

      primaryStage.setTitle("Space Invaders");
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
