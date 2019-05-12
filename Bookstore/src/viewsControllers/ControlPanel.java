package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import models.LoggedUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlPanel implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private void generateAlert(String title,String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(title);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);


        TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane alertContent = new GridPane();
        alertContent.setMaxWidth(Double.MAX_VALUE);
        alertContent.add(textArea, 0, 0);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(alertContent);

        alert.showAndWait();

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

    public void totlalSalesClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        generateAlert("The total sales for books in the previous month", String.valueOf(databaseHandler.getprevMonthSales()));
    }

    public void topCustomersClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        generateAlert("The top 5 customers who purchase the most purchase amount in descending order for the last\n" +
                "three months", databaseHandler.getTop5Customers());
    }

    public void topBooksClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        generateAlert("The top 10 selling books for the last three months", databaseHandler.viewTopSellingBooks());
    }
}
