package csc_1_Software_Design.DataLayer;

import java.sql.Date;

public class Account {
	
	private String cnp;
	int account_id;
	private int balance;
	Date createdDate;
	String type;
	
	
	public Account(int account_id, Date createdDate, String type, int initialDeposit) {
		this.account_id = account_id;
		this.createdDate = createdDate;
		this.type = type;
		this.balance = initialDeposit;
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
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
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
