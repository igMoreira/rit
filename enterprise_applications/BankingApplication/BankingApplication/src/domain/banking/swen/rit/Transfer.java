package domain.banking.swen.rit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import datasource.banking.swen.rit.AccountDAO;
import datasource.banking.swen.rit.TransactionDAO;
import interfaces.domain.banking.swen.rit.Fee;
import interfaces.domain.banking.swen.rit.Operation;

public class Transfer implements Operation {

	private Account originAccount;
	private Account destinationAccount;
	private Double transferValue;
	private List<Fee> fees = new ArrayList<Fee>();
	private static final String type = "Transfer";
	private Double operationCost;
	
	@Override
	public void execute() {
		operationCost = originAccount.getBalance();
		destinationAccount.setBalance(destinationAccount.getBalance() + transferValue);
		originAccount.setBalance(originAccount.getBalance() - transferValue);
		for (Fee fee : fees) {
			originAccount.setBalance(fee.applyFee(originAccount.getBalance()));
		}
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.update(destinationAccount);
		accountDAO.update(originAccount);
		Transaction t = new Transaction();
		t.setAccountNumber(originAccount.getAccountNumber());
		t.setOperation(type); 
		t.setDate(new Date( new java.util.Date().getTime()));
		operationCost = (operationCost - originAccount.getBalance() - transferValue);
		t.setOperationCost(operationCost);
		TransactionDAO transactionDAO = new TransactionDAO();
		transactionDAO.insert(t);
	}
	
	@Override
	public void addFee(Fee fee) {
		fees.add(fee);
	}
	
	public Transfer(Account originAccount, Account destinationAccount, Double value) {
		this.originAccount = originAccount;
		this.destinationAccount = destinationAccount;
		this.transferValue = value;
	}

	public Account getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(Account originAccount) {
		this.originAccount = originAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(Account destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public Double getTransferValue() {
		return transferValue;
	}

	public void setTransferValue(Double transferValue) {
		this.transferValue = transferValue;
	}

	public List<Fee> getFees() {
		return fees;
	}

	public void setFees(List<Fee> fees) {
		this.fees = fees;
	}

	@Override
	public String getOperationType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public Double getOperationCost() {
		// TODO Auto-generated method stub
		return operationCost;
	}

}
