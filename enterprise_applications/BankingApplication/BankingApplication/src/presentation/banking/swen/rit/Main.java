package presentation.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Command;

import java.util.Scanner;

import transactions.scripts.domain.banking.swen.rit.AccountByBalanceList;
import transactions.scripts.domain.banking.swen.rit.GenerateTransactionsList;
import transactions.scripts.domain.banking.swen.rit.TransactionsByOperation;
import transactions.scripts.domain.banking.swen.rit.CreateAccount;
import transactions.scripts.domain.banking.swen.rit.DoCheck;
import transactions.scripts.domain.banking.swen.rit.DoDeposit;
import transactions.scripts.domain.banking.swen.rit.DoPayment;
import transactions.scripts.domain.banking.swen.rit.GenerateAccountList;
import transactions.scripts.domain.banking.swen.rit.JoyAccount;
import transactions.scripts.domain.banking.swen.rit.SeeAccountClient;
import transactions.scripts.domain.banking.swen.rit.SeeClientsAccount;
import transactions.scripts.domain.banking.swen.rit.VerifyAccount;
import transactions.scripts.domain.banking.swen.rit.DoTransfer;

public class Main {

	private static Command command;
	private static Scanner input;
	
	public static void main(String args[])
	{
		input = new Scanner(System.in);
		int option;
		
		do{
			System.out.println("Choose an option :");
			System.out.println("1. Create Account"); // OK
			System.out.println("2. Verify account status"); // OK
			System.out.println("3. Do transfer "); // OK
			System.out.println("4. Do payment "); // OK
			System.out.println("5. Do deposit "); // OK
			System.out.println("6. Use check "); // OK
			System.out.println("7. Joy another account"); // OK
			System.out.println("8. See accounts of a client"); // OK
			System.out.println("9. See clients of a account"); // OK
			System.out.println("10. Generate Accounts list");
			System.out.println("11. Generate Account list with balance filter");
			System.out.println("12. For an account generate a list of checks of the last 30 days");
			System.out.println("13. For an account generate a list of operations of the last 30 days");
			System.out.println("14. Exit"); // OK
			option = input.nextInt();
			
			switch(option){
			case 1: 
				command = new CreateAccount();
				command.execute();break;
			case 2:
				command = new VerifyAccount();
				command.execute();break;
			case 3:
				command = new DoTransfer();
				command.execute();break;
			case 4:
				command = new DoPayment();
				command.execute();break;
			case 5:
				command = new DoDeposit();
				command.execute();break;
			case 6:
				command = new DoCheck();
				command.execute();break;
			case 7:
				command = new JoyAccount();
				command.execute();break;
			case 8:
				command = new SeeAccountClient();
				command.execute();break;
			case 9:
				command = new SeeClientsAccount();
				command.execute();break;
			case 10:
				command = new GenerateAccountList();
				command.execute();break;
			case 11:
				command = new AccountByBalanceList();
				command.execute();break;
			case 12:
				command = new TransactionsByOperation();
				command.execute();break;
			case 13:
				command = new GenerateTransactionsList();
				command.execute();break;
			case 14: return;
			}
			
		}while(true);
	}
	
}
