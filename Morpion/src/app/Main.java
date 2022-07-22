package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Charles T.
 *
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      Pane  root  = FXMLLoader.load(getClass().getResource("/app/view.fxml"));
      Scene scene = new Scene(root);

      root.requestFocus();
      primaryStage.setResizable(false);
      primaryStage.setTitle("Morpion");
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
