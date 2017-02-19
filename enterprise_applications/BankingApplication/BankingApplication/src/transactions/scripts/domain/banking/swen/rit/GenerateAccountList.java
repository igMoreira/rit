package transactions.scripts.domain.banking.swen.rit;

import java.util.List;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;
import interfaces.domain.banking.swen.rit.Command;

public class GenerateAccountList implements Command {

	@Override
	public boolean execute() {
		AccountDAO accountDAO = new AccountDAO();
		List<Account> accounts = accountDAO.getAllAccounts();
		
		for (Account account : accounts) {
			System.out.println("Account number: " + account.getAccountNumber());
			System.out.println("Balance : $" + account.getBalance());
			System.out.println("Owners: ");
			for (Client owner : accountDAO.getAccountOwners(account)) {
				System.out.println("\t "+ owner.getName());
			}
			System.out.println("\n");
		}
		return false;
	}

}
