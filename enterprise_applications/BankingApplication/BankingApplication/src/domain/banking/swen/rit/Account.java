package domain.banking.swen.rit;

import java.util.ArrayList;
import java.util.List;

import datasource.banking.swen.rit.AccountDAO;

public class Account {
	
	private Integer clientLogged;
	private List<Client> owners = new ArrayList<Client>();
	private int accountNumber;
	private Double balance;
	
	public Account(Integer clientLogged) {
		this.clientLogged = clientLogged;
	}
	
	public Account()
	{
		
	}
	
	public boolean save()
	{
		AccountDAO dao = new AccountDAO();
		return dao.insert(this);
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Integer getClientLogged() {
		return clientLogged;
	}

	public List<Client> getOwners() {
		return owners;
	}

	public void setOwners(List<Client> owners) {
		this.owners = owners;
	}
 
}
