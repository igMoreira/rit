import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Implements a thread responsible for sending
 * content to the server. This thread will only
 * send will never receive anything.
 * @author igMoreira
 *
 */
public class SenderThread extends Thread{
	
	private PrintWriter out;
	private String request = null;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param out
	 */
	public SenderThread(OutputStream out) {
		this.out = new PrintWriter(out);
	}
	
	/**
	 * Gets the OutputStream of the server connection
	 * @return: PrintWriter file of the connection
	 */
	public PrintWriter getOut() {
		return out;
	}

	/**
	 * Represents a task to be executed by the thread.
	 * This part of the code will actually send requests
	 * for the server.
	 */
	@Override
	public void run() {
		while(true)
		{
			try {
				while(request == null)
				{
					Thread.sleep(20);
				}
				out.println(request);
				out.flush();
				request = null;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method allows external objects
	 * insert a request to be sent by the server.
	 * @param request: Request to be sent by the server
	 */
	public void sendRequest(String request)
	{
		this.request = request;
	}

}
