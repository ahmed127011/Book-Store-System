package viewsControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField userNameTxtField;

    @FXML
    private TextField passwordTxtField;

    public void loginClk(ActionEvent actionEvent) {
        System.out.println(userNameTxtField.getText());
        System.out.println(passwordTxtField.getText());
    }

    public void SignUpClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSignUpScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
