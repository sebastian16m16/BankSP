package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.Client;
import csc_1_Software_Design.DataLayer.Login;


import java.sql.SQLException;


public class  MainOp
{
    public static void main(String[] args) throws SQLException {

        UserOp up = new UserOp();
        DBConnection connection = DBConnection.getConnection();

        Client c1 = new Client(connection.connection, "1940129125819", "123ff4", "Sebastian Marius",
                "Muresan", "Str.Vagabonzilor 46, Cluj-Napoca");

        Login l1 = new Login(connection.connection, c1.getClient_id(), "sebastian16", "blabla");

        System.out.println( up.getInformation(l1, connection.connection));



    }
}
