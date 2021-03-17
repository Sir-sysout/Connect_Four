
/** This is the driver class for the whole Connect Four game
 *  By @TommyBi bisi@thayer.org
 *  February.13.2021
 */

import javax.swing.JOptionPane;

public class ConnectFour {

	public static void main(String[] args) throws InterruptedException {

		// Using the default constructor of TBGameGenerator class to initiate a TBGameGenerator object
		GameGenerator game = new GameGenerator();

		do {
			// Start the game and get the winner of this game when finished
			game.askBoardSize();
			game.startGame();
			while (!game.isGameOver()) {
				game.nextRound();
			}
			System.out.println(game.getWinner());
			game.recordStats(game.getWinner());
			
			game.reset();

			// Ask player if want to play again
			String goOn = JOptionPane.showInputDialog("Want To Play Again? (\'Yes\' or \'No\')");

		} while (goOn == "Yes");

	}

}
