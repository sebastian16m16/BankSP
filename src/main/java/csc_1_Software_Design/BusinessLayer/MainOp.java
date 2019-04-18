package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.*;


import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Date;


public class  MainOp
{
    public static void main(String[] args) throws SQLException {

        DBConnection connection = DBConnection.getConnection();
        long millis=System.currentTimeMillis();
        Date date = new Date(millis);

        Client c1 = new Client("1940129125999", "1234ff4", "Sebastian", "Muresan", "Str.Vagabonzilor 25, Cluj-Napoca");

        Login l1 = new Login(c1.getClient_id(), "Ishtvan", "1234");
        Account a1 = new Account();

        AccountOP accountOP = new AccountOP();
        LoginOP loginOP = new LoginOP();
        ClientOP clientOP = new ClientOP();

        try {
//            clientOP.insertClient(connection.connection, c1);
//            loginOP.createUserLogin(connection.connection, c1, "user", "user");
            Client cExist = clientOP.getClientByID(connection.connection, 7);
            clientOP.deleteClient(connection.connection, cExist);





        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
