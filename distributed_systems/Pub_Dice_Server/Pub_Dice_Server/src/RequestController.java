import java.io.IOException;


public class RequestController {

	public void startMatch(Player playerONE, Player playerTWO)
	{
		try {
			PubDiceServices proxy = new ClientProxy(playerONE.getPort(), playerONE.getIPAddress());
			proxy.join(playerONE.getName(), playerTWO.getName(), 1);
			proxy = new ClientProxy(playerTWO.getPort(), playerTWO.getIPAddress());
			proxy.join(playerONE.getName(), playerTWO.getName(), 2);
			startTurn(playerONE, playerTWO);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startTurn(Player currentPlayer, Player endingTurn)
	{
		PubDiceServices proxy = new ClientProxy(currentPlayer.getPort(),currentPlayer.getIPAddress());
		proxy.startTurn(currentPlayer.getPlayerNumber());
		proxy = new ClientProxy(endingTurn.getPort(), endingTurn.getIPAddress());
		proxy.startTurn(currentPlayer.getPlayerNumber());
	}
	
	public void score(Player currentPlayer, Player waitingPlayer)
	{
		PubDiceServices proxy = new ClientProxy(currentPlayer.getPort(),currentPlayer.getIPAddress());
		proxy.updateScore(currentPlayer.getPlayerNumber(), currentPlayer.getScore());
		proxy = new ClientProxy(waitingPlayer.getPort(), waitingPlayer.getIPAddress());
		proxy.updateScore(currentPlayer.getPlayerNumber(), currentPlayer.getScore());
	}
	
	public void dice(Player currentPlayer, Player waitingPlayer, int[] dice)
	{
		dice(currentPlayer,dice);
		dice(waitingPlayer,dice);
	}
	
	public void dice(Player player, int[] dice)
	{
		PubDiceServices proxy = new ClientProxy(player.getPort(),player.getIPAddress());
		proxy.rollDie(dice);
	}
	
	public void quit(Player playerONE, Player playerTWO)
	{
		quit(playerONE);
		quit(playerTWO);
	}
	
	public void quit(Player player)
	{
		PubDiceServices proxy = new ClientProxy(player.getPort(),player.getIPAddress());
		proxy.quit();
	}
	
	public void flipTile(Player currentPlayer, Player waitingPlayer, int i, boolean position)
	{
		flipTile(currentPlayer, i, position);
		flipTile(waitingPlayer, i, position);
	}
	
	public void flipTile(Player player, int i, boolean position)
	{
		PubDiceServices proxy = new ClientProxy(player.getPort(),player.getIPAddress());
		proxy.turnTile(i, position);
	}
	
	public void showWinner(Player currentPlayer, Player waitingPlayer, int winner)
	{
		showWinner(currentPlayer, winner);
		showWinner(waitingPlayer, winner);
	}
	
	public void showWinner(Player player, int winner)
	{
		PubDiceServices proxy = new ClientProxy(player.getPort(),player.getIPAddress());
		proxy.showWinner(winner);
	}
	
}
