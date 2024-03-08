package com.example.csd214test3baswinsureshbabu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OrderPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CSD214AswinSureshbabu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static double CalculatetotalBill(double Bill){
        double toppingprice = 1.5;
        double toppingnumber = 2;
        double totaltoppingprice = toppingprice * toppingnumber;
        double price = 12;
        double fprice = totaltoppingprice + price;
        double tax = 0.15 * fprice;
        double totalBill = tax + fprice;
        return totalBill;
    }
}