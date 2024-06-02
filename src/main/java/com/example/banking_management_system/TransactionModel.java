package com.example.banking_management_system;

import javafx.scene.control.TextField;

public class TransactionModel {
    String Name_Col,AccNo_Col,AccType_Col;

    String Deposit;
    String WithDraw;

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    String Balance;

    public String getDeposit() {
        return Deposit;
    }

    public void setDeposit(String deposit) {
        Deposit = deposit;
    }

    public String getWithDraw() {
        return WithDraw;
    }

    public void setWithDraw(String withdraw) {
        WithDraw = withdraw;
    }

    public TransactionModel(String name_Col, String accNo_Col, String accType_Col, String deposit, String withdraw, String balance ) {
        this.Name_Col = name_Col;
        this.AccNo_Col = accNo_Col;
        this.AccType_Col = accType_Col;
        this.Deposit = deposit;
        this.WithDraw = withdraw;
        this.Balance=balance;

    }



    public String getName_Col() {
        return Name_Col;
    }

    public void setName_Col(String name_Col) {
        Name_Col = name_Col;
    }

    public String getAccNo_Col() {
        return AccNo_Col;
    }

    public void setAccNo_Col(String accNo_Col) {
        AccNo_Col = accNo_Col;
    }

    public String getAccType_Col() {
        return AccType_Col;
    }

    public void setAccType_Col(String accType_Col) {
        AccType_Col = accType_Col;
    }


}
