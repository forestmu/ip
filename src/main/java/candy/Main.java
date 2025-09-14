package candy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A main class where application would start in.
 * Class inspired from GUI tutorial
 */
public class Main extends Application {

    private Candy candy = new Candy("./data/candyStorage.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Font.loadFont(getClass().getResource("/fonts/PixelifySans-VariableFont_wght.ttf").toExternalForm(), 10);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCandy(candy);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
