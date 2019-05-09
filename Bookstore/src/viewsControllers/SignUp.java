package viewsControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SignUpClk(ActionEvent actionEvent) {

    }

    public void backClk(ActionEvent actionEvent) {
        ViewsController.getInstance().openLoginScreen();
    }
}
