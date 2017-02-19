package transactions.scripts.domain.banking.swen.rit;

import java.util.List;
import java.util.Scanner;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Client;
import domain.banking.swen.rit.OverdraftFee;
import domain.banking.swen.rit.PrintingFee;
import domain.banking.swen.rit.Transfer;
import domain.banking.swen.rit.WireTransferFee;
import interfaces.domain.banking.swen.rit.Command;
import interfaces.domain.banking.swen.rit.Operation;

public class DoTransfer implements Command {

	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = false;
		Client client = new Client();
		Account originAccount = new Account();
		Account destinationAccount = new Account();
		Double value;
		
		System.out.println("Type your client id : ");
		client.setClientId(Integer.parseInt(input.nextLine()));
		
		System.out.println("Type your account number :");
		originAccount.setAccountNumber(Integer.parseInt(input.nextLine()));
		
		System.out.println("Type the account number to transfer : ");
		destinationAccount.setAccountNumber(Integer.parseInt(input.nextLine()));
		
		System.out.println("Type the value you want to transfer : ");
		value = Double.parseDouble(input.nextLine());
		
		AccountDAO accountDAO = new AccountDAO();
		originAccount = accountDAO.get(originAccount.getAccountNumber());
		destinationAccount = accountDAO.get(destinationAccount.getAccountNumber());
		List<Client> list = accountDAO.getAccountOwners(originAccount);
		for (Client client2 : list) {
			if(client.getClientId().equals(client2.getClientId()))
			{
				status = true;
				break;
			}
		}
		if(status)
		{
			if(originAccount != null && destinationAccount != null)
			{
				status = true;
				Operation op = new Transfer(originAccount, destinationAccount, value);
				op.addFee(new WireTransferFee());
				op.addFee(new PrintingFee());
				if( originAccount.getBalance() < value)
					op.addFee(new OverdraftFee());
				op.execute();

				System.out.println("The operation was successfully performed");

				System.out.println("Operation Type : " + op.getOperationType());
				System.out.println("Account Number : " + originAccount.getAccountNumber() );
				System.out.println("Account balance : $"  + originAccount.getBalance());
				System.out.println("Operation Cost : $"  + op.getOperationCost());

			}
			else
			{
				System.out.println("The information provided has an error, please revise that and try again.");
			}
		}
		else
		{
			System.out.println("The origin account do not belongs to the specified client");
		}
		
		System.out.println("\n\n Type any key o continue.");
		input.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		
		return status;
	}

}
