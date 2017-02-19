package transactions.scripts.domain.banking.swen.rit;

import java.util.Scanner;

import datasource.banking.swen.rit.ClientDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;
import interfaces.domain.banking.swen.rit.Command;

public class JoyAccount implements Command{
	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = true;
		Integer clientId;
		Integer accountNumber;
		
		System.out.println("Type the client Id : ");
		clientId = Integer.parseInt(input.nextLine());
		
		System.out.println("Type the account number to join : ");
		accountNumber = Integer.parseInt(input.nextLine());
		
		ClientDAO dao = new ClientDAO();
		Client c = new Client();
		c.setClientId(clientId);
		
		for (Account account : dao.getClientAccounts(c)) {
			if(accountNumber.equals(account.getAccountNumber()))
			{
				status = false;
				break;
			}
		}
		
		if(status)
		{
			dao.joyAccount(clientId, accountNumber);
			System.out.println("Account Succesfully bounded to client.");
		}
		else
		{
			System.out.println("This account is already bounded to this client");
		}
		
		System.out.println("\n\n Type any key o continue.");
		input.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		
		return status;
	}

}
