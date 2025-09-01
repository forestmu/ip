package candy;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Candy candy;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image candyImage = new Image(this.getClass().getResourceAsStream("/images/Candy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Candy instance
     */
    public void setCandy(Candy c) {
        candy = c;
    }

    /**
     * Injects the Stage instance
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Creates the dialog box for
     * user input and chatbot reply
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String candyText = candy.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getCandyDialog(candyText, candyImage)
        );
        userInput.clear();

        if (Parser.getIsConversationOver()) {
            Parser.setIsConversationOver(false);

            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> stage.close()); // Close stage after delay
            delay.play();
        }
    }
}
