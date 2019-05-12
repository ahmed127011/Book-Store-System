package controllers;

import models.UserOrders;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;


/**
 * Created by khaledabdelfattah on 5/7/19.
 */
public class ShoppingCart {
    private List<UserOrders> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addOrder(UserOrders order) {
        for(UserOrders oldOrder:cart){
            if(oldOrder.equals(order)){
                oldOrder.setQuantity(oldOrder.getQuantity()+order.getQuantity());
                return;
            }
        }
        cart.add(order);
    }

    public void removeOrder(UserOrders order) {
        cart.remove(order);
    }

    public List<UserOrders> getOrders() {
        return this.cart;
    }


}
