/**
 * Response for interpret a given response
 * caught by the receiver thread. After
 * interpreted the response a action is
 * executed as a result of the response.
 * 
 * This class is an observer so whatever happens
 * to the subject (ReceiverThread) it will know
 * and will get the most recent data, Observer
 * pattern in push mode.
 *  
 * @author igMoreira
 *
 */
public class ResponseController implements Observer{
	private static Player realPlayer;
	private static Player virtualPlayer;
	private static PubDiceUI view;
	
	/**
	 * Observer pattern being applied,
	 * basically allows this class
	 * update as soon as the subject
	 * changes.
	 */
	@Override
	public void update(String response) {
		processResponse(response);
	}
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param playerName: Name of the real player
	 * @param gui: View to be update as a result of a response
	 */
	public ResponseController(String playerName, PubDiceUI gui) {
		realPlayer = new Player();
		virtualPlayer = new Player();
		view = gui;
		realPlayer.setName(playerName);
	}
	
	/**
	 * Process a response given
	 * by the server, basically interprets
	 * the response and delegates a task
	 * 
	 * @param response: String response provided by the server
	 */
	private void processResponse(String response)
	{
			String[] interpretedResponse = response.split(" ");
			String command = interpretedResponse[0];
			switch(command)
			{
				case "quit":
					ServerProxy s = new ServerProxy();
					s.closeConnection();
					System.exit(0);
					break;
				case "score":
					scoreUpdate(interpretedResponse);
					break;
				case "tile":
					tileUpdate(interpretedResponse);
					break;
				case "dice":
					diceUpdate(interpretedResponse);
					break;
				case "joined":
					playersUpdate(interpretedResponse);
					break;
				case "turn":
					turnUpdate(interpretedResponse);
					break;
				case "win":
					winUpdate(interpretedResponse);
					break;
				default:
					System.out.println("Invalid server command "+response);
			}
	}
	
	/**
	 * Updates the tiles in the view
	 * giving a server response tile <X> <Y>
	 * X = tile number		Y=up/down
	 * @param tileInfo: Interpreted response
	 */
	private void tileUpdate(String[] tileInfo)
	{
		if(realPlayer.isTurn())
		{
			int tile = Integer.parseInt(tileInfo[1]);
			boolean up = (tileInfo[2].equalsIgnoreCase("up"));
			view.setTile(tile, up);
		}
	}
	
	/**
	 * Updates the scores in the view
	 * giving a server response score <X> <Y>
	 * X= player number		Y=score value
	 * @param scoreInfo: interpreted response
	 */
	private void scoreUpdate(String[] scoreInfo)
	{
		int playerNumber = Integer.parseInt(scoreInfo[1]);
		int score = Integer.parseInt(scoreInfo[2]);
		if(realPlayer.getNumber() == playerNumber)
		{
			realPlayer.setScore(score);
			if(playerNumber == 1)
				view.setMessage(""+realPlayer.getName()+" "+ realPlayer.getScore() +" -- "+virtualPlayer.getName());
			else
				view.setMessage(""+virtualPlayer.getName()+" "+ virtualPlayer.getScore() +" -- "+realPlayer.getName()+" "+realPlayer.getScore());
		}
		else
		{
			virtualPlayer.setScore(score);
			if(playerNumber == 1)
				view.setMessage(""+virtualPlayer.getName()+" "+ virtualPlayer.getScore() +" -- "+realPlayer.getName());
			else
				view.setMessage(""+realPlayer.getName()+" "+ realPlayer.getScore() +" -- "+virtualPlayer.getName()+" "+virtualPlayer.getScore());
		}
	}
	
	/**
	 * Updates the player information in the view.
	 * Sets the name of the players in the message
	 * box.
	 * 
	 * @param playersInfo: Interpreted response
	 */
	private void playersUpdate(String[] playersInfo)
	{
		if(!realPlayer.getName().equalsIgnoreCase(playersInfo[1]))
			virtualPlayer.setName(playersInfo[1]);
		else
			virtualPlayer.setName(playersInfo[2]);
		realPlayer.setNumber(Integer.parseInt(playersInfo[3]));
		if( (realPlayer.getNumber() == 1) )
		{
			virtualPlayer.setNumber( 2 );
			view.setMessage(""+realPlayer.getName()+" -- "+virtualPlayer.getName());
		}
		else
		{
			virtualPlayer.setNumber( 1 );
			view.setMessage(""+virtualPlayer.getName()+" -- "+realPlayer.getName());
		}
	}
	
	/**
	 * Updates the turn, enables/disables
	 * the button according to a player
	 * turn.
	 * 
	 * @param turnInfo: Interpreted response
	 */
	private void turnUpdate(String[] turnInfo)
	{
		int turn = Integer.parseInt(turnInfo[1]);
		if(turn == realPlayer.getNumber())
		{
			view.enableButtons(true);
			realPlayer.setTurn(!realPlayer.isTurn());
		}
		else
			view.enableButtons(false);
	}
	
	/**
	 * Updates the view with the winner
	 * message.
	 * X wins! or Y wins or Tie!
	 * X = player1 name		Y = player 2 name
	 * @param winInfo: Interpreted response
	 */
	private void winUpdate(String[] winInfo)
	{
		String winMessage = "";
		int winnerNumber = Integer.parseInt(winInfo[1]);
		if(winnerNumber == 0)
			winMessage = " -- Tie!";
		else if(winnerNumber == realPlayer.getNumber())
			winMessage = " -- "+ realPlayer.getName()+" wins!";
		else
			winMessage = " -- "+ virtualPlayer.getName()+" wins!";
		view.setMessage(view.getMessage()+winMessage);
	}
	
	/**
	 * Updates the dice in the view according
	 * to a server response
	 * 
	 * @param diceInfo; Interpreted response
	 */
	private void diceUpdate(String[] diceInfo)
	{
		int die1 = Integer.parseInt(diceInfo[1]);
		int die2 = Integer.parseInt(diceInfo[2]);
		view.setDie(0, die1);
		view.setDie(1, die2);
	}

}
