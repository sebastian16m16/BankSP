package csc_1_Software_Design.DataLayer;

import java.sql.*;

public class AccountOP {

    public Account getAccountByID(Connection connection, int account_id) throws SQLException {

        Account foundAccount = new Account();

        String stmt = "Select * from account where account_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1,account_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        int id = resultSet.getInt("account_id");
        int client_id = resultSet.getInt("client_id");
        double balance = resultSet.getDouble("balance");
        String type = resultSet.getString("type");
        Date created_date = resultSet.getDate("created_date");

        foundAccount.setAccount_id(id);
        foundAccount.setClient_id(client_id);
        foundAccount.setBalance(balance);
        foundAccount.setType(type);
        foundAccount.setCreatedDate(created_date);

        return foundAccount;
    }

    public Account[] getAccountsByClient_id(Connection connection, int id) throws SQLException{

        Account[] foundAccounts = new Account[5];
        int i= 0;

        String stmt = "Select * from account where client_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {

            int account_id = resultSet.getInt("account_id");
            int client_id = resultSet.getInt("client_id");
            double balance = resultSet.getDouble("balance");
            String type = resultSet.getString("type");
            Date created_date = resultSet.getDate("created_date");

            foundAccounts[i].setClient_id(client_id);
            foundAccounts[i].setAccount_id(account_id);
            foundAccounts[i].setBalance(balance);
            foundAccounts[i].setType(type);
            foundAccounts[i].setCreatedDate(created_date);

            i++;
        }

        return foundAccounts;
    }

    public void createAccount(Connection connection, Client client, String type, Date created_date) throws SQLException{
        String stmt = "INSERT INTO Account(client_id,type,created_date, balance) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, client.getClient_id());
        preparedStatement.setString(2,type);
        preparedStatement.setDate(3, created_date);
        preparedStatement.setDouble(4, 0);
        preparedStatement.executeUpdate();
    }

    public void topUp(Connection connection, int account_id, double insertMoney) throws SQLException{
        String stmt = "UPDATE account set balance = balance + ? where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setDouble(1, insertMoney);
        preparedStatement.setInt(2, account_id);
        preparedStatement.executeUpdate();
    }

    public void updateAccountType(Connection connection, int account_id, String new_type) throws SQLException{
        String stmt = "UPDATE account SET type = ? where account_id =? ";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, new_type);
        preparedStatement.setInt(2, account_id);
        preparedStatement.executeUpdate();
    }

    public String transfer(Connection connection, Account sender, Account receiver, double amount) throws SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1, sender.getClient_id());
        check.setInt(2, sender.getAccount_id());
        ResultSet qResult = check.executeQuery();

        int balance = qResult.getInt("Balance");

        //Check to see if there are enough money on the sender account
        if(balance > amount){

            // Send money
            String stmt = "UPDATE Account SET Balance = Balance + ? WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";
            String stmt2 = "UPDATE Account SET Balance = Balance - ? WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";

            PreparedStatement prepST1 = connection.prepareStatement(stmt);
            PreparedStatement prepSt2 = connection.prepareStatement(stmt2);


            prepST1.setDouble(1,  amount);
            prepST1.setInt(2, receiver.getClient_id());
            prepST1.setInt(3, receiver.getAccount_id());

            prepSt2.setDouble(1, amount);
            prepSt2.setInt(2, sender.getClient_id());
            prepSt2.setInt(3, sender.getAccount_id());

            return "The transfer was successful!";
        }else
            return "Insufficient funds!";


    }

}
