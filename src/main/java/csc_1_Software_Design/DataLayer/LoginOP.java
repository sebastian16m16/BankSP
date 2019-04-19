package csc_1_Software_Design.DataLayer;

import csc_1_Software_Design.BusinessLayer.UserOp;

import java.sql.*;

public class LoginOP{

    public void createUserLogin(Connection connection, String cnp, String username, String password) throws SQLException {

        String stmt = "INSERT INTO Login (username, password, administrator, CNP) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2, password);
        preparedStatement.setBoolean(3, false);
        preparedStatement.setString(4, cnp);
        preparedStatement.executeUpdate();

        System.out.println("Login created!");

    }

    public  void addLoginIdToClient(Connection connection, Client client, Login login) throws SQLException{
        String stmt = "UPDATE Client SET login_id = ? where client_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1,login.getLogin_id());
        preparedStatement.setInt(2,client.getClient_id());
        preparedStatement.executeUpdate();
    }

    public void createAdminLogin(Connection connection, String username, String password) throws SQLException{
        String stmt = "INSERT INTO Login (username, password, administrator) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setBoolean(3, true);
        preparedStatement.executeUpdate();

        System.out.println("Admin login created! \n  UserName: " + username + " Password: " + password);
    }

    public Login getLoginByID(Connection connection, int id) throws SQLException{
        Login login = new Login();

        String stmt = "Select * from login where login_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        int login_id = resultSet.getInt("login_id");
        int client_id = resultSet.getInt("client_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");


        login.setLogin_id(login_id);
        login.setUsername(username);
        login.setPassword(password);
        login.setClient_id(client_id);

        return login;
    }

    public void updateUsername(Connection connection, Login login, String new_username) throws SQLException{
        String stmt = "UPDATE Login SET username = ? where login_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_username);
        preparedStatement.setInt(2, login.getLogin_id());

        preparedStatement.executeUpdate();
    }

    public void updatePassword(Connection connection, Login login, String new_password) throws SQLException{

        String stmt = "UPDATE Login SET password = ? where login_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_password);
        preparedStatement.setInt(2, login.getLogin_id());

        preparedStatement.executeUpdate();
    }

    public void deleteLogin(Connection connection, String username) throws SQLException{
        String stmt = "DELETE FROM login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
        System.out.println("Login with username: " + username + " was removed!");
    }

    public boolean usernameExists(Connection connection, String username) throws SQLException{
        String stmt = "Select * from Login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return true;
        else
            return false;
    }

    public boolean cnpExists(Connection connection, String cnp) throws SQLException{
        String stmt = "Select * from login where cnp = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1,cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

    public boolean isAdmin(Connection connection, String username) throws SQLException{
        String stmt = "Select * FROM Login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean admin = false;

        while(resultSet.next()){
             admin = resultSet.getBoolean("administrator");
        }

        return admin;
    }

    public boolean loginOK(Connection connection, String username, String password) throws SQLException{
        String stmt = "Select * from login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            if(resultSet.getString("password").equals(password))
                return true;
            else
                return false;
        }
        return false;
    }
}
