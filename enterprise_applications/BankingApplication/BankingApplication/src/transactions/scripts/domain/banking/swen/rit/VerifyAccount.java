package transactions.scripts.domain.banking.swen.rit;

import java.util.Scanner;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import interfaces.domain.banking.swen.rit.Command;

public class VerifyAccount implements Command {

	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = false;
		Integer accountNumber;
		Account account = null;
		
		System.out.println("Type the account number : ");
		accountNumber = Integer.parseInt( input.nextLine());
		
		AccountDAO dao = new AccountDAO();
		account = dao.get(accountNumber);
		
		if(account != null)
		{
			status = true;
			System.out.println("Account number : " + account.getAccountNumber());
			System.out.println("Balance: " + account.getBalance());

			System.out.println("\n\n Type any key to continue...");
			input.nextLine();
		}
		return status;
	}
	
	

}
