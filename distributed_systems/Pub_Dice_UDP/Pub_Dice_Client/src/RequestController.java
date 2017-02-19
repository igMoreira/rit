import java.io.IOException;

/**
 * This class is the MVC controller
 * part responsible for delegating
 * task for a specific part
 * of the model. This is the view
 * input bridge, whatever the view
 * has as input comes here and is
 * delegated for the model. 
 * 
 *
 */
public class RequestController {
	
	private PubDiceServices model;
	
	/**
	 * Constructor
	 */
	public RequestController() {
		this.model = new ServerProxy();
	}
	
	/**
	 * Provides a starting match task
	 * @param playerName
	 */
	public void startMatch(String playerName)
	{
		try {
			model.join(playerName);
		} catch (IOException e) {
			System.out.printf("REQUEST ERROR: Error trying to join a game\n");
			e.printStackTrace();
			System.exit(2);
		}
	}
	
	/**
	 * Provides a roll die task
	 */
	public void rollDie()
	{
		model.rollDie();
	}
	
	/**
	 * Provides a flip (turn) tile task
	 * 
	 * @param tile: tile to be flipped, integer
	 * @param up: tile position (true if up / false if down)
	 */
	public void flipTile(int tile, boolean up)
	{
		try {
			model.turnTile(tile, up);
		} catch (IOException e) {
			System.out.printf("REQUEST ERROR: Error trying to move a tile\n");
			e.printStackTrace();
			System.exit(3);
		}
	}
	
	/**
	 * Provides a quit task
	 */
	public void quit()
	{
		model.quit();
	}
	
	/**
	 * Provides the end turn task
	 */
	public void endTurn()
	{
		model.endTurn();
	}
}
