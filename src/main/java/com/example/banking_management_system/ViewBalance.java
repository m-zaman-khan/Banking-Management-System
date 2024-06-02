package com.example.banking_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ViewBalance {

    @FXML
    private Label Accountno;

    @FXML
    private Label Accountno_label;

    @FXML
    private Label Balance;

    @FXML
    private Label Balance_Label;

    @FXML
    private Label Name;

    @FXML
    private TextField Name_TextField;

    @FXML
    private Button back;

    @FXML
    private Button search;

    @FXML
    void onSearchClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
        String name =Name_TextField.getText();
        String selectQuery = "SELECT accNo, iniDeposit FROM clients WHERE name = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();

        if(resultSet.next()) { // Move the cursor to the first row
            String accountNumber = resultSet.getString(1);// Retrieve the data from the first column
            String balance = resultSet.getString(2);
            Accountno_label.setText(accountNumber);
            Balance_Label.setText(balance);
        }else {
            // Show a dialog box indicating that the name does not exist
            Name_TextField.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name Not Found");
            alert.setContentText("The provided name does not exist in the database.");
            alert.showAndWait();

        }
    }


    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
