package viewsControllers;


import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import models.LoggedUser;
import models.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField passwordTxtField;
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
    @FXML
    private Button submitBtn;
    @FXML
    private Label titleLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setSignUpController(this);
    }

    public void onSceneShow() {
        User curUser = LoggedUser.getInstance().getUser();
        if(curUser == null) { // Not logged in : Sign Up
            titleLabel.setText("Sign Up");
            submitBtn.setText("Sign Up");
            userNameTxtField.setText("");
            passwordTxtField.setText("");
            emailTxtField.setText("");
            firstNameTxtField.setText("");
            lastNameTxtField.setText("");
            addressTxtField.setText("");
            phoneTxtField.setText("");
        } else { // Logged in : Edit Profile
            titleLabel.setText("Edit my Profile");
            submitBtn.setText("Save");
            userNameTxtField.setText(curUser.getUserName());
            passwordTxtField.setPromptText("Unchanged");
            emailTxtField.setText(curUser.getEmail());
            firstNameTxtField.setText(curUser.getFirstName());
            lastNameTxtField.setText(curUser.getLastName());
            addressTxtField.setText(curUser.getAddress());
            phoneTxtField.setText(curUser.getPhone());
        }
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openLoginScreen();
    }

    public void submitClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        String username = userNameTxtField.getText();
        String password = passwordTxtField.getText();
        String email = emailTxtField.getText();
        String phone = phoneTxtField.getText();
        String firstName = firstNameTxtField.getText();
        String lastName = lastNameTxtField.getText();
        String address = addressTxtField.getText();
        User curUser = LoggedUser.getInstance().getUser();
        if(curUser == null) { // Not logged in : Sign Up
            User newuser = new User(username, email, password, phone);
            newuser.setFirstName(firstName);
            newuser.setLastName(lastName);
            newuser.setAddress(address);

            Boolean signedUp = databaseHandler.signUp(newuser);
            if(signedUp) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Signed Up");
                alert.setHeaderText(null);
                alert.setOnCloseRequest(dialogEvent -> {
                    try {
                        ViewsController.getInstance().openLoginScreen();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong SignUp Information");
                alert.show();
            }
        } else { // Logged in : Edit Profile
            curUser.setUserName(username);
            curUser.setEmail(email);
            curUser.setPassword(password);
            curUser.setPhone(phone);
            curUser.setFirstName(firstName);
            curUser.setLastName(lastName);
            curUser.setAddress(address);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Edited Your Profile");
            alert.setHeaderText(null);
            alert.setOnCloseRequest(dialogEvent -> {
                try {
                    ViewsController.getInstance().openControlPanelScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            alert.show();
        }
    }
}
