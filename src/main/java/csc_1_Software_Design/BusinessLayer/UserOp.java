package csc_1_Software_Design.BusinessLayer;


import csc_1_Software_Design.DataLayer.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserOp {

    DBConnection connection = DBConnection.getConnection();
    AccountOP accountOp = new AccountOP();
    ClientOP clientOP = new ClientOP();
    LoginOP loginOP = new LoginOP();


    public String getFullClientName(String cnp) throws SQLException{
        return clientOP.getFullClientName(connection.connection, cnp);
    }

    public String getAccountCNPFromAccountID(int id) throws SQLException{
        return accountOp.getAccountCNPfromAccountID(connection.connection, id);
    }

    public int numberOfMoneyAccountsOnCLIENT(String cnp)throws SQLException{
        return accountOp.numberOfAccountsOnClient(connection.connection, cnp);
    }

    public String[] getTypesOfAccountsFromClient(String cnp, String noShowType) throws SQLException{
        return accountOp.getTypesOfAccountsFromClient(connection.connection, cnp, noShowType);
    }

    public int getAccountIDbyType(String type, String cnp) throws SQLException{
        return accountOp.getAccountIDbyType(connection.connection, type, cnp);
    }

    public void createAccount(String cnp, String type, double initialBalance) throws SQLException{
        accountOp.createAccount(connection.connection, cnp, type, initialBalance);
    }

    public ArrayList<Account> getClientAccount(String cnp) throws SQLException{
        return accountOp.getAccountByCNP(connection.connection, cnp);
    }

    public Account getAccountByClientID(int id) throws SQLException {
       return accountOp.getAccountByID(connection.connection, id);
    }

    public void topUp(int accountID, double amount) throws SQLException{
        accountOp.topUp(connection.connection, accountID, amount);
    }

    public void updateAccountType(String newType, int accountID) throws SQLException{
        accountOp.updateAccountType(connection.connection, accountID, newType);
    }

    public void transferMoney(int sender_id, int receiver_id, double amount) throws SQLException{
        accountOp.transferMoney(connection.connection, sender_id, receiver_id, amount);
    }

    public boolean existsAccountByID(int id) throws SQLException{
        return accountOp.existsAccountByID(connection.connection, id);
    }

    public void deleteAccount(int accountID)throws SQLException{
        accountOp.deleteAccountById(connection.connection, accountID);
    }

    public void createUserLogin(String cnp, String username, String password)throws SQLException{
        loginOP.createUserLogin(connection.connection, cnp, username, password);
    }

    public Client getClientByCNP(String cnp) throws SQLException{
        Client client = clientOP.getClientByCNP(connection.connection, cnp);
        return client;
    }

    public double getBalanceOfAccountByTypeAndCNP(String type, String cnp) throws SQLException{
        return accountOp.getBalanceOfAccountByTypeAndCNP(connection.connection, type, cnp);
    }

    public void transferAllFromOneTypeToAnother(String main, String receiver) throws SQLException{
        accountOp.transferAllFromOneTypeToAnother(connection.connection, main, receiver);
    }

    public void updateUsernameByCNP(String newUsername, String cnp) throws SQLException{
        loginOP.updateUsernameByCNP(connection.connection, newUsername, cnp);
    }

    public void updatePasswordByCNP(String newPassword, String cnp) throws SQLException{
        loginOP.updatePasswordByCNP(connection.connection, newPassword, cnp);
    }

    public void updateClient(String cnp, String first_name, String last_name, String cni, String address) throws SQLException{
        clientOP.updateClient(connection.connection, cnp, first_name, last_name, address, cni);
    }
}
