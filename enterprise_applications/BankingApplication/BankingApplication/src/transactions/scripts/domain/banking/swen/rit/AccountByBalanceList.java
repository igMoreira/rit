package transactions.scripts.domain.banking.swen.rit;

import java.util.List;
import java.util.Scanner;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import interfaces.domain.banking.swen.rit.Command;

public class AccountByBalanceList implements Command{
	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		Double balance;
		System.out.println("Type the value of balance to filter : ");
		balance = Double.parseDouble(input.nextLine());
		AccountDAO accountDAO = new AccountDAO();
		List<Account> accounts = accountDAO.getAllAccountsByBalance(balance);
		
		for (Account account : accounts) {
			System.out.println("Account number: " + account.getAccountNumber());
			System.out.println("Balance : $" + account.getBalance());
			System.out.println("\n");
		}
		return false;
	}

}
