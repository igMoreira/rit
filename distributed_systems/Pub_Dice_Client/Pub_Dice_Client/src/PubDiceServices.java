import java.io.IOException;

/**
 * Defines an interface for the services provided
 * by the Pub Dice application
 * @author igMoreira
 *
 */
public interface PubDiceServices {
	
	/**
	 * Allows the user join a game with
	 * other player
	 * 
	 * @param playerName: The real player name
	 * @throws IOException: If not possible to stablish the match this exception is thrown
	 */
	public void join(String playerName) throws IOException;
	
	/**
	 * Allows the user turn a tile up
	 * and down
	 * 
	 * @param x: Number of the tile to flip
	 * @param up: True if up False if down
	 * @throws IOException: if not possible to perform operation this exception is thrown
	 */
	public void turnTile(int x, boolean up) throws IOException;
	
	/**
	 * Allows the user end its turn
	 */
	public void endTurn();
	
	/**
	 * Allows the user roll the dice
	 */
	public void rollDie();
	
	/**
	 * Allows the user quit a match
	 */
	public void quit();
}
