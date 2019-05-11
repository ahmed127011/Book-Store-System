package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CheckOut implements Initializable {
    public DatePicker expireDatePicker;
    public TextField cardNumberTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitClk(ActionEvent actionEvent) {
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        if (databaseHandler.Checkout(cardNumberTxtField.getText(), Date.valueOf(expireDatePicker.getValue()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Checked out ");
            alert.setHeaderText(null);
            alert.show();
            alert.setOnCloseRequest(dialogEvent -> {
                try {
                    ViewsController.getInstance().openSearchForBooksScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error While checking out ");
            alert.setHeaderText(null);
            alert.show();
        }
    }
    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSearchForBooksScreen();
    }
}
