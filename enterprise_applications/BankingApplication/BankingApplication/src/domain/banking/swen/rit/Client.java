package domain.banking.swen.rit;

import java.util.ArrayList;
import java.util.List;

import datasource.banking.swen.rit.ClientDAO;

public class Client {

	private Integer clientId;
	private String name;
	private List<Account> accounts = new ArrayList<Account>();
	
	public boolean save()
	{
		boolean status;
		ClientDAO dao = new ClientDAO();
		status = dao.insert(this);
		return status;
	}
	
	public static Client getClient(Integer clientId){
		ClientDAO dao = new ClientDAO();
		Client c = dao.get(clientId);
		return c;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
