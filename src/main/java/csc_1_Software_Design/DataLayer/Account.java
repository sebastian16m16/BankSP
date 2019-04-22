package csc_1_Software_Design.DataLayer;

import java.sql.Date;

public class Account {

	protected int account_id;
	protected double balance;
	protected String cnpAdmin;
	protected String type;
	protected Date createdDate;

	public Account(int id, double balance, String cnpAdmin, String type, Date createdDate){
		this.account_id = id;
		this.balance = balance;
		this.cnpAdmin = cnpAdmin;
		this.type = type;
		this.createdDate = createdDate;
	}

	public Account(){}

	public String getCnpAdmin() {
		return cnpAdmin;
	}

	public void setCnpAdmin(String cnpAdmin) {
		this.cnpAdmin = cnpAdmin;
	}

	/**
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}

	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
