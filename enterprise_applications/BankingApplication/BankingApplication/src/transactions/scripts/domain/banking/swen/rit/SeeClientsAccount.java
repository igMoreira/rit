package transactions.scripts.domain.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Command;

import java.util.Scanner;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;

public class SeeClientsAccount implements Command{
	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = false;
		Integer accountNumber;
		System.out.println("Type the account number : ");
		accountNumber = Integer.parseInt(input.nextLine());
		AccountDAO dao = new AccountDAO();
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setOwners( dao.getAccountOwners(account) );
		if(account.getOwners().size() > 0 )
		{
			System.out.println("The clients of the account " + account.getAccountNumber() + " are:");
			for (Client client : account.getOwners()) {
				System.out.println("Client : " + client.getName());
			}
			status = true;
		}
		System.out.println("\n\n Type any key o continue.");
		input.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		return status;
	}
}
