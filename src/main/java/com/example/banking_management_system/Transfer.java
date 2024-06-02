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
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.io.IOException;
import java.sql.*;

public class Transfer {

    @FXML
    private Label Accountno;

    @FXML
    private Label Accountno_Label;

    @FXML
    private Label Amount_Label;

    @FXML
    private Label Amount_Label1;

    @FXML
    private TextField Amount_TextField;

    @FXML
    private TextField Accountno_TextField;

    @FXML
    private Label AvailableBalance_Label;

    @FXML
    private TextField AvailableBalance_TextField;

    @FXML
    private TextField Name;

    @FXML
    private Label Name_Label;

    @FXML
    private Button Search_Button;

    @FXML
    private TextField TotalBalance_TExtfield;

    @FXML
    private Button Total_Button;

    @FXML
    private Button Transfer_Button;

    @FXML
    private Button back;
    public float currentTransfer;
    public float amount;
    public float updatedTranfer;

    @FXML
    void onTotalButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String name = Name.getText();
        String selectQuery = "SELECT iniDeposit FROM clients WHERE name = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();


        if (resultSet.next()) {
            currentTransfer = Float.parseFloat(resultSet.getString("iniDeposit"));
            amount = Float.parseFloat(Amount_TextField.getText());
            updatedTranfer = currentTransfer - amount;
            TotalBalance_TExtfield.setText(String.valueOf(updatedTranfer));}
        selectStatement.close();
        resultSet.close();
        con.close();

    }

    @FXML
    void onTransferButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String accountNumber = Accountno_TextField.getText();
        float transferAmount = Float.parseFloat(Amount_TextField.getText());
        String name = Name.getText();

        // Check if the account number exists in the clients table
        String selectAccountQuery = "SELECT COUNT(*) FROM clients WHERE accNo = ?";
        PreparedStatement selectAccountStatement = con.prepareStatement(selectAccountQuery);
        selectAccountStatement.setString(1, accountNumber);
        ResultSet accountResultSet = selectAccountStatement.executeQuery();

        if (accountResultSet.next()) {
            int accountCount = accountResultSet.getInt(1);
            if (accountCount > 0) {
                // Update the inideposit for the specified account number
                String updateAccountQuery = "UPDATE clients SET inideposit = inideposit + ? WHERE accNo = ?";
                PreparedStatement updateAccountStatement = con.prepareStatement(updateAccountQuery);
                updateAccountStatement.setFloat(1, transferAmount);
                updateAccountStatement.setString(2, accountNumber);
                updateAccountStatement.executeUpdate();

                // Update the inideposit for the specified name
                String updateNameQuery = "UPDATE clients SET inideposit = inideposit - ? WHERE name = ?";
                PreparedStatement updateNameStatement = con.prepareStatement(updateNameQuery);
                updateNameStatement.setFloat(1, transferAmount);
                updateNameStatement.setString(2, name);
                updateNameStatement.executeUpdate();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Profile Updated");
                alert.setHeaderText(null);
                alert.setContentText("Transferred Successfully");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Profile Updated");
                alert.setHeaderText(null);
                alert.setContentText("Account No. does not exist");
                alert.showAndWait();

            }
        }

        con.close();
    }

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);


    }

    @FXML
    void onesearchClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        String databaseUser = "root";
        String databasePassword = "zamanlew6732";
        String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

        String n = Name.getText();
        String sq = "SELECT clients.accNo,clients.iniDeposit FROM clients WHERE name = ?";
        PreparedStatement stat = con.prepareStatement(sq);
        stat.setString(1, n);
        ResultSet resultSet = stat.executeQuery();

        if(resultSet.next()) { // Move the cursor to the first row
            String accountNumber = resultSet.getString(1);// Retrieve the data from the first column
            String balance = resultSet.getString(2);
            Accountno_Label.setText(accountNumber);
            AvailableBalance_TextField.setText(balance);
        }else {
            // Show a dialog box indicating that the name does not exist
            Name.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name Not Found");
            alert.setContentText("The provided name does not exist in the database.");
            alert.showAndWait();

        }
    }

}


