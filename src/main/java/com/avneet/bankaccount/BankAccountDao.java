package com.avneet.bankaccount;

import java.sql.*;

public class BankAccountDao {
    public Connection connection = null;
    private PreparedStatement insertPreparedStatement;
    private PreparedStatement readByUserPassPreparedStatement;
    private PreparedStatement changeBalancePreparedStatement;

    public BankAccountDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "toor");
            insertPreparedStatement = connection.prepareStatement(
                    "INSERT INTO `bank`.`customers`(username,password,fname,lname,balance,email)VALUES(?, ?, ?, ?, 0, ? )");
            readByUserPassPreparedStatement = connection.prepareStatement("SELECT * from customers WHERE username = ? AND password = ?");
            changeBalancePreparedStatement = connection.prepareStatement("UPDATE bank.customers SET balance = ? WHERE username = ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BankAccount logIn(String inputtedUsername, String inputtedPassword) {
        try {

            readByUserPassPreparedStatement.setString(1, inputtedUsername);
            readByUserPassPreparedStatement.setString(2, inputtedPassword);
            ResultSet resultSet = readByUserPassPreparedStatement.executeQuery();
            if (!resultSet.next()){
                return null;
            }
//          TODO: what happens if user does not exist
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            double balance = resultSet.getDouble("balance");
            return new BankAccount(inputtedUsername, fname, lname, balance);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public BankAccount register(String user, String pass, String fname, String lname, String email) {
        try {
            insertPreparedStatement.setString(1, user);
            insertPreparedStatement.setString(2, pass);
            insertPreparedStatement.setString(3, fname);
            insertPreparedStatement.setString(4, lname);
            insertPreparedStatement.setString(5, email);
            insertPreparedStatement.execute();
            return new BankAccount(user, fname,lname, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean changeBalance(String username, double newTotal) {
        try {
            changeBalancePreparedStatement.setDouble(1, newTotal);
            changeBalancePreparedStatement.setString(2, username);
            changeBalancePreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}