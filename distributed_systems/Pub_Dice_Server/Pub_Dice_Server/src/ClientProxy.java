import java.io.IOException;
import java.net.InetAddress;


public class ClientProxy implements PubDiceServices {

	private static CommunicationProtocol network = null;
	private int clientPort;
	private InetAddress clientIPAddress;
	
	public ClientProxy(int hostPort) {
		if(network == null)
			network = new Udp(hostPort);
	}
	
	public ClientProxy(int clientPort, InetAddress clientIPAddress) {
		this.clientIPAddress = clientIPAddress;
		this.clientPort = clientPort;
	}
	
	@Override
	public void join(String playerOneName, String playerTwoName, int playerNumber) throws IOException {
		String message = "joined " + playerOneName + " " + playerTwoName + " " + Integer.toString(playerNumber);
		network.sendRequest(message, clientPort, clientIPAddress);
	}

	@Override
	public void turnTile(int x, boolean up){
		String request = "tile "+ x + ((up) ? " up " : " down ");
		network.sendRequest(request, clientPort, clientIPAddress);
	}

	@Override
	public void startTurn(int playerNumber) {
		String message = "turn " + playerNumber;
		network.sendRequest(message, clientPort, clientIPAddress);
	}

	@Override
	public void rollDie(int[] dice) {
		String message = "dice "+ dice[0]+" "+ dice[1];
		network.sendRequest(message, clientPort, clientIPAddress);
	}

	@Override
	public void quit() {
		String message = "quit";
		network.sendRequest(message, clientPort, clientIPAddress);
	}

	@Override
	public void updateScore(int playerNumber, int score) {
		String message = "score " + playerNumber + " " + score;
		network.sendRequest(message, clientPort, clientIPAddress);
	}

	@Override
	public void showWinner(int winner) {
		String message = "win " + winner;
		network.sendRequest(message, clientPort, clientIPAddress);
	}

}
