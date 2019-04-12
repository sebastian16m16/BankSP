package csc_1_Software_Design.DataLayer;

import java.sql.Date;

public class AccountEUR extends Account {
	
	public AccountEUR(Date created_date, String type, String CNP) {
		this.createdDate = created_date;
		this.type = type;
		this.cnp = CNP;
	}
	
	public AccountEUR(Date created_date, String type, String CNP, int initialBalance) {
		this.createdDate = created_date;
		this.type = type;
		this.cnp = CNP;
		this.balance = initialBalance;
	}
	
}
