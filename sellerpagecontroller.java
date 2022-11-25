package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.ecommerce.HelloApplication.connection;

public class sellerpagecontroller {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;
    @FXML
    public void Add(MouseEvent event) throws SQLException{
        ResultSet res=HelloApplication.connection.executeQuery("Select max(productid) from product");
        int productid=0;
        if(res.next())
            productid=res.getInt("max(productid)")+1;
        String query=String.format("Insert Into product values(%s,'%s','%s','%s')",productid,name.getText(),price.getText(),email.getText());
int response= connection.excuteUpdate(query);
        Dialog<String> dialog=new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
if(response>0){

    dialog.setContentText("The Product is Added");

}
else{


    dialog.setContentText("The Product is not Added");

}
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
