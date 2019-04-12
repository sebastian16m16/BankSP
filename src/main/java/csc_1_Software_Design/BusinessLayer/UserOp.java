package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.User;

import java.sql.*;

public class UserOp {

    public static void getInformation(User user, Connection connection) throws SQLException {
        String stmt = "SELECT * from USER where username == ?";

        PreparedStatement prepSt = connection.prepareStatement(stmt);
        prepSt.setString(1,user.getUsername());
        prepSt.executeQuery();

    }
}
