import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class MatchManager {

	private static Queue<Player> waitingPlayers = new LinkedList<Player>();
	private static HashMap<String, Match> matches = new HashMap<String, Match>();
	
	public synchronized void joinMatch(String playerName, int port, InetAddress IPAddress)
	{
		Player currentPlayer = new Player(playerName, port, IPAddress);
		if(waitingPlayers.isEmpty())
		{
			currentPlayer.setPlayerNumber(1);
			waitingPlayers.add(currentPlayer);
		}
		else
		{
			currentPlayer.setPlayerNumber(2);
			Player playerONE = waitingPlayers.poll();
			Match match = new Match(playerONE, currentPlayer);
			matches.put(IPAddress+":"+Integer.toString(port), match);
			matches.put(playerONE.getIPAddress()+":"+Integer.toString(playerONE.getPort()), match);
			RequestController controller = new RequestController();
			controller.startMatch(playerONE, currentPlayer);
		}
	}
	
	public synchronized void finishTurn(int port, InetAddress IPAddress)
	{
		String key = IPAddress + ":"+ Integer.toString(port);
		if(matches.containsKey(key))
		{
			Match match = matches.get(key);
			
			// Sends the score of the current player for both clients
			Player currentPlayer = match.getCurrentPlayer();
			Player waitingPlayer = match.getWaitingPlayer();
			RequestController controller = new RequestController();
			controller.score(currentPlayer, waitingPlayer);
			
			// Verifies victory and update the switches the current player
			int winner = match.updateCurrentPlayer();
			if(winner != -1)
				controller.showWinner(currentPlayer, waitingPlayer, winner);
			
			// Allows the next player starts its turn
			currentPlayer = match.getCurrentPlayer();
			waitingPlayer = match.getWaitingPlayer();
			controller.startTurn(currentPlayer, waitingPlayer);
		}
		
	}
	
	public synchronized void quit(int port, InetAddress IPAddress)
	{
		String key = IPAddress + ":"+ Integer.toString(port);
		Player waitingPlayer = null;
		if(matches.containsKey(key))
		{
			Match match = matches.get(key);
			Player currentPlayer = match.getCurrentPlayer();
			waitingPlayer = match.getWaitingPlayer();
			RequestController controller = new RequestController();
			controller.quit(currentPlayer, waitingPlayer);
			matches.remove(currentPlayer.getIPAddress() + ":" + currentPlayer.getPort());
			matches.remove(waitingPlayer.getIPAddress() + ":" + waitingPlayer.getPort());
		}
		else if((waitingPlayer = getWaitingPlayer(port, IPAddress)) != null)
		{
			RequestController controller = new RequestController();
			controller.quit(waitingPlayer);
			waitingPlayers.remove(waitingPlayer);
		}
	}
	
	public synchronized void diceUpdate(int port, InetAddress IPAddress)
	{
		String key = IPAddress + ":"+ Integer.toString(port);
		if(matches.containsKey(key))
		{
			Match match = matches.get(key);
			int[] dice = match.rollDice();
			Player currentPlayer = match.getCurrentPlayer();
			Player waitingPlayer = match.getWaitingPlayer();
			RequestController controller = new RequestController();
			controller.dice(currentPlayer, waitingPlayer, dice);
		}
	}
	
	public synchronized void flipTile(int port, InetAddress IPAddress, int i, String position)
	{
		String key = IPAddress + ":"+ Integer.toString(port);
		if(matches.containsKey(key))
		{
			Match match = matches.get(key);
			boolean result = match.flipTile(i, position);
			Player currentPlayer = match.getCurrentPlayer();
			Player waitingPlayer = match.getWaitingPlayer();
			RequestController controller = new RequestController();
			controller.flipTile(currentPlayer, waitingPlayer, i, result);
		}
	}
	
	private Player getWaitingPlayer(int port, InetAddress IPAddress)
	{
		Player result = null;
		for (Player player : waitingPlayers) {
			String playerHost = player.getIPAddress().getHostName();
			String host = IPAddress.getHostName();
			if(player.getPort() == port && playerHost.equalsIgnoreCase(host))
			{
				result = player;
				break;
			}
		}
		return result;
	}
	
}