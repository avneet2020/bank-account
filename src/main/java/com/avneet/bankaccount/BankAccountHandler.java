package com.avneet.bankaccount;

import java.text.NumberFormat;

public class BankAccountHandler {
    BankAccountDao dao;

    public BankAccountHandler(BankAccountDao dao) {
        this.dao = dao;
    }

    public BankAccount login(String username, String password) {
        return dao.logIn(username, password);
    }

    public BankAccount registerUser(String username, String password, String name, String email) {
        return dao.register(username, password, name, email);
    }

    public void deposit(BankAccount bank, double amount) {
        double newTotal = bank.getBalance() + amount;
        boolean result = dao.changeBalance(bank.getName(), newTotal);
        if(result) {
            bank.setBalance(newTotal);
            System.out.println("Deposit Success!");
        }
    }

    public void withdraw(BankAccount bank, double amount) {
        if (amount == 0){
            System.out.println("Cannot Withdraw no Money. Try Again");
        }
        else if (amount < 0) {
            System.out.println("Cannot Withdraw Negative Money. Try Again.");
        }
        else if (amount > bank.getBalance()) {
            System.out.println("Insufficient funds.");

        } else {
            double newTotal = (bank.getBalance() - amount);
            boolean result = dao.changeBalance(bank.getName(),newTotal);
            if(result) {
                bank.setBalance(newTotal);
                System.out.println("Transaction Success!");
                System.out.println("You have withdrawn: " + NumberFormat.getCurrencyInstance().format(amount));
            }

        }

    }

}
