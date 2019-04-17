package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.*;


import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLException;


public class  MainOp
{
    public static void main(String[] args) throws SQLException {

        DBConnection connection = DBConnection.getConnection();
        Date date = new Date(2019, 5, 17);
        Client c1 = new Client("1940129125999", "1234ff4", "Sebastian", "Muresan", "Str.Vagabonzilor 25, Cluj-Napoca");

        Login l1 = new Login(c1.getClient_id(), "Ishtvan", "1234");
        Account a1 = new Account();

        AccountOP accountOP = new AccountOP();
        LoginOP loginOP = new LoginOP();
        ClientOP clientOP = new ClientOP();

        try {
           // clientOP.insertClient(connection.connection, c1);
            Client cExist = new Client();
            clientOP.getClientByID(connection.connection,1);
            accountOP.createAccount(connection.connection, cExist,"Saving", date);
            loginOP.createUserLogin(connection.connection, cExist, "user", "user");


        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
