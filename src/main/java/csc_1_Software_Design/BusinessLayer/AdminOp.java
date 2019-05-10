package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminOp {

    DBConnection connection = DBConnection.getConnection();
    AccountOP accountOp = new AccountOP();
    ClientOP clientOP = new ClientOP();
    LoginOP loginOP = new LoginOP();


	public void createAccount(String cnp, String type, double initialBalance) throws SQLException{
	    accountOp.createAccount(connection.connection, cnp, type, initialBalance);
    }

    public void insertClient(Client client) throws SQLException{
	    clientOP.insertClient(connection.connection, client);
    }

    public Client getClientByID(int id) throws SQLException{
	    return clientOP.getClientByID(connection.connection, id);
    }

    public Client getClientByCNP(String cnp) throws SQLException{
        return clientOP.getClientByCNP(connection.connection, cnp);
    }

    public void deleteClient(String cnp) throws SQLException{
	    clientOP.deleteClient(connection.connection, cnp);
    }

    public void updateClient(String cnp, String first_name, String last_name, String cni, String address) throws SQLException{
	    clientOP.updateClient(connection.connection, cnp, first_name, last_name, address, cni);
    }

    public void createAdminLogin(String username, String password) throws SQLException{
	    loginOP.createAdminLogin(connection.connection, username, password);
    }

    public Login getLoginByID(int id) throws SQLException{
	    return loginOP.getLoginByID(connection.connection, id);
    }

    public boolean existsClientByCNP(String cnp)throws SQLException{
        return clientOP.existsClientByCNP(connection.connection, cnp);
    }

    public boolean existsClientByID(int id) throws SQLException{
	    return clientOP.existsClientByID(connection.connection, id);
    }

    public int numberOfMoneyAccountsOnCLIENT(String cnp)throws SQLException{
	    return accountOp.numberOfAccountsOnClient(connection.connection, cnp);
    }

    public boolean existsAccountByID(int id) throws SQLException{
	    return accountOp.existsAccountByID(connection.connection, id);
    }

    public void deleteAccountByID(int id) throws SQLException{
	    accountOp.deleteAccountById(connection.connection, id);
    }

    public String getAccountTypeByID(int id) throws SQLException{
	    return accountOp.getAccountTypeByID(connection.connection, id);
    }

    public String getAccountCNPbyID(int id) throws SQLException{
	    return accountOp.getAccountCNPfromAccountID(connection.connection, id);
    }

    public String[] getTypesOfAccountsFromClient(String cnp, String noShowType) throws SQLException{
	    return accountOp.getTypesOfAccountsFromClient(connection.connection, cnp, noShowType);
    }

    public void transferAllFromOneTypeToAnother(String main, String receiver) throws SQLException{
	    accountOp.transferAllFromOneTypeToAnother(connection.connection, main, receiver);
    }

    public Account getAccountByID(int id) throws SQLException{
	    return accountOp.getAccountByID(connection.connection, id);
    }

    public ArrayList<Account> getAccountByCNP(String cnp) throws SQLException{
	    return accountOp.getAccountByCNP(connection.connection, cnp);
    }

    public boolean existsAccountByTypeFromCNP(String type, String cnp) throws SQLException{
	    return accountOp.existsAccountByTypeFromCNP(connection.connection, type, cnp);
    }

    public boolean existsAccountByCNP(String cnp) throws SQLException{
	    return accountOp.existsAccountByCNP(connection.connection, cnp);
    }

    public double getBalanceFromAccountByID(int id) throws SQLException{
	    return accountOp.getBalanceOfAccountByID(connection.connection, id);
    }

    public String getClientCNPByID(int id) throws SQLException{
	    return clientOP.getClientCNPfromID(connection.connection, id);
    }

    public boolean existsLoginByCNP(String cnp) throws SQLException{
	    return loginOP.existsLoginByCNP(connection.connection, cnp);
    }

    public boolean existsLoginByID(int id) throws SQLException{
	    return loginOP.existsLoginByID(connection.connection, id);
    }

    public Login getLoginByCNP(String cnp) throws SQLException{
	    return loginOP.getLoginByCNP(connection.connection, cnp);
    }

    public void updateUsernameByCNP(String newUsername, String cnp) throws SQLException{
	    loginOP.updateUsernameByCNP(connection.connection, newUsername, cnp);
    }

    public void updatePasswordByCNP(String newPassword, String cnp) throws SQLException{
	    loginOP.updatePasswordByCNP(connection.connection, newPassword, cnp);
    }

    public void updateUsernameByID(String newUsername, int id) throws SQLException{
	    loginOP.updateUsernameByID(connection.connection, newUsername, id);
    }

    public void updatePasswordByID(String newPassword, int id) throws SQLException{
	    loginOP.updatePasswordByID(connection.connection, newPassword, id);
    }

    public void updateAccountType(int id, String newType) throws SQLException{
	    accountOp.updateAccountType(connection.connection, id, newType);
    }
}
