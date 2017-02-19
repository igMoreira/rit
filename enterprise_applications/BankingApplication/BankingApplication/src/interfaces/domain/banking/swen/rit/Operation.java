package interfaces.domain.banking.swen.rit;

public interface Operation {

	public void execute();
	
	public void addFee(Fee fee);
	
	public String getOperationType();
	
	public Double getOperationCost();
}
