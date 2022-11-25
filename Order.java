package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String productid) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(orderid) from orders");
        int orderid=0;
        if(res.next())
            orderid=res.getInt("max(orderid)")+1;

        String query=String.format("Insert Into orders values(%s,%s,'%s')",orderid,productid,HelloApplication.emailid);
    int response=HelloApplication.connection.excuteUpdate(query);
    if(response>0){
        Dialog<String> dialog=new Dialog<>();
        dialog.setTitle("Order");
        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);


        dialog.setContentText("Your Order is Placed");



        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
        System.out.println("order is placed");
    }
    }
}
