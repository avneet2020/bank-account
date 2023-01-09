package com.avneet.bankaccount;

class BankAccount {

    private String name;
    private double balance;

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        String s = "Name: " + name + "\nBalance: $" + balance;
        return s;

    }

}