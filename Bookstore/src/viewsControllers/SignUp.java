package viewsControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SignUpClk(ActionEvent actionEvent) {

    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        File file = new File("/home/ayman/Projects/University/Book_Store_System/Bookstore/src/views/login.fxml");
        Parent root = FXMLLoader.load(file.toURI().toURL());
        Main.primaryStage.setScene(new Scene(root));
    }
}
