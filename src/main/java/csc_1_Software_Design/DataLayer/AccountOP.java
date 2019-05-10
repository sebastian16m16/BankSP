package csc_1_Software_Design.DataLayer;


import java.sql.Date;
import java.util.*;
import java.sql.*;


public class AccountOP {
    long millis=System.currentTimeMillis();
    private Date createdDate = new Date(millis);



    public void createAccount(Connection connection, String cnp, String type, double initialBalance) throws SQLException{

        String stmt = "INSERT INTO Account (cnpAdmin, type, created_date, balance) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);
        preparedStatement.setString(2, type);
        preparedStatement.setDate(3, createdDate);
        preparedStatement.setDouble(4, initialBalance);
        preparedStatement.executeUpdate();

        System.out.println("Account created!");
    }

    public void topUp(Connection connection, int account_id, double insertMoney) throws SQLException{
        String stmt = "UPDATE account set balance = balance + ? where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setDouble(1, insertMoney);
        preparedStatement.setInt(2, account_id);
        preparedStatement.executeUpdate();
    }

    public void updateAccountType(Connection connection, int account_id, String new_type) throws SQLException{
        String stmt = "UPDATE account SET type = ? where account_id =? ";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, new_type);
        preparedStatement.setInt(2, account_id);
        preparedStatement.executeUpdate();
    }

    public String transferMoney(Connection connection, int sender_id, int receiver_id, double amount) throws SQLException{

        //Check the balance of the sender account
        String checkBalance = "SELECT Balance FROM Account WHERE Account_id = ?";
        PreparedStatement check = connection.prepareStatement(checkBalance);
        check.setInt(1, sender_id);
        ResultSet qResult = check.executeQuery();
        double balance = 0;
        while(qResult.next()) {

           balance = qResult.getDouble("Balance");

        }
        //Check to see if there are enough money on the sender account
        if(balance > amount){

            // Send money
            String stmt = "UPDATE Account SET Balance = Balance + ? WHERE Account_id = ? ";
            String stmt2 = "UPDATE Account SET Balance = Balance - ? WHERE Account_id = ?";

            PreparedStatement prepST1 = connection.prepareStatement(stmt);
            PreparedStatement prepSt2 = connection.prepareStatement(stmt2);


            prepST1.setDouble(1,  amount);
            prepST1.setInt(2, receiver_id);

            prepSt2.setDouble(1, amount);
            prepSt2.setInt(2, sender_id);

            prepST1.executeUpdate();
            prepSt2.executeUpdate();

            return "The transfer was successful!";
        }else
            return "Insufficient funds!";


    }

    public void deleteAccountById(Connection connection, int account_id) throws SQLException{

        String stmt = "DELETE FROM Account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setInt(1, account_id);
        preparedStatement.executeUpdate();
        System.out.println("Account with id: " + account_id + " was removed!");
    }

    public int numberOfAccountsOnClient(Connection connection, String cnp) throws SQLException{
        int numberOfAccounts = 0;

        String stmt = "Select * from account where cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            ++numberOfAccounts;
        }


        return numberOfAccounts;
    }

    public boolean existsAccountByID(Connection connection, int id) throws SQLException{
        String stmt = "Select * from account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

    public String getAccountTypeByID(Connection connection, int id) throws SQLException{
        String stmt = "Select type from account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        String type = "";
        while (resultSet.next())
            type = resultSet.getString("type");
        return type;
    }

    public int getAccountIDbyType(Connection connection, String type, String cnp) throws SQLException{
        String stmt = "Select account_id from account where type = ? and cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, type);
        preparedStatement.setString(2, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();
        int id = 0;

        while (resultSet.next()){
            id = resultSet.getInt("account_id");
        }
        return id;
    }

    public String getAccountCNPfromAccountID(Connection connection, int id) throws SQLException{
        String stmt = "Select cnpAdmin from account where account_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        String cnp = "";

        while (resultSet.next())
            cnp = resultSet.getString("cnpAdmin");

        return cnp;

    }

    public String[] getTypesOfAccountsFromClient(Connection connection, String cnp, String noShowType) throws SQLException{
        String stmt = "Select type from account where cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();
        int i = 0;
        String[] types = new String[5];
        while (resultSet.next()){
            if(resultSet.getString("type").equals(noShowType)){}
            else
                types[i++] = resultSet.getString("type");
        }

        return types;
    }

    public double getBalanceOfAccountByTypeAndCNP(Connection connection, String type, String cnp) throws SQLException{
        String stmt = "Select balance from account where type = ? and cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, type);
        preparedStatement.setString(2, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        double balance = 0;

        while(resultSet.next()){
            balance = resultSet.getDouble("balance");
        }
        return balance;
    }

    public void transferAllFromOneTypeToAnother(Connection connection, String main, String receiver) throws SQLException{
        String stmt = "select balance from account where type = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, main);
        ResultSet resultSet = preparedStatement.executeQuery();
        double balance = 0;

        while (resultSet.next()){
            balance = resultSet.getDouble("balance");
        }

        //Send the money
        String stmt1 = "Update account set balance = balance - ? where type = ?";
        String stmt2 = "Update account Set balance = balance + ? where type = ?";
        preparedStatement = connection.prepareStatement(stmt1);
        preparedStatement.setDouble(1, balance);
        preparedStatement.setString(2, main);
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement(stmt2);
        preparedStatement.setDouble(1, balance);
        preparedStatement.setString(2, receiver);
        preparedStatement.executeUpdate();
    }

    public boolean existsAccountByTypeFromCNP(Connection connection, String type, String cnp) throws SQLException{
        String stmt = "Select * from account where type = ? and cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);

        preparedStatement.setString(1, type);
        preparedStatement.setString(2, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

    public boolean existsAccountByCNP(Connection connection, String cnp) throws SQLException{
        String stmt = "Select * from account where cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            return true;
        else
            return false;
    }

    public Account getAccountByID(Connection connection, int id) throws SQLException{

        Account foundAccount = new Account();

        String stmt = "Select * from account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            foundAccount.setType(resultSet.getString("type"));
            foundAccount.setCreatedDate(resultSet.getDate("created_date"));
            foundAccount.setBalance(resultSet.getDouble("balance"));
            foundAccount.setCnpAdmin(resultSet.getString("cnpAdmin"));
            foundAccount.setAccount_id(resultSet.getInt("account_id"));
        }
        return foundAccount;
    }

    public ArrayList<Account> getAccountByCNP(Connection connection, String cnp) throws SQLException{

        ArrayList<Account> foundAccounts = new ArrayList<>();
        Account fAccount;

        String stmt = "Select * from account where cnpAdmin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setString(1, cnp);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            fAccount = new Account(resultSet.getInt("account_id"), resultSet.getDouble("balance"), resultSet.getString("cnpAdmin"),
                    resultSet.getString("type"), resultSet.getDate("created_date"));
            foundAccounts.add(fAccount);
        }

        return foundAccounts;
    }

    public double getBalanceOfAccountByID(Connection connection, int id) throws SQLException{
        String stmt = "Select balance from account where account_id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        double balance = 0;
        while(resultSet.next())
            balance = resultSet.getDouble("balance");
        return balance;

    }

}
