package csc_1_Software_Design.DataLayer;

import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import csc_1_Software_Design.BusinessLayer.UserOp;

import java.sql.*;

public class ClientOP{

        public String getFullClientName(Connection connection, String cnp) throws SQLException{
            String stmt = "Select first_name, last_name from client where cnp = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            String first_name = "";
            String last_name = "";
            while(resultSet.next()){
                first_name = resultSet.getString("First_name");
                last_name = resultSet.getString("last_name");
            }

            return last_name + " " +first_name;

        }

        public void insertClient(Connection con, Client client) throws SQLException{
            String statement = "INSERT INTO client (CNP, CNI, First_name, Last_name, address, cnpAdmin) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prepSt = con.prepareStatement(statement);

            prepSt.setString(1, client.getCnp());
            prepSt.setString(2, client.getCni());
            prepSt.setString(3, client.getFirst_name());
            prepSt.setString(4, client.getLast_name());
            prepSt.setString(5, client.getAddress());
            prepSt.setString(6, client.getCnpAdmin());

            prepSt.executeUpdate();
            System.out.println("Client created!");
        }

        public Client getClientByID(Connection connection, int client_id) throws SQLException {

            Client foundClient = new Client();

            Statement stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM client where client_id=?";
            PreparedStatement prepSt = connection.prepareStatement(sqlQuery);
            prepSt.setInt(1, client_id);

            System.out.println("Looking for client ...");
            ResultSet queryResult = prepSt.executeQuery();


            while (queryResult.next()){
                //retrieve by column name
                int id = queryResult.getInt("client_id");
                String cnp = queryResult.getString("cnp");
                String cni = queryResult.getString("cni");
                String last_name = queryResult.getString("last_name");
                String first_name = queryResult.getString("first_name");
                String address = queryResult.getString("address");

                foundClient.setClient_id(id);
                foundClient.setCnp(cnp);
                foundClient.setCni(cni);
                foundClient.setLast_name(last_name);
                foundClient.setFirst_name(first_name);
                foundClient.setAddress(address);

                //Display values
                System.out.println("Client ID: " + id + " CNP: " + cnp + " CNI: " + cni +
                        " Last Name: " + last_name + " First_name: " + first_name + " Address:" + address);

            }
            queryResult.close();
            prepSt.close();

            return foundClient;
        }

        public Client getClientByCNP(Connection connection, String cnp) throws SQLException{

            Client foundClient = new Client();

            String stmt = "Select * from client where cnp = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {

                int client_id = resultSet.getInt("client_id");
                String cni = resultSet.getString("cni");
                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String address = resultSet.getString("address");
                String cnp1 = resultSet.getString("cnp");

                foundClient.setClient_id(client_id);
                foundClient.setCni(cni);
                foundClient.setLast_name(last_name);
                foundClient.setFirst_name(first_name);
                foundClient.setAddress(address);
                foundClient.setCnp(cnp1);
            }

            System.out.println("Client found!");
            return foundClient;


        }

        public void deleteClient(Connection connection, String cnp) throws SQLException{
            String stmt = "DELETE FROM account where cnpAdmin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, cnp);
            preparedStatement.executeUpdate();

            String stmt1 = "DELETE FROM login where CNP = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(stmt1);
            preparedStatement1.setString(1, cnp);
            preparedStatement1.executeUpdate();

            String statement = "DELETE FROM client where cnp = ?";
            PreparedStatement prepSt = connection.prepareStatement(statement);
            prepSt.setString(1, cnp);
            prepSt.executeUpdate();

            System.out.println("Client successfully deleted! and everything linked to the client!");
        }

        public void updateClient(Connection connection, String cnp, String first_name, String last_name, String address, String cni) throws SQLException{

            System.out.println("UPDATEing ...");
            String statement = "UPDATE client SET first_name =?, last_name=?, cni=?, address=? where cnp =?";
            PreparedStatement prepSt = connection.prepareStatement(statement);
               prepSt.setString(1, first_name);
               prepSt.setString(2, last_name);
               prepSt.setString(3, cni);
               prepSt.setString(4, address);
               prepSt.setString(5, cnp);
            prepSt.executeUpdate();

            System.out.println("Client with cnp: " + cnp + " has been updated!");
        }

        public boolean existsClientByCNP(Connection connection, String cnp) throws SQLException{
            String stmt = "Select * from Client where cnp = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = false;

            while (resultSet.next()){
                found = true;
            }
            return found;
        }

        public boolean existsClientByID(Connection connection, int ID) throws SQLException{
        String stmt = "Select * from Client where client_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(stmt);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean found = false;

        while (resultSet.next()){
            found = true;
        }
        return found;
    }

        public String getClientCNPfromID(Connection connection, int id) throws SQLException{
            String cnp = "";

            String stmt = "Select CNP from client where client_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cnp = resultSet.getString("CNP");
            }
            return cnp;
        }
}

