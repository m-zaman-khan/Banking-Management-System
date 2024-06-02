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

public class LoanRequest {

    @FXML
    private Label Accountno;

    @FXML
    private Label Accountno_Label;

    @FXML
    private Label Amount;

    @FXML
    private TextField Amount_TextField;

    @FXML
    private Label AvailableBalance_Label;

    @FXML
    private TextField AvailableBalance_TextField;

    @FXML
    private Label NIC;

    @FXML
    private TextField NIC_TextField;

    @FXML
    private TextField Name;

    @FXML
    private Label Name_Label;

    @FXML
    private Button Request_Button;

    @FXML
    private Button Search_Button;

    @FXML
    private TextField Status;

    @FXML
    private Label Status_label;

    @FXML
    private Button back;

    @FXML
    void onRequestButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
        String sq="INSERT Into loan_request  (Name, AccountNumber, NIC, AvailableBalance, ReqAmount, Status) values (?,?,?,?,?,?)";
        PreparedStatement stat = con.prepareStatement(sq);

        stat.setString(1,Name.getText());
        stat.setString(2,Accountno_Label.getText());
        stat.setString(3,NIC_TextField.getText());
        stat.setString(4,AvailableBalance_TextField.getText());
        stat.setString(5,Amount_TextField.getText());
        stat.setString(6,Status.getText());

        stat.executeUpdate();
        stat.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loan");
        alert.setHeaderText(null);
        alert.setContentText("Loan Request Successfully");
        alert.showAndWait();
    }

    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

   /* @FXML
    void onesearchClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        String databaseUser="root";
        String databasePassword="zamanlew6732";
        String url="jdbc:mysql://localhost:3306/banking_mangement_system";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(url,databaseUser,databasePassword);
        String name =Name.getText();
        String selectQuery = "SELECT accNo, nic, iniDeposit FROM clients WHERE name = ?";
        PreparedStatement selectStatement = con.prepareStatement(selectQuery);
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) { // Move the cursor to the first row
            Accountno_Label.setText(resultSet.getString(1));
            NIC_TextField.setText(resultSet.getString(2));
            AvailableBalance_TextField.setText(resultSet.getString(3));
        }
    }*/
   @FXML
   void onesearchClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
       String databaseUser = "root";
       String databasePassword = "zamanlew6732";
       String url = "jdbc:mysql://localhost:3306/banking_mangement_system";
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection con = DriverManager.getConnection(url, databaseUser, databasePassword);

       String name = Name.getText();
       String selectQuery = "SELECT accNo, nic, iniDeposit FROM clients WHERE name = ?";
       PreparedStatement selectStatement = con.prepareStatement(selectQuery);
       selectStatement.setString(1, name);
       ResultSet resultSet = selectStatement.executeQuery();

       if (resultSet.next()) {
           Accountno_Label.setText(resultSet.getString(1));
           NIC_TextField.setText(resultSet.getString(2));
           AvailableBalance_TextField.setText(resultSet.getString(3));

           // Fetch data from the loan_request table
           String loanRequestQuery = "SELECT * FROM loan_request WHERE Name = ?";
           PreparedStatement loanRequestStatement = con.prepareStatement(loanRequestQuery);
           loanRequestStatement.setString(1, name);
           ResultSet loanRequestResultSet = loanRequestStatement.executeQuery();

           if (loanRequestResultSet.next()) {
               // Set the fields as uneditable

               NIC_TextField.setEditable(false);
               AvailableBalance_TextField.setEditable(false);
               Amount_TextField.setEditable(false);
               Status.setEditable(false);
               Request_Button.setDisable(true);

               // Populate the fields with data from the loan_request table
               Accountno_Label.setText(loanRequestResultSet.getString("AccountNumber"));
               NIC_TextField.setText(loanRequestResultSet.getString("NIC"));
               AvailableBalance_TextField.setText(loanRequestResultSet.getString("AvailableBalance"));
               Amount_TextField.setText(loanRequestResultSet.getString("ReqAmount"));
               Status.setText(loanRequestResultSet.getString("Status"));
           }

           loanRequestResultSet.close();
           loanRequestStatement.close();
       } else {
           // Show a dialog box indicating that the name does not exist
           Name.clear();
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Name Not Found");
           alert.setContentText("The provided name does not exist in the database.");
           alert.showAndWait();
       }

       resultSet.close();
       selectStatement.close();
       con.close();
   }

}
