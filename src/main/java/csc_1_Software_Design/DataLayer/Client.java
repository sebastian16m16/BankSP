package csc_1_Software_Design.DataLayer;

import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {

	Account[] accounts = new Account[5];

	
	int nr_cont=0;
	private static int client_id;
	private String cnp;
	private String cni;
	private String first_name;
	private String last_name;
	private String address;
	private int login_id;


	public Client(Connection connection, String cnp, String cni, String first_name, String last_name, String address) throws SQLException {
		this.cnp = cnp;
		this.cni = cni;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		client_id++;

		String stmt = "INSERT INTO Client ('Client_id', 'CNP', 'CNI', 'Last Name', 'First Name', 'Address', 'login_id') " +
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(stmt);
		preparedStatement.setInt(1,client_id);
		preparedStatement.setString(2,cnp);
		preparedStatement.setString(3,cni);
		preparedStatement.setString(4, last_name);
		preparedStatement.setString(5,first_name);
		preparedStatement.setString(6,address);
		preparedStatement.setInt(7,login_id);
		preparedStatement.executeUpdate();

	}


	/**
	 * @return the client_id
	 */
	public int getClient_id() {
		return client_id;
	}

	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	/**
	 * @return the cnp
	 */
	public String getCnp() {
		return cnp;
	}

	/**
	 * @param cnp the cnp to set
	 */
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	/**
	 * @return the cni
	 */
	public String getCni() {
		return cni;
	}

	/**
	 * @param cni the cni to set
	 */
	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	


	
	
}
