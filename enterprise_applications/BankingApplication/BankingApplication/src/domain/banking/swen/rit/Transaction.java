package domain.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Operation;

import java.sql.Date;

public class Transaction {

	private Date date;
	private String operation;
	private Integer accountNumber;
	private Double operationCost;
	
	public Transaction(Integer accountNumber, Operation operation) {
		
	}
	
	public Transaction() {
		
	}
	
	public Integer getAccountNumber() {
		return accountNumber;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public Double getOperationCost() {
		return operationCost;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setOperationCost(Double operationCost) {
		this.operationCost = operationCost;
	}

	public void save(Transaction transaction)
	{
		
	}
}
