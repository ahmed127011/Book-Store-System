package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import models.LoggedUser;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlPanel implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void generateReportClk(ActionEvent actionEvent) {
        // Todo : generate reports
    }

    public void searchBooksClk(ActionEvent actionEvent) {
        // Todo : open search for books
    }

    public void addBookClk(ActionEvent actionEvent) {
        // Todo : open add book
    }

    public void logoutClk(ActionEvent actionEvent) {
        LoggedUser.getInstance().logOut();
        ViewsController.getInstance().openLoginScreen();
    }

    public void editProfileClk(ActionEvent actionEvent) {
    }

    public void promoteUserClk(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Please enter the username:");
        dialog.setHeaderText(null);

        // Get The user name
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            DatabaseHandler databaseHandler = new MysqlDatabaseHandler();
            if(databaseHandler.promoteUser(result.get())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully promoted "+ result.get());
                alert.setHeaderText(null);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error While promoting "+ result.get());
                alert.setHeaderText(null);
                alert.show();
            }
        }
    }
}
