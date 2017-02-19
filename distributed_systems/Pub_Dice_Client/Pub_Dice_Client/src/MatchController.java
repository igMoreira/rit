import java.io.IOException;
import java.net.UnknownHostException;


public class MatchController {
	
	private static PubDiceServices model;
	
	public MatchController() {
		try {
			model = new ServerProxy();
		} catch (UnknownHostException e) {
			System.out.printf("CONNECTION ERROR: Error trying connect to host\n");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.printf("CONNECTION ERROR: Error trying to get server I/O\n");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
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
	
	public void rollDie()
	{
		model.rollDie();
	}
	
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
	
	public void quit()
	{
		model.quit();
	}
	
	public void endTurn()
	{
		model.endTurn();
	}
	
/*	private void showWinner(String[] response, PubDiceUI view)
	{
		String endMatchPrase = "";
		String[] scoreInfo = response[0].split(" ");
		String[] winnerInfo = response[1].split(" ");
		int scoreID = Integer.parseInt(scoreInfo[1]);
		if(realPlayer.getNumber() == scoreID)
			realPlayer.setScore(Integer.parseInt(scoreInfo[2]));
		else
			virtualPlayer.setScore(Integer.parseInt(scoreInfo[2]));
		int winnerID = Integer.parseInt(winnerInfo[1]);
		if(winnerID == 0)
			endMatchPrase = "Tie!";
		else if(realPlayer.getNumber() == winnerID)
			endMatchPrase = realPlayer.getName() + " wins!";
		else
			endMatchPrase = virtualPlayer.getName() + " wins!";
		view.setMessage( 
				(realPlayer.getNumber() == 1) 
				? realPlayer.getName() + " " + realPlayer.getScore() + " -- " + virtualPlayer.getName() + " " + virtualPlayer.getScore() + " -- " + endMatchPrase 
				: virtualPlayer.getName() + " " + virtualPlayer.getScore() + " -- " + realPlayer.getName() + " " + realPlayer.getScore() + " -- " + endMatchPrase);
	}*/
}
