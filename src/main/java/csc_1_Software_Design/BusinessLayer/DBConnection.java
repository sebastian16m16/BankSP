package csc_1_Software_Design.BusinessLayer;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Bank";

    static final String USER = "root";
    static final String PASS = "spune-miIshmael";

    public Connection connection;

    public static DBConnection db;

    private DBConnection(){
        try{
            System.out.println("Connecting to DATABASE...\n");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized DBConnection getConnection(){
        if(db == null){
            db = new DBConnection();
        }
        return db;
    }
}