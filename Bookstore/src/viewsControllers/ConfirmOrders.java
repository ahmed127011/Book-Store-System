package viewsControllers;

import database.DatabaseHandler;
import database.MysqlDatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.LibraryOrders;
import models.LoggedUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConfirmOrders implements Initializable {
    public VBox ordersVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setConfirmOrdersController(this);
    }

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openControlPanelScreen();
    }

    public void onSceneShow() {
        ordersVBox.getChildren().clear();
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<LibraryOrders> orders = databaseHandler.getOrders();
        HBox titleHBox = new HBox();
        titleHBox.setPrefHeight(25);
        titleHBox.getChildren().add(getLabel("ISBN",150.0));
        //titleHBox.getChildren().add(getLabel("Order Date",150.0));
        titleHBox.getChildren().add(getLabel("Quantity",150.0));
        titleHBox.getChildren().add(getLabel("Confirm",150.0));

        ordersVBox.getChildren().add(titleHBox);
        for (LibraryOrders order :
                orders) {
            HBox orderHBox = new HBox();
            orderHBox.getChildren().add(getLabel(order.getIsbn(),150.0));
            //orderHBox.getChildren().add(getLabel(order.getOrderedDate().toString(),150.0));
            orderHBox.getChildren().add(getLabel(String.valueOf(order.getQuantity()),150.0));
            Button confirmBtn = new Button("Confirm");
            confirmBtn.setPrefWidth(150);
            confirmBtn.setOnAction(actionEvent -> {
                databaseHandler.confirmOrder(order);
                ordersVBox.getChildren().remove(orderHBox);
            });
            orderHBox.getChildren().add(confirmBtn);

            ordersVBox.getChildren().add(orderHBox);
        }
    }

    private Label getLabel(String text, Double width) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(width);
        return label;
    }
}
