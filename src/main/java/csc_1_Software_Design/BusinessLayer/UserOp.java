package csc_1_Software_Design.BusinessLayer;


import csc_1_Software_Design.DataLayer.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UserOp {

    DBConnection connection = DBConnection.getConnection();
    AccountOP accountOp = new AccountOP();
    ClientOP clientOP = new ClientOP();
    LoginOP loginOP = new LoginOP();
    String cnp;
    String first_name;
    String last_name;
    int id;

    public UserOp(String cnp){
        this.cnp = cnp;
    }

    public String getFullClientName(String cnp) throws SQLException{
        return clientOP.getFullClientName(connection.connection, cnp);
    }

    public Account getAccountByClientID(int id) throws SQLException {
       return accountOp.getAccountByID(connection.connection, id);
    }

    public void topUp(double amount, int accountID) throws SQLException{
        accountOp.topUp(connection.connection, accountID, amount);
    }

    public void updateAccountType(String newType, int accountID) throws SQLException{
        accountOp.updateAccountType(connection.connection, accountID, newType);
    }

    public void transferMoney(double amount, Account sender, Account receiver) throws SQLException{
        accountOp.transfer(connection.connection, sender, receiver, amount);
    }

    public void deleteAccount(int accountID)throws SQLException{
        accountOp.deleteAccountById(connection.connection, accountID);
    }

    public void createUserLogin(String cnp, String username, String password)throws SQLException{
        loginOP.createUserLogin(connection.connection, cnp, username, password);
    }

    public Client getClientByCNP() throws SQLException{
        Client client = clientOP.getClientByCNP(connection.connection, cnp);
        this.id = client.getClient_id();
        return client;
    }
}
