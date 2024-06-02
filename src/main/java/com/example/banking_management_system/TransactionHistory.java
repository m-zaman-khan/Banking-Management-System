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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TransactionHistory implements Initializable {


    @FXML
    private TableView<TransactionModel> table;

    @FXML
    private TableColumn<TransactionModel, String> AccNo_Col;

    @FXML
    private TableColumn<TransactionModel, String> AccType_Col;

    @FXML
    private TableColumn<TransactionModel, String> Deposit;

    @FXML
    private TableColumn<TransactionModel, String> Name_Col;

    @FXML
    private TableColumn<TransactionModel, String> WithDraw;
    @FXML
    private TableColumn<TransactionModel, String> Balance;

    @FXML
    private Button back;


    ObservableList<TransactionModel> listview= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name_Col.setCellValueFactory(new PropertyValueFactory<>("Name_Col"));
        AccNo_Col.setCellValueFactory(new PropertyValueFactory<>("AccNo_Col"));
        AccType_Col.setCellValueFactory(new PropertyValueFactory<>("AccType_Col"));
        Deposit.setCellValueFactory(new PropertyValueFactory<>("Deposit"));
        WithDraw.setCellValueFactory(new PropertyValueFactory<>("WithDraw"));
        Balance.setCellValueFactory(new PropertyValueFactory<>("Balance"));




        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_mangement_system","root","zamanlew6732");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT clients.name, clients.accNo, clients.accType, transaction_list.deposit, transaction_list.withdraw, clients.iniDeposit FROM clients INNER JOIN transaction_list ON clients.accNo = transaction_list.accNo");




            while(resultSet.next()){
                listview.add(new TransactionModel(
                        resultSet.getString("name"),
                        resultSet.getString("accNo"),
                        resultSet.getString("accType"),
                        resultSet.getString("deposit"),
                        resultSet.getString("withdraw"),
                        resultSet.getString("iniDeposit")


                ));
                table.setItems(listview);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
