package transactions.scripts.domain.banking.swen.rit;

import java.util.Scanner;

import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;
import interfaces.domain.banking.swen.rit.Command;

public class CreateAccount implements Command {

	Client c = null;
	private Scanner input;
	
	@Override
	public boolean execute() {
		boolean status = false;
		status = getClient();
		if(status)
			status = createAccount();
		return status;
	}
	
	private boolean getClient()
	{
		boolean status = true;
		input = new Scanner(System.in);
		String text = null;
		
		System.out.println("The client is already registered in our bank ? (Y/N) ");
		text = input.nextLine();
		
		if(text.equalsIgnoreCase("Y"))
		{
			System.out.println("Type the client id: ");
			text = input.nextLine();
			
			c = Client.getClient(Integer.parseInt(text));
		}
		else if(text.equalsIgnoreCase("N"))
		{
			c = new Client();
			
			System.out.println("Type the client Name: ");
			text = input.nextLine();
			c.setName(text);
			c.save();
		}
		else
		{
			System.out.println("Invalid option");
			status = false;
		}
		return status;
	}
	
	private boolean createAccount(){
		boolean status = false;
		Account a = new Account( c.getClientId() );
		status = a.save();
		if(status)
		{
			System.out.println("Account succesfully createad");
			System.out.println("Client ID :" + c.getClientId());
			System.out.println("Client Name : " + c.getName());
			System.out.println("Account Number : " + a.getAccountNumber() );
			System.out.println("Account balance : $"  + a.getBalance());
			System.out.println("\n\n Type any key o continue.");
			input.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		}
		return status;
	}
}
