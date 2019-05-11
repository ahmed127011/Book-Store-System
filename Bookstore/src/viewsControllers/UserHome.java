package viewsControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import models.LoggedUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHome implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void searchBooksClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSearchForBooksScreen();
    }

    public void logoutClk(ActionEvent actionEvent) throws IOException {
        LoggedUser.getInstance().logOut();
        ViewsController.getInstance().openLoginScreen();
    }

    public void editProfileClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSignUpScreen();
    }
}
