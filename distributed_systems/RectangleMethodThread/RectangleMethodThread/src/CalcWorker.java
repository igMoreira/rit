/***
 * 
 * @author igMoreira
 *	Represents a thread in the system, each thread will work in the calc of the
 *	area of the curved figured. The number of workers is specified by the user.
 */
public class CalcWorker implements Runnable {

	private Double[] function;	// arguments of the function
	private Double startPoint;	// start point coordinate in the x-axis
	private Double endPoint;	// end point coordinate in the x-axis
	private int numIntervals;	// number of squares to be used in the rectangle method
	private SharedBuffer totalArea;	// shared buffer to be used between threads
	
	/***
	 * CalcWorker constructor
	 * 
	 * @param buffer: shared buffer between threads
	 * @param function: function arguments provided by the user
	 * @param startPoint: starting interval in the x-axis
	 * @param endPoint: ending interval in y-axis
	 * @param numIntervals: number of rectangles per thread
	 */
	public CalcWorker(SharedBuffer buffer, Double[] function, Double startPoint, Double endPoint, int numIntervals) {
		this.function = function;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.numIntervals = numIntervals;
		this.totalArea = buffer;
	}
	
	/***
	 * Will execute the thread.
	 */
	@Override
	public void run() {
		Double squareLength = (endPoint-startPoint)/numIntervals;
		Double squareHeigth = 0D;
		Double area = 0D;
		for (int i = 0; i < numIntervals; i++) {
			squareHeigth = calculateFunction(function, startPoint);
			startPoint = startPoint + squareLength;
			area += squareLength * squareHeigth;
		}
		this.totalArea.addArea(area); 
	}
	
	/***
	 * Calculates the function for a given x-axis coordinate.
	 * 
	 * @param function: array containing the arguments of the function
	 * @param x: x-axis coordinate to calculate the function
	 * @return: The value of f(x)
	 */
	private double calculateFunction(Double[] function, Double x)
	{
		double result = 0D;
		Integer[] expoents = new Integer[function.length];
		for (int i = 0; i < function.length; i++) {
			expoents[i] = Math.abs(i-(function.length-1));
		}
		for (int i = 0; i < function.length; i++) {
			result += function[i] * (Math.pow(x, expoents[i]));
		}
		return result;
	}

}
