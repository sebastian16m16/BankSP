package csc_1_Software_Design.BusinessLayer;

import csc_1_Software_Design.DataLayer.*;

import javax.crypto.AEADBadTagException;
import java.sql.*;

public class UserOp {

    double currency =  4.712;

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

    public static String transferRO(Login login, Connection connection, AccountRO accountRO, Client receiver, AccountRO recieverRO, double amount) throws  SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1,login.getClient_id());
        check.setInt(2, accountRO.getAccount_id());
        ResultSet qResult = check.executeQuery();

        int balance = qResult.getInt("Balance");

        //Check to see if there are enough money on the sender account
        if(balance > amount){

            // Send money
            String stmt = "UPDATE Account SET Balance = Balance + ? WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";
            String stmt2 = "UPDATE Account SET Balance = Balance - ? WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";

            PreparedStatement prepST1 = connection.prepareStatement(stmt);
            PreparedStatement prepSt2 = connection.prepareStatement(stmt2);


            prepST1.setDouble(1,  amount);
            prepST1.setInt(2, receiver.getClient_id());
            prepST1.setInt(3,recieverRO.getAccount_id());

            prepSt2.setDouble(1, amount);
            prepSt2.setInt(2, login.getClient_id());
            prepSt2.setInt(3,accountRO.getAccount_id());

            return "The transfer was successful!";
        }else
            return "Insufficient funds!";

    }

    public static String transferEUR(Login login, Connection connection, AccountEUR accountEUR, Client receiver, AccountEUR receiverEUR, double amount) throws  SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Client_id = ? AND type = 'EUR'";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1,login.getClient_id());
        ResultSet qResult = check.executeQuery();

        int balance = qResult.getInt("Balance");

        //Check to see if there are enough money on the sender account
        if(balance > amount){

            // Send money
            String stmt = "UPDATE Account SET Balance = Balance + ? WHERE Client_id = ? AND Account_id = ? AND type = 'EUR'";
            String stmt2 = "UPDATE Account SET Balance = Balance - ? WHERE Client_id = ? AND Account_id = ? AND type = 'EUR'";

            PreparedStatement prepST1 = connection.prepareStatement(stmt);
            PreparedStatement prepSt2 = connection.prepareStatement(stmt2);


            prepST1.setDouble(1,  amount);
            prepST1.setInt(2, receiver.getClient_id());
            prepST1.setInt(3,receiverEUR.getAccount_id());

            prepSt2.setDouble(1, amount);
            prepSt2.setInt(2, login.getClient_id());
            prepSt2.setInt(3,accountEUR.getAccount_id());

            return "The transfer was successful!";
        }else
            return "Insufficient funds!";

    }

    public String currencyExchangeROtoEUR(Login login, Connection connection, AccountRO accountRO, AccountEUR accountEUR, double amountInEur) throws SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Client_id = ? AND Account_id = ? AND type = 'RO'";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1,login.getClient_id());
        check.setInt(2,accountRO.getAccount_id());
        ResultSet qResult = check.executeQuery();

        int balance = qResult.getInt("Balance");

        //Check to see if there are enough money on the sender account
        if(balance > amountInEur*currency) {

            String stmt1 = "UPDATE Account SET Balance = Balance - ? WHERE Client_id = ? AND type = 'RO' ";
            String stmt2 = "UPDATE Account SET Balance = Balance + ? WHERE Client_id = ? AND type = 'EUR'";

            PreparedStatement preparedStatement1 = connection.prepareStatement(stmt1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(stmt2);

            preparedStatement1.setDouble(1, amountInEur * currency);
            preparedStatement1.setInt(2, accountRO.getClient_id());

            preparedStatement2.setDouble(1, amountInEur);
            preparedStatement2.setInt(2, accountEUR.getClient_id());

            return "The exchange was successful!";
        }else
            return "Insufficient funds!";
    }

    public String currencyExchangeEURtoRO(Login login, Connection connection, AccountEUR accountEUR, AccountRO accountRO, double amountInRO) throws  SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Client_id = ?, type = 'EUR'";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1,login.getClient_id());
        ResultSet qResult = check.executeQuery();

        int balance = qResult.getInt("Balance");

        //Check to see if there are enough money on the sender account
        if(balance > amountInRO/currency) {

            String stmt1 = "UPDATE Account SET Balance = Balance - ? WHERE Client_id = ?, type = 'EUR' "; // take euros from account
            String stmt2 = "UPDATE Account SET Balance = Balance + ? WHERE Client_id = ?, type = 'RO'"; // add money to receiver account

            PreparedStatement preparedStatement1 = connection.prepareStatement(stmt1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(stmt2);

            preparedStatement1.setDouble(1, amountInRO / currency);
            preparedStatement1.setInt(2, accountRO.getClient_id());

            preparedStatement2.setDouble(1, amountInRO);
            preparedStatement2.setInt(2, accountEUR.getClient_id());

            return "The exchange was successful!";
        }else
            return "Insufficient funds!";

    }


}
