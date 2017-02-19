package transactions.scripts.domain.banking.swen.rit;

import java.util.Scanner;

import datasource.banking.swen.rit.AccountDAO;
import domain.banking.swen.rit.Account;
import domain.banking.swen.rit.Check;
import domain.banking.swen.rit.OverdraftFee;
import domain.banking.swen.rit.PrintingFee;
import interfaces.domain.banking.swen.rit.Command;
import interfaces.domain.banking.swen.rit.Operation;

public class DoCheck implements Command{

	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		boolean status = false;
		Account originAccount = new Account();
		Double value;
		
		System.out.println("Type your account number of the check:");
		originAccount.setAccountNumber(Integer.parseInt(input.nextLine()));
		
		System.out.println("Type the value of the check : ");
		value = Double.parseDouble(input.nextLine());
		
		AccountDAO accountDAO = new AccountDAO();
		originAccount = accountDAO.get(originAccount.getAccountNumber());
		if(originAccount != null)
		{
			status = true;
			Operation op = new Check(originAccount, value);
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
		
		System.out.println("\n\n Type any key o continue.");
		input.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		
		return status;
	}

}
