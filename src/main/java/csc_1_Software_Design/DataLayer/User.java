package csc_1_Software_Design.DataLayer;

public class User {
    int user_id;
    String username;
    String password;
    Boolean administrator;
    
    public User(int user_id, String username, String password, Boolean admin) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.administrator = admin;
    }

    public User(int user_id){

    }
    
    public User(Boolean admin) {
    	this.administrator = admin;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
