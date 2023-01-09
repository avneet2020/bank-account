package com.avneet.bankaccount;

import java.text.NumberFormat;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        BankAccount bankAccount = null;
        BankAccountDao dao = new BankAccountDao();
        BankAccountHandler bankAccountHandler = new BankAccountHandler(dao);
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Bank");
        System.out.println("Select an Option");
        System.out.println("1: Log in");
        System.out.println("2: Register");
        System.out.print("Your Selection: ");
        boolean exit = false;

        while (!exit) {
            switch (scan.nextInt()) {
                case 1:
                    // 1 = Log In
                    System.out.print("Enter your username: ");
                    String user = scan.next().trim();
                    System.out.print("Enter your password: ");
                    String pass = scan.next().trim();
                    exit = true;
                    bankAccount = bankAccountHandler.login(user, pass);
                    if (bankAccount == null) {
                        System.out.println("Invalid Credentials.\nExiting...");
                        System.exit(1);
                    }
                    break;
                case 2:
                    // 2 = Register
                    System.out.println("Enter your name: ");
                    String name = scan.next().trim();
                    System.out.println("Enter your username: ");
                    String username = scan.next().trim();
                    System.out.println("Enter your password: ");
                    String password = scan.next().trim();
                    System.out.println("Enter your email: ");
                    String email = scan.next().trim();
                    bankAccount = bankAccountHandler.registerUser(username,password,name,email);
                    exit = true;
                    if (bankAccount == null) {
                        System.out.println("Error Registering.\nExiting...");
                        System.exit(1);
                    }
                    break;
                default:
                    // Wrong Input
                    System.out.println("Invalid input. Try Again");
                    break;
            }
        }
        welcome(bankAccount);
    }

    public void welcome(BankAccount bankAccount) {
        boolean exit = false;
        BankAccountDao dao = new BankAccountDao();
        BankAccountHandler bankAccountHandler = new BankAccountHandler(dao);
        System.out.println("Welcome, " + bankAccount.getName() + "!");
        System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(bankAccount.getBalance()));
        help();

        Scanner scan = new Scanner(System.in);
        while (!exit) {

            System.out.print("Enter your action: ");
            int input = scan.nextInt();
            switch (input) {
                case 1:
                    // 1 = View Balance
                    System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(bankAccount.getBalance()));
                    break;
                case 2:
                    // 2 = Deposit Money
                    System.out.print("How much would you like to deposit?\n$");
                    double moneyDeposited = scan.nextDouble();
                    if (moneyDeposited < 0) {
                        System.out.println("Cannot deposit negative money. Try Again.");
                        break;
                    }
                    bankAccountHandler.deposit(bankAccount,moneyDeposited);
                    break;
                case 3:
                    // 3 = Withdraw Money
                    System.out.print("How much would you like to withdraw?\n$");
                    double moneyWithdrawn = scan.nextDouble();
                    bankAccountHandler.withdraw(bankAccount,moneyWithdrawn);
                    break;
                case 4:
                    // 4 = Reveal Options
                    help();
                    break;
                case 5:
                    // 5 = Exit
                    System.out.println("Goodbye.");
                    exit = true;
                    break;
                default:
                    // Wrong Input. Add Another Input.
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    public void help() {
        System.out.println("Options");
        System.out.println("1: View Balance");
        System.out.println("2: Deposit Money");
        System.out.println("3: Withdraw Cash");
        System.out.println("4: Show Options");
        System.out.println("5: Exit");
    }

}
