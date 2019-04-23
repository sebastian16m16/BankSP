package csc_1_Software_Design.DataLayer;

import java.sql.*;

public class LoginOP{

    public String getCNPfromLogin(Connection connection, String username) throws SQLException{
        String stmt = "Select cnp from login where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        String cnp = "";

        while (resultSet.next()){
            cnp = resultSet.getString("cnp");
        }
        return cnp;
    }

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

        while(resultSet.next()) {
            int login_id = resultSet.getInt("login_id");
            String cnp = resultSet.getString("cnp");
            Boolean administrator = resultSet.getBoolean("Administrator");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            login.setLogin_id(login_id);
            login.setUsername(username);
            login.setPassword(password);
            login.setCnp(cnp);
            login.setAdministrator(administrator);
        }


        return login;
    }

    public Login getLoginByCNP(Connection connection, String cnp) throws SQLException{

        Login foundLogin = new Login();

        String stmt = "Select * from login where cnp = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            foundLogin.setLogin_id(resultSet.getInt("login_id"));
            foundLogin.setUsername(resultSet.getString("username"));
            foundLogin.setPassword(resultSet.getString("password"));
            foundLogin.setAdministrator(resultSet.getBoolean("administrator"));
            foundLogin.setCnp(resultSet.getString("cnp"));

        }

        return foundLogin;
    }

    public void updateUsernameByCNP(Connection connection, String new_username, String cnp) throws SQLException{
        String stmt = "UPDATE Login SET username = ? where cnp =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_username);
        preparedStatement.setString(2, cnp);

        preparedStatement.executeUpdate();
    }

    public void updatePasswordByCNP(Connection connection, String new_password, String cnp) throws SQLException{

        String stmt = "UPDATE Login SET password = ? where cnp =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_password);
        preparedStatement.setString(2, cnp);

        preparedStatement.executeUpdate();
    }

    public void updateUsernameByID(Connection connection, String new_username, int id) throws SQLException{
        String stmt = "UPDATE Login SET username = ? where login_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_username);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
    }

    public void updatePasswordByID(Connection connection, String new_password, int id) throws SQLException{
        String stmt = "UPDATE Login SET password = ? where login_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, new_password);
        preparedStatement.setInt(2, id);

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

    public boolean existsLoginByCNP(Connection connection, String cnp) throws SQLException{
        String stmt = "Select * from login where cnp = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1,cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

    public boolean existsLoginByID(Connection connection, int id) throws SQLException{
        String stmt = "Select * from login where login_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
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
