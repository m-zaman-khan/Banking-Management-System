package com.example.banking_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ManagerLoanApproval implements Initializable {
    @FXML
    private TableView<LoanModel> table;

    @FXML
    private TableColumn<LoanModel, String> account_no;

    @FXML
    private TableColumn<LoanModel, String> avail_bal;

    @FXML
    private Button back;

    @FXML
    private TableColumn<LoanModel, String> name;

    @FXML
    private TableColumn<LoanModel, String> nic;

    @FXML
    private TableColumn<LoanModel, String> req_amount;

    @FXML
    private TableColumn<LoanModel, String> status;
    ObservableList<LoanModel> listview= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        account_no.setCellValueFactory(new PropertyValueFactory<>("account_no"));
        nic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        avail_bal.setCellValueFactory(new PropertyValueFactory<>("avail_bal"));
        req_amount.setCellValueFactory(new PropertyValueFactory<>("req_amount"));
        status.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList("Approved", "Decline", "Pending")));
        status.setOnEditCommit((TableColumn.CellEditEvent<LoanModel, String> event) -> {
            TablePosition<LoanModel, String> pos = event.getTablePosition();
            String newStatus = event.getNewValue();
            int row = pos.getRow();
            LoanModel loanModel = event.getTableView().getItems().get(row);
            loanModel.setStatus(newStatus);

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_mangement_system", "root", "zamanlew6732");
                    PreparedStatement statement = connection.prepareStatement("UPDATE loan_request SET Status = ? WHERE AccountNumber = ?");
                    statement.setString(1, loanModel.getStatus());
                    statement.setString(2, loanModel.getAccount_no());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        });


        table.setEditable(true); // Enable editing in the table
        table.setItems(listview);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_mangement_system", "root", "zamanlew6732");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Name, AccountNumber, NIC, AvailableBalance, ReqAmount, Status FROM loan_request");

            while (resultSet.next()) {
                listview.add(new LoanModel(
                        resultSet.getString("Name"),
                        resultSet.getString("AccountNumber"),
                        resultSet.getString("NIC"),
                        resultSet.getString("AvailableBalance"),
                        resultSet.getString("ReqAmount"),
                        resultSet.getString("Status")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onbackClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
