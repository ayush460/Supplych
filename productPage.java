package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productPage {

    ListView<HBox> products;
    ListView<HBox> showProductsbyName(String search) throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from product");
        products=new ListView<>();
        Label name=new Label();
        Label price=new Label();
        Label id=new Label();
        HBox details=new HBox();
        name.setMinWidth(50);
        id.setMinWidth(50);
        price.setMinWidth(50);
        name.setText("productname");

        price.setText("price");
        id.setText("productid");
        details.getChildren().addAll(id,name,price);
        productList.add(details);
        while(res.next()) {
            if (res.getString("productname").contains(search.toLowerCase())) {


                Label productname = new Label();
                Label productPrice = new Label();
                Label productID = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();
                productname.setMinWidth(50);
                productID.setMinWidth(50);
                productPrice.setMinWidth(50);
                buy.setText("Buy");
                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override

                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailid.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);


                            dialog.setContentText("Login in First before placing the order");


                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {
                            try {

                                Order place = new Order();
                                place.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            System.out.println("You are Clicked buy button");
                        }


                    }
                });
                productname.setText(res.getString("productname"));

                productPrice.setText(res.getString("price"));
                productID.setText("" + res.getInt("productid"));
                productDetails.getChildren().addAll(productID, productname, productPrice, buy);
                productList.add(productDetails);

            }
        }
        System.out.println(productList.size());
        if(productList.size()==1){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);


            dialog.setContentText("No product is Available for Your Search ||");



            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }

    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("Select * from product");
        products=new ListView<>();
        Label name=new Label();
        Label price=new Label();
        Label id=new Label();
        HBox details=new HBox();
        name.setMinWidth(50);
        id.setMinWidth(50);
       price.setMinWidth(50);
        name.setText("productname");

       price.setText("price");
        id.setText("productid");
        details.getChildren().addAll(id,name,price);
        productList.add(details);
while(res.next()){
    Label productname=new Label();
    Label productPrice=new Label();
    Label productID=new Label();
    Button buy=new Button();
    HBox productDetails=new HBox();
    productname.setMinWidth(50);
    productID.setMinWidth(50);
    productPrice.setMinWidth(50);
    buy.setText("Buy");
    buy.setOnAction(new EventHandler<ActionEvent>() {
        @Override

        public void handle(ActionEvent actionEvent) {
            if(HelloApplication.emailid.equals("")){
                Dialog<String> dialog=new Dialog<>();
                dialog.setTitle("Login");
                ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);


                dialog.setContentText("Login in First before placing the order");



                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
            else{
                try {

                    Order place = new Order();
                    place.placeOrder(productID.getText());
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println("You are Clicked buy button");
            }


        }
    });
    productname.setText(res.getString("productname"));

    productPrice.setText(res.getString("price"));
    productID.setText(""+res.getInt("productid"));
    productDetails.getChildren().addAll(productID,productname,productPrice,buy);
    productList.add(productDetails);

}
products.setItems(productList);
return products;
    }
}
