package datasource.banking.swen.rit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;

public class AccountDAO{
	
	private Connection conn;
	private String createAccountProcedure = "call createAccount(?)";
	private String getAccountProcedure = "call getAccount(?)";
	private String updateAccountProcedure = "call updateAccount(?,?)";
	private String deleteAccountProcedure = "call deleteAccount(?)";
	private String getAccountOwnersProcedure = "call getAccountOwners(?)";
	private String getAllAccountsProcedure = "call getAllAccounts()";
	private String getAccountsBalanceFilterProcedure = "call getAccountsByBalance(?)";
	
	
	public boolean insert(Account account) {
		boolean status = false;
		conn =  ConnectionFactory.getConn();
		try {
			CallableStatement call = conn.prepareCall(createAccountProcedure);
			call.setDouble(1, account.getClientLogged());
			status = call.execute();
			ResultSet rst = call.getResultSet();
			while(rst.next())
			{
				account.setAccountNumber(rst.getInt(1));
				account.setBalance(rst.getDouble(2));
			}
			call.close();
			rst.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public Account get(Integer accountNumber) {
		conn = ConnectionFactory.getConn();
		Account account = new Account();
		try {
			CallableStatement call = conn.prepareCall(getAccountProcedure);
			call.setInt(1, accountNumber);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				account.setAccountNumber(rst.getInt(1));
				account.setBalance(rst.getDouble(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public void update(Account account)
	{
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(updateAccountProcedure);
			call.setInt(1, account.getAccountNumber());
			call.setDouble(2, account.getBalance());
			call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(Account account)
	{
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(deleteAccountProcedure);
			call.setInt(1, account.getAccountNumber());
			call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Client> getAccountOwners(Account account)
	{
		List<Client> clients = new ArrayList<Client>();
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(getAccountOwnersProcedure);
			call.setInt(1, account.getAccountNumber());
			call.execute();
			
			ResultSet rst = call.getResultSet();
			
			while (rst.next()) {
				Client c = new Client();
				c.setClientId(rst.getInt(1));
				c.setName(rst.getString(2));
				clients.add(c);
			}
			
			call.close();
			rst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	public List<Account> getAllAccounts()
	{
		List<Account> list = new ArrayList<Account>();
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(getAllAccountsProcedure);
			call.execute();
			
			ResultSet rst = call.getResultSet();
			
			while (rst.next()) {
				Account a = new Account();
				a.setAccountNumber(rst.getInt(1));
				a.setBalance(rst.getDouble(2));
				list.add(a);
			}
			
			call.close();
			rst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Account> getAllAccountsByBalance(Double balance)
	{
		List<Account> list = new ArrayList<Account>();
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(getAccountsBalanceFilterProcedure);
			call.setDouble(1, balance);
			call.execute();
			
			ResultSet rst = call.getResultSet();
			
			while (rst.next()) {
				Account a = new Account();
				a.setAccountNumber(rst.getInt(1));
				a.setBalance(rst.getDouble(2));
				list.add(a);
			}
			
			call.close();
			rst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
