package viewsControllers;


import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import models.User;


import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private TextField userNameTxtField;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private TextField emailTxtField;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField lastNameTxtField;
    @FXML
    private TextField addressTxtField;
    @FXML
    private TextField phoneTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SignUpClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = new MysqlDatabaseHandler();
        String username = userNameTxtField.getText();
        String password = passwordTxtField.getText();
        String email = emailTxtField.getText();
        String phone = phoneTxtField.getText();
        String firstName = firstNameTxtField.getText();
        String lastName = lastNameTxtField.getText();
        String address = addressTxtField.getText();

        User user = new User(username, email, password, phone);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);

        Boolean signedUp = databaseHandler.signUp(user);
        if(signedUp) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Signed Up");
            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                @Override
                public void handle(DialogEvent dialogEvent) {
                    ViewsController.getInstance().openLoginScreen();
                }
            });
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong SignUp Information");
            alert.show();
        }
    }

    public void backClk(ActionEvent actionEvent) {
        ViewsController.getInstance().openLoginScreen();
    }
}
