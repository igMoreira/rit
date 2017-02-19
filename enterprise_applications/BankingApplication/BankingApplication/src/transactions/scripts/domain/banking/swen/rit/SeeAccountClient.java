package transactions.scripts.domain.banking.swen.rit;

import java.util.Scanner;

import datasource.banking.swen.rit.ClientDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;
import interfaces.domain.banking.swen.rit.Command;

public class SeeAccountClient implements Command{

	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = false;
		Integer clientId;
		System.out.println("Type the client id : ");
		clientId = Integer.parseInt(input.nextLine());
		ClientDAO dao = new ClientDAO();
		Client client = new Client();
		client.setClientId(clientId);
		client.setAccounts( dao.getClientAccounts(client) );
		if(client.getAccounts().size() > 0 )
		{
			System.out.println("The accounts of the client " + client.getClientId() + " are:");
			for (Account account : client.getAccounts()) {
				System.out.println("Account Number : " + account.getAccountNumber());
			}
			status = true;
		}
		System.out.println("\n\n Type any key o continue.");
		input.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		return status;
	}

}
