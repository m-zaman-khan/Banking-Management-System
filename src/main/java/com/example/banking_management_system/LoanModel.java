package com.example.banking_management_system;

public class LoanModel {
    String name;
    String account_no;
    String nic;
    String avail_bal;
    String req_amount;



    String status;

    public LoanModel(String name, String account_no, String nic, String avail_bal, String req_amount, String status) {
        this.name = name;
        this.account_no = account_no;
        this.nic = nic;
        this.avail_bal = avail_bal;
        this.req_amount = req_amount;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAvail_bal() {
        return avail_bal;
    }

    public void setAvail_bal(String avail_bal) {
        this.avail_bal = avail_bal;
    }
    public String getReq_amount() {
        return req_amount;
    }

    public void setReq_amount(String req_amount) {
        this.req_amount = req_amount;
    }




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
