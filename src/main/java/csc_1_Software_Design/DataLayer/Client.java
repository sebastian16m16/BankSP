package csc_1_Software_Design.DataLayer;

public class Client {

	Account[] accounts = new Account[5];

	
	int nr_cont=0;
	private int client_id;
	private String cnp;
	private String cni;
	private String first_name;
	private String last_name;
	private String address;
	public static int login_id = 0;

	public Client(String cnp, String cni, String first_name, String last_name, String address){
		this.cnp = cnp;
		this.cni = cni;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		login_id++;
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
