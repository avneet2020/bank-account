package com.avneet.bankaccount;

import java.sql.*;

public class BankAccountDao {
    public boolean connected = false;
    public Connection connection = null;

    public BankAccountDao() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "toor");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BankAccount logIn(String inputtedUsername, String inputtedPassword) {
        try {

            Statement statement = connection.createStatement();
            String sql = "SELECT * from customers WHERE username = '" + inputtedUsername + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            String password = resultSet.getString("password");
            if (!inputtedPassword.equals(password)) {
                return null;
            }
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO `bank`.`customers`(username,password,name,balance,email)VALUES(?, ?, ?, 0, ? )");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, email);
            // preparedStatement.executeUpdate();
            preparedStatement.execute();
            // String sql = "INSERT INTO
            // `bank`.`customers`(username,password,first,last,balance,email)VALUES(" + user
            // + "," + pass + "," + first + "," + last + "," + balance + "," + email + ");";
            // statement.execute(sql);
            // logIn(user, pass);
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}