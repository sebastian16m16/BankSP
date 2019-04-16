package csc_1_Software_Design.DataLayer;

import java.sql.Date;

public class AccountRO extends Account{

	public AccountRO(int account_id, int client_id, Date created_date, String type) {
		this.createdDate = created_date;
		this.type = type;
		this.account_id = account_id;
		this.client_id = client_id;

	}

	public AccountRO(int account_id, int client_id, Date created_date, String type,int initialBalance) {
		this.createdDate = created_date;
		this.type = type;
		this.balance = initialBalance;
		this.account_id = account_id;
		this.client_id = client_id;

	}
	
	
}
