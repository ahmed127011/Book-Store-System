package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Publisher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddPublisher implements Initializable {
    public VBox addressesVBox;
    public TextField nameTxtField;
    public VBox phonesVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addAddressClk(ActionEvent actionEvent) {
        addNewTxtField(addressesVBox);
    }

    private void addNewTxtField(VBox vBox) {
        HBox newElement = new HBox();
        newElement.getChildren().add(new TextField());
        Button removeBtn = new Button();
        removeBtn.setText("-");
        removeBtn.setOnAction(actionEvent1 -> vBox.getChildren().remove(newElement));
        newElement.getChildren().add(removeBtn);
        vBox.getChildren().add(newElement);
    }

    public void addPhoneClk(ActionEvent actionEvent) {
        addNewTxtField(phonesVBox);
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openControlPanelScreen();
    }

    public void submitClk(ActionEvent actionEvent) {
        String authorName = nameTxtField.getText();
        List<String> addresses = getMultiTxtField(addressesVBox);
        List<String> phones = getMultiTxtField(phonesVBox);
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        Publisher newPublisher = new Publisher(authorName);
        databaseHandler.addNewPublisher(newPublisher);
        databaseHandler.addPublisherAddresses(newPublisher, addresses);
        databaseHandler.addPublisherPhones(newPublisher, phones);
    }

    private List<String> getMultiTxtField(VBox vBox) {
        List<String> result = new ArrayList<>();
        for (Node children :
                vBox.getChildren()) {
            result.add(((TextField)(((HBox)children).getChildren().get(0))).getText());
        }
        return result;
    }
}
