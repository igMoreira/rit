package domain.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Fee;

public class PrintingFee extends Fee{

	private Double value = 1.50;
	
	@Override
	public Double applyFee(Double balance) {
		balance -= value;
		return balance;
	}

}
