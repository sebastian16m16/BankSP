package csc_1_Software_Design.DataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Login {
    private static int login_id = 0;
    private int client_id;
    private String username;
    private String password;
    private Boolean administrator;

    public Login(Connection connection, int client_id, String username, String password) throws SQLException {
        login_id++;
        this.username = username;
        this.password = password;
        this.client_id = client_id;

        String stmt = "INSERT INTO Login('login_id', 'username', 'password', 'administrator', 'client_id') " +
                "values( ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setInt(1, login_id);
        preparedStatement.setString(2,username);
        preparedStatement.setString(3, password);
        preparedStatement.setBoolean(4,false);
        preparedStatement.setInt(5,client_id);
        preparedStatement.executeUpdate();
    }

    public Login(Connection connection, int client_id, String username, String password, Boolean administrator) throws SQLException {
        login_id++;
        this.username = username;
        this.password = password;
        this.administrator = administrator;
        this.client_id = client_id;

        String stmt = "INSERT INTO Login('login_id', 'username', 'password', 'administrator', 'client_id') " +
                "values( ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setInt(1, login_id);
        preparedStatement.setString(2,username);
        preparedStatement.setString(3, password);
        preparedStatement.setBoolean(4,administrator);
        preparedStatement.setInt(5,client_id);
        preparedStatement.executeUpdate();
    }

    public Login(Connection connection, String username, String password, Boolean administrator) throws SQLException {
        login_id++;
        this.username = username;
        this.password = password;
        this.client_id = client_id;

        String stmt = "INSERT INTO Login('login_id', 'username', 'password', 'administrator') " +
                "values( ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setInt(1, login_id);
        preparedStatement.setString(2,username);
        preparedStatement.setString(3, password);
        preparedStatement.setBoolean(4,administrator);
        preparedStatement.executeUpdate();
    }

    public int getClient_id() {
        return client_id;
    }

    /**
     * @return the login_id
     */
    public int getLogin_id() {
        return login_id;
    }

    /**
     * @param login_id the login_id to set
     */
    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the administrator
     */
    public Boolean getAdministrator() {
        return administrator;
    }

    /**
     * @param administrator the administrator to set
     */
    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }
    

   
}
