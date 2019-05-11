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
import models.UserOrders;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShoppingCart implements Initializable {
    public VBox ordersVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewsController.getInstance().setShoppingCartController(this);
    }

    public void onSceneShow() {
        ordersVBox.getChildren().clear();
        DatabaseHandler databaseHandler = MysqlDatabaseHandler.getInstance();
        List<UserOrders> userOrders = databaseHandler.ShowShoppingCardInfo();
        HBox titleHBox = new HBox();
        titleHBox.setPrefHeight(25);
        titleHBox.getChildren().add(getLabel("ISBN",150.0));

        titleHBox.getChildren().add(getLabel("Quantity",150.0));
        titleHBox.getChildren().add(getLabel("Delete",150.0));

        ordersVBox.getChildren().add(titleHBox);
        for (UserOrders userOrder :
                userOrders) {
            HBox orderHBox = new HBox();
            orderHBox.getChildren().add(getLabel(userOrder.getIsbn(),150.0));
            orderHBox.getChildren().add(getLabel(String.valueOf(userOrder.getQuantity()),150.0));
            Button confirmBtn = new Button("Delete");
            confirmBtn.setPrefWidth(150);
            confirmBtn.setOnAction(actionEvent -> {
                databaseHandler.removeFromShoppingCard(userOrder.getIsbn());
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

    public void backClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openSearchForBooksScreen();
    }

    public void checkOutClk(ActionEvent actionEvent) throws IOException {
        ViewsController.getInstance().openCheckOutScreen();
    }
}
