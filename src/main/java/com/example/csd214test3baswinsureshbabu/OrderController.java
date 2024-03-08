package com.example.csd214test3baswinsureshbabu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class OrderController implements Initializable {
    public TextField AnoName;
    public TextField AnoNumber;
    public TextField AnoTopping;
    public TextField AnoId;
    public RadioButton rButtonS;
    public RadioButton rButtonM;
    public RadioButton rButtonL;
    public RadioButton rButtonXL;


    @FXML
    private TableView<Order> order;
    @FXML
    private TableColumn<Order,Integer > id;
    @FXML
    private TableColumn<Order, String> name;
    @FXML
    private TableColumn<Order,Integer> number;
    @FXML
    private TableColumn<Order,String> size;
    @FXML
    private TableColumn<Order,Integer> toppings;
    @FXML
    private TableColumn<Order,Integer> bill;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<Order> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Order,Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Order,String>("name"));
        number.setCellValueFactory(new
                PropertyValueFactory<Order,Integer>("number"));
        size.setCellValueFactory(new
                PropertyValueFactory<Order,String>("size"));
        toppings.setCellValueFactory(new
                PropertyValueFactory<Order,Integer>("toppings"));
        bill.setCellValueFactory(new
                PropertyValueFactory<Order,Integer>("bill"));

        order.setItems(list);
    }

    @FXML
    protected void ViewTable(){populateTable();}
    public void populateTable() {

        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizzaorderingapplication";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `order`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String CustomerName = resultSet.getString("CustomerName");
                int MobileNumber = resultSet.getInt("MobileNumber");
                String PizzaSize = resultSet.getString("PizzaSize");
                int NumberOfToppings = resultSet.getInt("NumberOfToppings");
                int TotalBill = resultSet.getInt("TotalBill");
                order.getItems().add(new Order(ID, CustomerName, MobileNumber, PizzaSize, NumberOfToppings, TotalBill));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String GetSize(){
        String size = " ";

        if (rButtonS.isSelected()){
            size = "S";
        } else if (rButtonM.isSelected()) {
            size = "M";
        } else if (rButtonL.isSelected()) {
            size = "L";
        }else {
            size = "XL";
        }
        return size;



    }

    public double TotalBill(String size,int Topping){
        double basePrice;
        switch (size){
            case "S":
                basePrice = 8.00;
                break;
            case "M":
                basePrice = 10.00;
                break;
            case "L":
                basePrice = 12.00;
                break;
            case "XL":
                basePrice = 15.00;
                break;
            default:
                basePrice = 0.00;
                break;
        }

        double priceAfterTopping = 1.50 * Topping;
        double beforeTax = basePrice + priceAfterTopping;
        double tax = 0.15 * beforeTax;
        double totalBill = beforeTax + tax;
        return totalBill;
    }


    public void InsertTable() {

        String Name =AnoName.getText();
        int Number = Integer.parseInt(AnoNumber.getText());
        int Topping = Integer.parseInt(AnoTopping.getText());


        InsertData(Name, Number, Topping);
    }

    public void InsertData(String Name, int Number, int Topping){

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizzaorderingapplication";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            String size = GetSize();
            double ValueTotalBill = TotalBill(size, Topping);

// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `order`( `CustomerName`, `MobileNumber`, `PizzaSize`, `NumberOfToppings`, `TotalBill`) VALUES ('"+Name+"','"+Number+"','"+size+"','"+Topping+"','"+ValueTotalBill+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteTable(ActionEvent actionEvent) {
        int Id = Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizzaorderingapplication";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `order` WHERE ID = '"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();
// Populate the table with data from the database



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTable(ActionEvent actionEvent) {

        int Id = Integer.parseInt(AnoId.getText());
        String Name =AnoName.getText();
        int Number = Integer.parseInt(AnoNumber.getText());
        int Topping = Integer.parseInt(AnoTopping.getText());

        UpdateData(Id, Name, Number, Topping);
    }

    public void UpdateData(int Id, String Name, int Number, int Topping){

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizzaorderingapplication";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            String size = GetSize();
            double ValueTotalBill = TotalBill(size, Topping);
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `order` SET `CustomerName`='"+Name+"',`MobileNumber`='"+Number+"',`PizzaSize`='"+size+"',`NumberOfToppings`='"+Topping+"',`TotalBill`='"+ValueTotalBill+"' WHERE `ID`='"+Id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();
// Populate the table with data from the database



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void LoadTable(ActionEvent actionEvent) {

        int Id=Integer.parseInt(AnoId.getText());

        String jdbcUrl = "jdbc:mysql://localhost:3306/pizzaorderingapplication";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `order` WHERE ID='"+Id+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String CustomerName = resultSet.getString("CustomerName");
                int MobileNumber = resultSet.getInt("MobileNumber");
                String PizzaSize = resultSet.getString("PizzaSize");
                int NumberOfToppings = resultSet.getInt("NumberOfToppings");
                int TotalBill = resultSet.getInt("TotalBill");

                AnoName.setText(CustomerName);
                AnoNumber.setText(String.valueOf(MobileNumber));
                AnoTopping.setText(String.valueOf(NumberOfToppings));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}