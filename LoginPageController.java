package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent even)  throws SQLException,IOException
    {
        String query=String.format("Select * from user where emailid='%s' AND pass= '%s'" ,email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);

        if (res.next()) {
            String userType=res.getString("userType");
           /// HelloApplication.emailid=res.getString("emailid");
            HelloApplication.emailid=res.getString("emailid");
            if(userType.equals("buyer")){
                System.out.println("Log in as a buyer");
Header header=new Header();
                productPage products=new productPage();
                ListView<HBox> productList=products.showProducts();
                AnchorPane productPane=new AnchorPane();

                productPane.setLayoutX(80);
                productPane.setLayoutY(80);
                productPane.getChildren().add(productList);
             HelloApplication.root.getChildren().clear();

               HelloApplication.root.getChildren().addAll(header.root,productPane);

            }
            else{
                System.out.println("log in as a seller");

                AnchorPane sellerpage= FXMLLoader.load((getClass().getResource("sellerpage.fxml")));
                HelloApplication.root.getChildren().add(sellerpage);
            }

        }
        else{
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);


                dialog.setContentText("Login Failed!! Please try Again");



            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }

    }
}
