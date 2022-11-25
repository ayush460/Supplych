package com.example.ecommerce;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {

    @FXML
    Button loginbutton;
    @FXML
    Label email;

    @FXML


    TextField searchtext;
@FXML
Button logout;
@FXML
    public void initialize(){
        if(!HelloApplication.emailid.equals("")){
            loginbutton.setOpacity(0);
            email.setText(HelloApplication.emailid);
        }
    }
    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane loginpage= FXMLLoader.load((getClass().getResource("Login.fxml")));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void search(MouseEvent event) throws IOException, SQLException {
        Header header=new Header();
        productPage products=new productPage();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(products.showProductsbyName(searchtext.getText()));
        productPane.setLayoutX(150);
        productPane.setLayoutY(150);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
    @FXML
    public  void logout(MouseEvent event) throws IOException,SQLException{
    if(logout.getOpacity()==0)
        logout.setOpacity(1);
    logout.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            HelloApplication.emailid="";
            logout.setOpacity(0);

            try{
                Header header=new Header();
                HelloApplication.root.getChildren().add(header.root);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    });


    }
}
