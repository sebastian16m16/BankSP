package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.Account;
import csc_1_Software_Design.DataLayer.Login;
import java.sql.*;

public class UserOp {

    public static String getInformation(Login login, Connection connection) throws SQLException {
        String stmt = "SELECT * FROM Client where login_id == ?";
        PreparedStatement prepSt = connection.prepareStatement(stmt);
        prepSt.setInt(1, login.getLogin_id());
        ResultSet qResult = prepSt.executeQuery();

            int id = qResult.getInt("client_id");
            String cnp = qResult.getString("cnp");
            String cni = qResult.getString("cni");
            String first_name = qResult.getString("first_name");
            String last_name = qResult.getString("last_name");
            String address = qResult.getString("address");
            int login_id = qResult.getInt("login_id");

            return "ID: " + id + " CNP: " + cnp + " CNI: " + cni + " First Name: " + first_name +
                    "Last Name: " + last_name + " Address: " + address + " Login_id: " + login_id;

    }

    public static void updateName(Login login, Connection connection, String newFirstName, String newLastName) throws  SQLException{

        String stmt = "UPDATE Client SET first_name = ?, last_name = ? WHERE login_id == ?";
        PreparedStatement prepSt = connection.prepareStatement(stmt);
        prepSt.setString(1, newFirstName);
        prepSt.setString(2, newLastName);
        prepSt.setInt(3, login.getLogin_id());

        prepSt.executeUpdate();

    }

    public static void updateCNP(Login login, Connection connection, String newCNP) throws SQLException{

        String stmt = "UPDATE Client SET cnp = ? where login_id == ?";
        PreparedStatement prepSt = connection.prepareStatement(stmt);
        prepSt.setString(1, newCNP);
        prepSt.setInt(2, login.getLogin_id());

        prepSt.executeUpdate();
    }

    public static void updateAddress(Login login, Connection connection, String newAddress) throws SQLException{
        String stmt = "UPDATE Client SET address = ? where login_id == ?";
        PreparedStatement prepSt = connection.prepareStatement(stmt);
        prepSt.setString(1, newAddress);
        prepSt.setInt(2, login.getLogin_id());

        prepSt.executeUpdate();
    }

    public static void transferRO(Login login, Connection, Account receiver, float amount){
        String stmt = "UPDATE A";
    }


}
