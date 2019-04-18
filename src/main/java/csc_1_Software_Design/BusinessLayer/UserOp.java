package csc_1_Software_Design.BusinessLayer;


import csc_1_Software_Design.DataLayer.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UserOp {

    DBConnection connection = DBConnection.getConnection();
    AccountOP accountOp = new AccountOP();
    ClientOP clientOP = new ClientOP();
    LoginOP loginOP = new LoginOP();

    public Account getAccountByClientID(int id) throws SQLException {
       return accountOp.getAccountByID(connection.connection, id);
    }

    public Account[] getAccountsByClientID(int id) throws SQLException{
        return accountOp.getAccountsByClient_id(connection.connection, id);
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

    public void createUserLogin(Client client, String username, String password)throws SQLException{
        loginOP.createUserLogin(connection.connection, client, username, password);
    }

    public void updateUsername(Login login, String new_username) throws SQLException{
        loginOP.updateUsername(connection.connection, login,new_username);
    }

    public void updatePassword(Login login, String new_password) throws SQLException{
        loginOP.updatePassword(connection.connection, login, new_password);
    }

    public Client getClientByCNP(String cnp) throws SQLException{
        return clientOP.getClientByCNP(connection.connection, cnp);
    }
}
