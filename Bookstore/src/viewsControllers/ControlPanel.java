package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import models.LoggedUser;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlPanel implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void generateReportClk(ActionEvent actionEvent) {
        // Todo : generate reports
    }

    public void searchBooksClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSearchForBooksScreen();
    }

    public void addBookClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openAddBookScreen();
    }

    public void logoutClk(ActionEvent actionEvent) throws IOException {
        LoggedUser.getInstance().logOut();
        ViewsController.getInstance().openLoginScreen();
    }

    public void editProfileClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSignUpScreen();
    }

    public void promoteUserClk(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Please enter the username:");
        dialog.setHeaderText(null);

        // Get The user name
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
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

    public void confirmOrderClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openConfirmOrdersScreen();
    }

    public void addPublisherClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openAddPublisherScreen();
    }
}
