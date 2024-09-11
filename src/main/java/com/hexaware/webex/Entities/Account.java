package com.hexaware.webex.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Account {
	@Id
	int actNo;
	
	@NotEmpty
	String name;
	
	Double balance;
	
	String email;
	
	@Override
	public String toString() {
		return "Account [actNo=" + actNo + ", name=" + name + ", balance=" + balance + ", email=" + email + "]";
	}

	public int getActNo() {
		return actNo;
	}

	public void setActNo(int actNo) {
		this.actNo = actNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
