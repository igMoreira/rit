import java.util.Arrays;

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.IntVbl;

/**
 * Provides a cellular automaton calculation
 * using parallel computing concepts
 * 
 * @author igMoreira
 *
 */
public class ParallelCellularAutomaton extends Task{

	private byte[] updateRule;
	private int smallestPopCounter = Integer.MAX_VALUE;
	private int smallestPopStep = 0;
	private int largestPopCounter = 0;
	private int largestPopStep = 0;
	private int currentPopCounter = 0;
	private IntVbl popCounter = new IntVbl.Sum(0);
	
	/**
	 * Get the current pop counter
	 * @return: current pop counter int
	 */
	public int getCurrentPopCounter() {
		return currentPopCounter;
	}
	
	/**
	 * Get the smallest pop counter
	 * @return: smallest pop counter int
	 */
	public int getSmallestPopCounter() {
		return smallestPopCounter;
	}

	/**
	 * Get the smallest pop step
	 * @return: smallest pop step int
	 */
	public int getSmallestPopStep() {
		return smallestPopStep;
	}

	/**
	 * Get the largest pop counter
	 * @return: largest pop counter int
	 */
	public int getLargestPopCounter() {
		return largestPopCounter;
	}

	/**
	 * Get the largest pop step
	 * @return: largest pop step int
	 */
	public int getLargestPopStep() {
		return largestPopStep;
	}

	/**
	 * Constructor
	 * @param updateRule: in order to use cellular automaton a
	 * rule must be provided.
	 */
	public ParallelCellularAutomaton(byte[] updateRule) {
		this.updateRule = updateRule;
	}
	
	/**
	 * Calculates the value of the next state array using cellular automaton
	 * 
	 * @param currentState: byte array containing the bit values of the current state
	 * @return: A byte array containing the bits of the next state
	 */
	public byte[] calcNextState(final byte[] currentState)
	{
		final byte[] copy = Arrays.copyOf(currentState, currentState.length);	
		
		// This is necessary to include step 0 in the metrics
		popCounter.item = 0;
		for (int i = 0; i < currentState.length; i++) {
			if(currentState[i] == 1)
				popCounter.item++;
		}
		
		updatePopCounters();
		
		parallelFor(0, currentState.length-1).exec(
				new Loop() {
					IntVbl threadCounter;
					
					public void start()
					{
						popCounter.item = 0;
						threadCounter = threadLocal (popCounter);
					}
					
					@Override
					public void run(int i) throws Exception {
						Worker w = new Worker();
						copy[i] = w.calcPositionState(currentState, updateRule, i)[i];
						if(copy[i] == 1)
							++threadCounter.item;
					}
				}
		);
		
		updatePopCounters();
		
		return copy;
	}

	/**
	 * Refresh the values of the popCounters variables
	 */
	private void updatePopCounters()
	{
		currentPopCounter = popCounter.item;
		
		if(popCounter.item > largestPopCounter)
		{
			largestPopCounter = popCounter.item;
			largestPopStep = CA.getSteps();
		}
		else if(popCounter.item < smallestPopCounter)
		{
			smallestPopCounter = popCounter.item;
			smallestPopStep = CA.getSteps();
		}
	}
	
	/**
	 * This is necessary to implement interface Task of pj2
	 */
	@Override
	public void main(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
