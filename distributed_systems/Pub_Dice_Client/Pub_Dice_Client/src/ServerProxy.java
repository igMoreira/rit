import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class represents the MVC model,
 * this is responsible for formating and packing a
 * request to sent to the server.
 * THIS CLASS DOES NOT SEND AT ALL.
 * It delegates the send request
 * for the SenderThread.
 * 
 * @author igMoreira
 *
 */
public class ServerProxy implements PubDiceServices  {

	private static ReceiverThread receiver;
	private static SenderThread sender;
	private static Socket conn;
	
	/**
	 * CONSTRUCTOR
	 */
	public ServerProxy(){
	}
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param host: host to be connected
	 * @param port: port to be connected
	 * @throws UnknownHostException: If not possible to connect
	 * @throws IOException: If not possible to get the streams
	 */
	public ServerProxy(String host, int port) throws UnknownHostException, IOException {
		if(conn == null)
		{
			conn = new Socket(host, port);
			receiver = new ReceiverThread(conn.getInputStream());
			sender = new SenderThread(conn.getOutputStream());
			receiver.setPriority(Thread.MAX_PRIORITY);
			receiver.start();
			sender.start();
		}
	}

	/**
	 * Gets the receiver thread
	 * @return: Receiver thread, thread responsible for listen to server responses
	 */
	public ReceiverThread getReceiver() {
		return receiver;
	}

	/**
	 * Packs a join match request to be sent for the
	 * server.
	 */
	@Override
	public void join(String playerName) throws IOException {
		String request = "join "+ playerName;
		sender.sendRequest(request);
	}

	/**
	 * Packs a turn tile command to be sent for the server.
	 */
	@Override
	public void turnTile(int x, boolean up) throws IOException {
		String request = "tile "+ x + ((up) ? " up " : " down ");
		sender.sendRequest(request);
	}

	/**
	 * Packs a turn roll dice command to be sent for the server.
	 */
	@Override
	public void rollDie() {
		String request = "roll";
		sender.sendRequest(request);
	}

	/**
	 * Packs a turn quit command to be sent for the server.
	 */
	@Override
	public void quit() {
		String request = "quit";
		sender.sendRequest(request);
	}

	/**
	 * Packs an end turn command to be sent for the server.
	 */
	@Override
	public void endTurn() {
		String request = "done";
		sender.sendRequest(request);
	}
	
	/**
	 * Closes all resources and connection
	 * with the server.
	 */
	public void closeConnection()
	{
		try {
			receiver.getIn().close();
			sender.getOut().close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
