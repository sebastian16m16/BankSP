package csc_1_Software_Design.DataLayer;

import java.sql.SQLException;

public class Login {
    private int login_id;
    private String cnp;
    private String username;
    private String password;
    private Boolean administrator;


    //user-Client

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getCnp() {
        return cnp;
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
