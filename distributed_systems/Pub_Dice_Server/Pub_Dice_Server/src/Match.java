import java.util.Random;


public class Match {
	private Player playerONE;
	private Player playerTWO;
	private Player currentPlayer;
	private Player waitingPlayer;
	
	public Match(Player playerONE, Player playerTWO) {
		this.playerONE = playerONE;
		this.playerTWO = playerTWO;
		currentPlayer = this.playerONE;
		waitingPlayer = this.playerTWO;
	}

	public Player getPlayerONE() {
		return playerONE;
	}

	public Player getPlayerTWO() {
		return playerTWO;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player getWaitingPlayer() {
		return waitingPlayer;
	}

	public int updateCurrentPlayer() {
		int status = -1;
		if(currentPlayer == playerONE)
		{
			currentPlayer = playerTWO;
			waitingPlayer = playerONE;
		}
		else
		{
			Player winner = getWinner();
			status = (winner == null) ? 0 : winner.getPlayerNumber();
			currentPlayer = playerONE;
			waitingPlayer = playerTWO;
		}
		return status;
	}
	
	public int[] rollDice()
	{
		int[] dice = new int[2];
		Random rand = new Random();
		dice[0] = rand.nextInt((6-1)+1)+1;
		System.out.println(dice[0]);
		dice[1] = rand.nextInt((6-1)+1)+1;
		System.out.println(dice[1]);
		return dice;
	}
	
	public boolean flipTile(int i, String position)
	{
		boolean result = currentPlayer.flipTile(i, position);
		return result;
	}
	
	private Player getWinner()
	{
		Player winner = null;
		if(waitingPlayer.getScore() < currentPlayer.getScore())
			winner = waitingPlayer;
		else if(currentPlayer.getScore() < waitingPlayer.getScore())
			winner = currentPlayer;
		currentPlayer.resetTiles();
		waitingPlayer.resetTiles();
		return winner;
	}
	
}
