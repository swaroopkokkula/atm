package com.zinkworks.atm.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	private Long accountNumber;

	@Column(name = "pin")
	private int pin;

	@Column(name = "open_balance")
	private double openingBalance;

	@Column(name = "overdraft_balance")
	private double overdraftBalance;

	public Account() {
	}

	public synchronized Long getAccountNumber() {
		return accountNumber;
	}

	public synchronized void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public synchronized int getPin() {
		return pin;
	}

	public synchronized void setPin(int pin) {
		this.pin = pin;
	}

	public synchronized double getOpeningBalance() {
		return openingBalance;
	}

	public synchronized void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public synchronized double getOverdraftBalance() {
		return overdraftBalance;
	}

	public synchronized void setOverdraftBalance(double overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

}
