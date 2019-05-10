package viewsControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBook implements Initializable {

    @FXML
    private TextField isbnTxtField;

    @FXML
    private TextField titleTxtField;

    @FXML
    private ChoiceBox publisherChoiceBox;

    @FXML
    private ChoiceBox categoryChoiceBox;

    @FXML
    private TextField priceTxtField;

    @FXML
    private TextField addressTxtField;

    @FXML
    private VBox authorsVBox;

    private Book chosenBook;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setAddBookController(this);
    }

    public void onSceneShow(Book chosenBook) {
        this.chosenBook = chosenBook;
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openControlPanelScreen();
    }

    public void submitClk(ActionEvent actionEvent) {

    }

    public void addAuthorClk(ActionEvent actionEvent) {
        HBox newAuthor = new HBox();
        TextField authorNameTxtField = new TextField();
        authorNameTxtField.setId("N"+String.valueOf(authorsVBox.getChildren().size()));
        newAuthor.getChildren().add(authorNameTxtField);
        Button removeAuthor = new Button();
        removeAuthor.setText("-");
        removeAuthor.setOnAction(actionEvent1 -> authorsVBox.getChildren().remove(newAuthor));
        newAuthor.getChildren().add(removeAuthor);
        authorsVBox.getChildren().add(newAuthor);
    }
}
