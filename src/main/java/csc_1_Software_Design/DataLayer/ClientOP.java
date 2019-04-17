package csc_1_Software_Design.DataLayer;

import java.sql.*;

public class ClientOP {


        public void insertClient(Connection con, Client client) throws SQLException{
            String statement = "INSERT INTO client (CNP, CNI, First_name, Last_name, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prepSt = con.prepareStatement(statement);

            prepSt.setString(1, client.getCnp());
            prepSt.setString(2, client.getCni());
            prepSt.setString(3, client.getFirst_name());
            prepSt.setString(4, client.getLast_name());
            prepSt.setString(5, client.getAddress());

            prepSt.executeUpdate();
        }

        public Client getClientByID(Connection connection, int client_id) throws SQLException {

            Client foundClient = new Client();

            Statement stmt = connection.createStatement();
            String sqlQuery = "SELECT * FROM client where client_id=?";
            PreparedStatement prepSt = connection.prepareStatement(sqlQuery);
            prepSt.setInt(1, client_id);

            System.out.println("Looking for client ...");
            ResultSet queryResult = prepSt.executeQuery();


            if (queryResult.next()){
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

        public void deleteClient(Connection connection, Client client) throws SQLException{
            String statement = "DELETE FROM client where client_id =?";
            PreparedStatement prepSt = connection.prepareStatement(statement);
            prepSt.setInt(1, client.getClient_id());
            prepSt.executeUpdate();
        }

        public void updateClient(Connection connection, Client client, String first_name, String last_name, String address, String cnp, String cni) throws SQLException{

            System.out.println("UPDATEing ...");
            String statement = "UPDATE client SET first_name =?, last_name=?, cnp=?, cni=?, address=? where client_id =?";
            PreparedStatement prepSt = connection.prepareStatement(statement);
               prepSt.setString(1, first_name);
               prepSt.setString(2, last_name);
               prepSt.setString(3, cnp);
               prepSt.setString(4, cni);
               prepSt.setString(5, address);
               prepSt.setInt(6, client.getClient_id());
            prepSt.executeUpdate();

            System.out.println("Client with id: " + client.getClient_id() + " has been updated!");
        }


}

