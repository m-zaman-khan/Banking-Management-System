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

public class ChangePin {

    @FXML
    private Label AvailableBalance_Label;

    @FXML
    private TextField CNewPin_TextField;

    @FXML
    private Button Change_Button;

    @FXML
    private Button Clear_Button;

    @FXML
    private Label Newpin;

    @FXML
    private TextField Newpin_TextFIeld;

    @FXML
    private Label Oldpin_Label;

    @FXML
    private TextField Oldpin_TextField;



    @FXML
    private Button back;

    @FXML
    private Label name_Label;

    @FXML
    private TextField name_TextField;

    @FXML
    void onChangeButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String n = name_TextField.getText();
        String sq = "SELECT clients.pin FROM clients WHERE name = ?";
        PreparedStatement stat = con.prepareStatement(sq);
        stat.setString(1, n);
        ResultSet resultSet = stat.executeQuery();
        if (resultSet.next()) { // Move the cursor to the first row
            String oldPin = resultSet.getString(1); // Retrieve the data from the first column
            String newPin = Newpin_TextFIeld.getText();
            String confirmPin = CNewPin_TextField.getText();

            if (newPin.equals(confirmPin)) { // Verify new pin fields match
                String updateQuery = "UPDATE clients SET pin = ? WHERE name = ?";
                PreparedStatement updateStat = con.prepareStatement(updateQuery);
                updateStat.setString(1, newPin);
                updateStat.setString(2, n);
                updateStat.executeUpdate();
                updateStat.close();

                // Display a success message or perform further actions
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pin Updated");
                alert.setHeaderText(null);
                alert.setContentText("Pin updated successfully!");
                alert.showAndWait();
            } else {
                // New pin fields don't match, display an error message or handle accordingly
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Don't Match");
                alert.setHeaderText(null);
                alert.setContentText("New pin fields don't match.");
                alert.showAndWait();
            }
        } else {
            // No record found for the provided name, display an error message or handle accordingly
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record not found!");
            alert.setHeaderText(null);
            alert.setContentText("No record found for the provided name..");
            alert.showAndWait();
            System.out.println("No record found for the provided name.");
        }
        resultSet.close();
        stat.close();
        con.close();
    }

    @FXML
    void onClearButtonClicked(ActionEvent event) {
        name_TextField.setText(null);
        Oldpin_TextField.setText(null);
        Newpin_TextFIeld.setText(null);
        CNewPin_TextField.setText(null);
    }

    @FXML
    void onSearchButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String n = name_TextField.getText();
        String sq = "SELECT clients.pin FROM clients WHERE name = ?";
        PreparedStatement stat = con.prepareStatement(sq);
        stat.setString(1, n);
        ResultSet resultSet = stat.executeQuery();
        while (resultSet.next()) {
            String pi = resultSet.getString(1);
            Oldpin_TextField.setText(pi);
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