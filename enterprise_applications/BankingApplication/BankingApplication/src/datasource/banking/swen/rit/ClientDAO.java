package datasource.banking.swen.rit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;

public class ClientDAO{
	
	private Connection conn;
	private String createClientProcedure = "call createClient(?)";
	private String getClientProcedure = "call getClient(?)";
	private String updateClientProcedure = "call updateClient(?,?)";
	private String deleteClientProcedure = "call deleteClient(?)";
	private String getClientAccountsProcedure = "call getClientAccounts(?)";
	private String joyAccountProcedure = "call joyAccount(?,?)";
	private String quitAccountProcedure = "call quitAccount(?,?)";
	
	public boolean insert(Client c) {
		boolean status = false;
		conn =  ConnectionFactory.getConn();
		try {
			CallableStatement call = conn.prepareCall(createClientProcedure);
			call.setString(1, c.getName());
			status = call.execute();
			ResultSet rst = call.getResultSet();
			while(rst.next())
			{
				c.setClientId(rst.getInt(1));
				c.setName(rst.getString(2));
			}

			call.close();
			rst.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public Client get(Integer clientId) {
		conn = ConnectionFactory.getConn();
		Client c = new Client();
		try {
			CallableStatement call = conn.prepareCall(getClientProcedure);
			call.setInt(1, clientId);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				c.setClientId(rst.getInt(1));
				c.setName(rst.getString(2));
				
			}
			
			call.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public void update(Client client)
	{
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(updateClientProcedure);
			call.setInt(1, client.getClientId());
			call.setString(2, client.getName());
			call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(Client client)
	{
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(deleteClientProcedure);
			call.setInt(1, client.getClientId());
			call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Account> getClientAccounts(Client client)
	{
		List<Account> accounts = new ArrayList<Account>();
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(getClientAccountsProcedure);
			call.setInt(1, client.getClientId());
			call.execute();
			
			ResultSet rst = call.getResultSet();
			
			while (rst.next()) {
				Account a = new Account();
				a.setAccountNumber(rst.getInt(1));
				a.setBalance(rst.getDouble(2));
				accounts.add(a);
			}
			
			call.close();
			rst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public boolean joyAccount(Integer clientId, Integer accountNumber)
	{
		boolean status = false;
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(joyAccountProcedure);
			call.setInt(1, clientId);
			call.setInt(2, accountNumber);
			status = call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public void quitAccount(Integer clientId, Integer accountNumber)
	{
		conn = ConnectionFactory.getConn();
		CallableStatement call;
		try {
			call = conn.prepareCall(quitAccountProcedure);
			call.setInt(1, clientId);
			call.setInt(2, accountNumber);
			call.execute();
			
			call.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
