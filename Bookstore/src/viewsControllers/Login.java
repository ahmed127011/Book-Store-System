package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import models.LoggedUser;
import models.User;
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
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        String username = userNameTxtField.getText();
        String password = passwordTxtField.getText();
        Boolean loggedin = databaseHandler.login(username,password);
        if(loggedin) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Signed In");
            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                @Override
                public void handle(DialogEvent dialogEvent) {
                    if(LoggedUser.getInstance().getUser().getIsManger()) {
                        try {
                            ViewsController.getInstance().openControlPanelScreen();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else  {
                        // Todo : go to user Home page
                    }
                }
            });
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong login Information");
            alert.show();
        }
    }

    public void SignUpClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSignUpScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
