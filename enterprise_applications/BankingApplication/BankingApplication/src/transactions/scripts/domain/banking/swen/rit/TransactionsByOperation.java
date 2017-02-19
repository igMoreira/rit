package transactions.scripts.domain.banking.swen.rit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import datasource.banking.swen.rit.TransactionDAO;
import domain.banking.swen.rit.Transaction;
import interfaces.domain.banking.swen.rit.Command;

public class TransactionsByOperation implements Command {

	Scanner input = new Scanner(System.in);
	
	@Override
	public boolean execute() {
		 TransactionDAO transactionDAO = new TransactionDAO();
		 Integer accountNumber;
		 System.out.println("Type the number of thee account : ");
		 accountNumber = Integer.parseInt(input.nextLine());
		 Calendar dt = Calendar.getInstance();
		 dt.add(Calendar.DAY_OF_MONTH, -30);
		 Date dateFrom = dt.getTime();
		 Date dateTo = Calendar.getInstance().getTime();
		 List<Transaction> transactions = transactionDAO.getByAccountAndOperationAndDate(accountNumber, "Check" , new java.sql.Date(dateFrom.getTime()) , new java.sql.Date(dateTo.getTime()));
		
		 for (Transaction t : transactions) {
			 System.out.println("Account number: " + t.getAccountNumber());
			 System.out.println("Operation type : " + t.getOperation());
			 System.out.println("Operation Cost: " + t.getOperationCost());
			 System.out.println("Operation Date : " + t.getDate());
			 System.out.println("\n");
		 }
		 
		 System.out.println("\n\n Type any key o continue.");
		 input.nextLine();
		 System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
			
		 return false;
	}

}
