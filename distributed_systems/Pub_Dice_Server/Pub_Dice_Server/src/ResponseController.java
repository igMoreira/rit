import java.net.InetAddress;


public class ResponseController extends Thread{
	private String message;
	private int port;
	private InetAddress IPAddress;
	
	public ResponseController(String message, int port, InetAddress IPAddress) {
		this.message = message;
		this.port = port;
		this.IPAddress = IPAddress;
	}
	
	private void processRequest()
	{
		String[] interpretedMessage = message.split(" ");
		String command = interpretedMessage[0];
		switch(command)
		{
			case "quit":
				quit();
				break;
			case "tile":
				tileUpdate(interpretedMessage);
				break;
			case "roll":
				diceUpdate(interpretedMessage);
				break;
			case "join":
				playersUpdate(interpretedMessage);
				break;
			case "done":
				turnUpdate(interpretedMessage);
				break;
			default:
				System.out.println("Invalid command "+message);
		}
	}
	
	private void turnUpdate(String[] interpretedMessage) {
		MatchManager manager = new MatchManager();
		manager.finishTurn(port, IPAddress);
	}

	private void playersUpdate(String[] interpretedMessage) {
		MatchManager manager = new MatchManager();
		manager.joinMatch(interpretedMessage[1], port, IPAddress);
	}

	private void diceUpdate(String[] interpretedMessage) {
		MatchManager manager = new MatchManager();
		manager.diceUpdate(port, IPAddress);
	}

	private void tileUpdate(String[] interpretedMessage) {
		MatchManager manager = new MatchManager();
		manager.flipTile(port, IPAddress, Integer.parseInt(interpretedMessage[1]), interpretedMessage[2]);
	}
	
	private void quit()
	{
		MatchManager manager = new MatchManager();
		manager.quit(port, IPAddress);
	}

	@Override
	public void run() {
		processRequest();
	}
	
}
