package datasource.banking.swen.rit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.banking.swen.rit.Transaction;

public class TransactionDAO {
	private Connection conn;
	private String createTransactionProcedure = "call createTransaction(?,?,?,?)";
	private String getTransactionProcedure = "call getTransaction(?)";
	private String getTransactionsByOperationProcedure = "call getTransactionsByOperation(?)";
	private String getTransactionsByAccountProcedure = "call getTransactionsByAccount(?)";
	private String getTransactionByAccountDateOperationFilterProcedure = "call getTransactionsByAccountAndDateAndOperation(?,?,?,?)";
	private String getTransactionByAccountDateFilterProcedure = "call getTransactionsByAccountAndDate(?,?,?)";
	
	
	public boolean insert(Transaction transaction) {
		boolean status = false;
		conn =  ConnectionFactory.getConn();
		try {
			CallableStatement call = conn.prepareCall(createTransactionProcedure);
			call.setDate(1, transaction.getDate());
			call.setString(2, transaction.getOperation());
			call.setDouble(3, transaction.getOperationCost());
			call.setInt(4, transaction.getAccountNumber());
			status = call.execute();
			
			call.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public Transaction get(Integer transactionId) {
		conn = ConnectionFactory.getConn();
		Transaction t = new Transaction();
		try {
			CallableStatement call = conn.prepareCall(getTransactionProcedure);
			call.setInt(1, transactionId);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				t.setDate(rst.getDate(2));
				t.setOperation(rst.getString(3));
				t.setOperationCost(rst.getDouble(4));
				t.setAccountNumber(rst.getInt(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public List<Transaction> getByAccount(Integer accountNumber) {
		conn = ConnectionFactory.getConn();
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			CallableStatement call = conn.prepareCall(getTransactionsByAccountProcedure);
			call.setInt(1, accountNumber);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				Transaction t = new Transaction();
				t.setDate(rst.getDate(2));
				t.setOperation(rst.getString(3));
				t.setOperationCost(rst.getDouble(4));
				t.setAccountNumber(rst.getInt(5));
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Transaction> getByOperation(String operation) {
		conn = ConnectionFactory.getConn();
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			CallableStatement call = conn.prepareCall(getTransactionsByOperationProcedure);
			call.setString(1, operation);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				Transaction t = new Transaction();
				t.setDate(rst.getDate(2));
				t.setOperation(rst.getString(3));
				t.setOperationCost(rst.getDouble(4));
				t.setAccountNumber(rst.getInt(5));
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Transaction> getByAccountAndOperationAndDate(Integer accountNumber, String operation, Date dateFrom, Date dateTo) {
		conn = ConnectionFactory.getConn();
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			CallableStatement call = conn.prepareCall(getTransactionByAccountDateOperationFilterProcedure);
			call.setInt(1, accountNumber);
			call.setString(2, operation);
			call.setDate(3, dateFrom);
			call.setDate(4, dateTo);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				Transaction t = new Transaction();
				t.setDate(rst.getDate(2));
				t.setOperation(rst.getString(3));
				t.setOperationCost(rst.getDouble(4));
				t.setAccountNumber(rst.getInt(5));
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Transaction> getByAccountAndDate(Integer accountNumber, Date dateFrom, Date dateTo) {
		conn = ConnectionFactory.getConn();
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			CallableStatement call = conn.prepareCall(getTransactionByAccountDateFilterProcedure);
			call.setInt(1, accountNumber);
			call.setDate(2, dateFrom);
			call.setDate(3, dateTo);
			call.execute();
			ResultSet rst  = call.getResultSet();
			while(rst.next())
			{
				Transaction t = new Transaction();
				t.setDate(rst.getDate(2));
				t.setOperation(rst.getString(3));
				t.setOperationCost(rst.getDouble(4));
				t.setAccountNumber(rst.getInt(5));
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
