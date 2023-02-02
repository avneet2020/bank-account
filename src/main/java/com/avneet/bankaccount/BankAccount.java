package com.avneet.bankaccount;

class BankAccount {
    private final String username;
    private String fname;
    private String lname;
    private double balance;

    public BankAccount(String username, String fname, String lname, double balance) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.balance = balance;
    }

    public String getFname() {
        return this.fname;
    }
    public String getLname() {
        return this.lname;
    }
    public String getName(){
        return fname + " " + lname;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        String s = "Name: " + fname + " " + lname + "\nBalance: $" + balance;
        return s;

    }

}