package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
public static String emailid;

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
emailid="";
         connection=new DatabaseConnection();
        root=new Group();
        Header header=new Header();
        productPage products=new productPage();
        ListView<HBox> productList=products.showProducts();
        AnchorPane productPane=new AnchorPane();

        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);

        root.getChildren().addAll(header.root,productPane);

//       ResultSet res= connection.executeQuery("Select * from product");
//       while(res.next())
//       {
//           System.out.println(res.getString( "productname"));
//
//       }
//       int res2=connection.excuteUpdate("Insert Into product values(3,'Laptop',25000,'abhay@gmail.com')");
//       if(res2>0){
//           System.out.println("A new row is inserted");
//
//       }
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("header.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        primaryStage.setTitle("Ecommerce");
        //stage.setScene(scene);
      primaryStage.setScene(new Scene(root,500,500));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            try{
                connection.con.close();
                System.out.println("connection is closed");
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}