package domain.banking.swen.rit;

import interfaces.domain.banking.swen.rit.Fee;

public class WireTransferFee extends Fee {

	private Double value = 5.00;
	
	@Override
	public Double applyFee(Double balance) {
		balance -= value;
		return balance;
	}

}
