package csc_1_Software_Design.DataLayer;


import java.sql.SQLException;

public class Client {

	private int client_id;
	private String cnp;
	private String cni;
	private String first_name;
	private String last_name;
	private String address;
	private int login_id;
	private String cnpAdmin;


	public String getCnpAdmin() {
		return cnpAdmin;
	}

	public void setCnpAdmin(String cnpAdmin) {
		this.cnpAdmin = cnpAdmin;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
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
