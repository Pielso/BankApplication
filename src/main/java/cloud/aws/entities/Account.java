package cloud.aws.entities;

import cloud.aws.service.AccountService;

public abstract class Account implements AccountService {
    protected int accountNumber;
    protected float accountBalance;

    public Account(int accountNumber, float accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }


    // Getters & Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }





}
