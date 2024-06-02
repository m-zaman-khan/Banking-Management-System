package com.example.banking_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class Profile {

    @FXML
    private TextField AccountNo;

    @FXML
    private TextField AccountType;

    @FXML
    private TextField Address;

    @FXML
    private Button Back;

    @FXML
    private TextField Country;

    @FXML
    private TextField DOB;

    @FXML
    private Button Edit;

    @FXML
    private TextField Gender;

    @FXML
    private TextField Ini_Deposit;

    @FXML
    private TextField NIC;

    @FXML
    private TextField Name;

    @FXML
    private TextField Phone_No;
    @FXML
    private TextField username;
    @FXML
    private Button Update;

    @FXML
    void onBackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    @FXML
    void oneUpdateClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String updateQuery = "UPDATE clients SET name=?, DOB=?, country=?, gender=?, nic=?, accType=?, phone=?, address=? WHERE name=?";
        PreparedStatement updateStatement = con.prepareStatement(updateQuery);
        updateStatement.setString(1, Name.getText());
        updateStatement.setString(2, DOB.getText());
        updateStatement.setString(3, Country.getText());
        updateStatement.setString(4, Gender.getText());
        updateStatement.setString(5, NIC.getText());
        updateStatement.setString(6, AccountType.getText());
        updateStatement.setString(7, Phone_No.getText());
        updateStatement.setString(8, Address.getText());
        updateStatement.setString(9, username.getText());

        int rowsUpdated = updateStatement.executeUpdate();
        if (rowsUpdated > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Profile Updated");
            alert.setHeaderText(null);
            alert.setContentText("Profile updated successfully!");

            // Clear the fields
            Name.clear();
            DOB.clear();
            Country.clear();
            Gender.clear();
            NIC.clear();
            AccountNo.clear();
            AccountType.clear();
            Ini_Deposit.clear();
            Phone_No.clear();
            Address.clear();

            alert.showAndWait();
        } else {
            System.out.println("Failed to update profile.");
        }
        updateStatement.close();
        con.close();
    }



    @FXML
    void oneEditClicked(ActionEvent event) {
        Name.setEditable(true);
        DOB.setEditable(true);
        Country.setEditable(true);
        AccountType.setEditable(true);
        Phone_No.setEditable(true);
        Address.setEditable(true);
    }
    @FXML
    void onesearchClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
        String name =username.getText();
        String selectQuery = "SELECT * FROM clients WHERE name = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();
        if (resultSet.next()) { // Move the cursor to the first row

            Name.setText(resultSet.getString(1));
            DOB.setText(resultSet.getString(2));
            Country.setText(resultSet.getString(3));
            Gender.setText(resultSet.getString(4));
            NIC.setText(resultSet.getString(5));
            AccountNo.setText(resultSet.getString("accNo"));
            AccountType.setText(resultSet.getString("accType"));
            Ini_Deposit.setText(resultSet.getString("iniDeposit"));
            Phone_No.setText(resultSet.getString("phone"));
            Address.setText(resultSet.getString("address"));

          }else {
        // Show a dialog box indicating that the name does not exist
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Name Not Found");
        alert.setContentText("The provided name does not exist in the database.");
        alert.showAndWait();
    }

    }
}
