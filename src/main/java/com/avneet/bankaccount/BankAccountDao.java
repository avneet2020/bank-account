package com.avneet.bankaccount;

import java.sql.*;

public class BankAccountDao {
    public Connection connection = null;
    private PreparedStatement insertPreparedStatement;
    private PreparedStatement readByUserPassPreparedStatement;

    public BankAccountDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "toor");
            insertPreparedStatement = connection.prepareStatement(
                    "INSERT INTO `bank`.`customers`(username,password,name,balance,email)VALUES(?, ?, ?, 0, ? )");
            readByUserPassPreparedStatement = connection.prepareStatement("SELECT * from customers WHERE username = ? AND password = ?");
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
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            return new BankAccount(name, balance);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public BankAccount register(String user, String pass, String name, String email) {
        try {
            insertPreparedStatement.setString(1, user);
            insertPreparedStatement.setString(2, pass);
            insertPreparedStatement.setString(3, name);
            insertPreparedStatement.setString(4, email);
            insertPreparedStatement.execute();
            return new BankAccount(name, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public boolean changeBalance(String name, double newTotal) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bank.customers SET balance = ? WHERE name = ?");
            preparedStatement.setDouble(1, newTotal);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}