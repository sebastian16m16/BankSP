package csc_1_Software_Design.DataLayer;

import java.sql.Date;

public class AccountRO extends Account{

	public AccountRO(Date created_date, String type, String CNP) {
		this.createdDate = created_date;
		this.type = type;
		this.cnp = CNP;
	}
	
	public AccountRO(Date created_date, String type, String CNP, int initialBalance) {
		this.createdDate = created_date;
		this.type = type;
		this.cnp = CNP;
		this.balance = initialBalance;
	}
	
	
}
