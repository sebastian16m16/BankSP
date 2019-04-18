package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.DataLayer.Login;

import java.sql.Date;
import java.sql.SQLException;

public class AdminOp extends UserOp {

	public void createAccount(Client client, String type, Date created_date) throws SQLException{
	    accountOp.createAccount(connection.connection, client, type, created_date);
    }

    public void insertClient(Client client) throws SQLException{
	    clientOP.insertClient(connection.connection, client);
    }

    public Client getClientByID(int id) throws SQLException{
	    return clientOP.getClientByID(connection.connection, id);
    }

    public void deleteClient(Client client) throws SQLException{
	    clientOP.deleteClient(connection.connection, client);
    }

    public void updateClient(Client client, String first_name, String last_name, String cnp, String cni, String address) throws SQLException{
	    clientOP.updateClient(connection.connection, client, first_name, last_name, address, cnp, cni);
    }

    public void createAdminLogin(String username, String password) throws SQLException{
	    loginOP.createAdminLogin(connection.connection, username, password);
    }

    public Login getLoginByID(int id) throws SQLException{
	    return loginOP.getLoginByID(connection.connection, id);
    }
}
