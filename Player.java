
/** This class defines a player of a Connect Four game
 * By @TommyBi bisi@thayer.org
 * February.13.2021
 */

import javax.swing.JOptionPane;

public class Player {

	// Private Instance Variables
	private String checkerSymbol;
	private String identity;

	
	// Default Constructor
	public TBPlayer() {
		checkerSymbol = "";
		identity = "";
	}

	
	// Methods

	// Get either "R" or "Y"
	public String getSymbol() {
		return checkerSymbol;
	}

	// Get either "Human" or "Bot"
	public String getIdentity() {
		return identity;
	}

	// Set "R" or "Y" symbol for a player
	public void setSymbol(String sym) {
		checkerSymbol = sym;
	}

	// Set "Human" or "Bot" identity for a player
	public void setIdentity(String id) {
		identity = id;
	}

	// Ask for a move and give where a player wants to go
	public int giveMove(String player) {

		if (player.equals("Human")) {
			return humanMove();
		}

		return botMove();

	}

	// A human move
	private int humanMove() {

		String ask = JOptionPane.showInputDialog("Which column would you go?");
		int colDrop = Integer.parseInt(ask);
		return colDrop;

	}

	// A bot move
	private int botMove() {

		// Randomly generate integers from 0-10, inclusive
		int colDrop = (int) (Math.random() * 11);
		return colDrop;

	}

}
