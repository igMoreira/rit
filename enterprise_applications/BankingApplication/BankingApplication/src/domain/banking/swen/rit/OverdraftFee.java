package domain.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Fee;

public class OverdraftFee extends Fee {
	private Double value = 2.00;
	
	@Override
	public Double applyFee(Double balance) {
		balance -= value;
		return balance;
	}

}
