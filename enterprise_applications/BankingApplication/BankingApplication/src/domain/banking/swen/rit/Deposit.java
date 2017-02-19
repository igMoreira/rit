package domain.banking.swen.rit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import datasource.banking.swen.rit.AccountDAO;
import datasource.banking.swen.rit.TransactionDAO;
import interfaces.domain.banking.swen.rit.Fee;
import interfaces.domain.banking.swen.rit.Operation;

public class Deposit implements Operation {

	private Account originAccount;
	private Double depositValue;
	private List<Fee> fees = new ArrayList<Fee>();
	private static final String type = "Deposit";
	private Double operationCost;
	
	@Override
	public void execute() {
		operationCost = originAccount.getBalance();
		originAccount.setBalance(originAccount.getBalance() + depositValue);
		for (Fee fee : fees) {
			originAccount.setBalance(fee.applyFee(originAccount.getBalance()));
		}
		AccountDAO accountDAO = new AccountDAO();
		accountDAO.update(originAccount);
		Transaction t = new Transaction();
		t.setAccountNumber(originAccount.getAccountNumber());
		t.setOperation(type); 
		t.setDate(new Date( new java.util.Date().getTime()));
		operationCost = (operationCost - originAccount.getBalance() + depositValue);
		t.setOperationCost(operationCost);
		TransactionDAO transactionDAO = new TransactionDAO();
		transactionDAO.insert(t);
	}
	
	@Override
	public void addFee(Fee fee) {
		fees.add(fee);
	}
	
	public Deposit(Account originAccount, Double value) {
		this.originAccount = originAccount;
		this.depositValue = value;
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
