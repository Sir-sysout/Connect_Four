/** This class generates a Connect Four game
 * By @TommyBi bisi@thayer.org
 * February.13.2021
 */

import javax.swing.JOptionPane;
import java.util.*;

public class TBGameGenerator {
	
	// Private Instance Variables
		
	private TBBoard board;
	private TBPlayer playerY;
	private TBPlayer playerR;
	private TBPlayer currentPlayer;
	private int currentRound;
	
	private ArrayList<String> winners;
	
	
	// Default Constructor
	public TBGameGenerator() {

		// Using the default constructors of TBBoard and TBPlayer class
		// to initiate a new board object, two new player objects, and a winner tracking ArrayList
		board = new TBBoard();
		playerY = new TBPlayer();
		playerR = new TBPlayer();
		currentPlayer = playerY;
		currentRound = 0;
		winners = new ArrayList<String>();
		
	}
	
	// Overloaded Constructor
	public TBGameGenerator(int cusRows, int cusCols) {

		// Using the default constructors of TBBoard and TBPlayer class
		// to initiate a new board object, two new player objects, and a winner tracking ArrayList
		// Board size could be customized
		board = new TBBoard(cusRows, cusCols);
		playerY = new TBPlayer();
		playerR = new TBPlayer();
		currentPlayer = playerY;
		currentRound = 0;
		winners = new ArrayList<String>();
		
	}

	
	// Methods

	// Start the game by asking the game will be played by human or bot
	public void startGame() {

		// Set PlayerY's ID and symbol
		String askY = JOptionPane.showInputDialog("Who's playing playerY? (\'Human\' or \'Bot\')");
		if (askY.contains("Human")) {
			playerY.setIdentity("Human");
			playerY.setSymbol("Y");
		} else {
			playerY.setIdentity("Bot");
			playerY.setSymbol("Y");
		}

		// Set PlayerR's ID and symbol
		String askR = JOptionPane.showInputDialog("Who's playing playerR? (\'Human\' or \'Bot\')");
		if (askR.contains("Human")) {
			playerR.setIdentity("Human");
			playerR.setSymbol("R");
		} else {
			playerR.setIdentity("Bot");
			playerR.setSymbol("R");
		}

	}

	// Based on the currentPlayer, this will perform their move. 
	public void nextRound() throws InterruptedException {		
		
		String currentSymbol = currentPlayer.getSymbol();
		int playerDropAt = currentPlayer.giveMove(currentPlayer.getIdentity());
		
		if(isValidMove(playerDropAt)){
			board.addChecker(playerDropAt, currentSymbol);
			currentRound += 1;			
		}
		else if(currentPlayer.getIdentity() == "Human"){
			
			boolean valid = false;
			do{
				
				String ask = JOptionPane.showInputDialog("Invalid column. PLS re-enter number");
				playerDropAt = Integer.parseInt(ask);
				if(isValidMove(playerDropAt)){
					valid = true;
				}
				
			} while(!valid);
			
			board.addChecker(playerDropAt, currentSymbol);
			currentRound += 1;
		}
		else{ // Protector: When user change board size, bot will generate invalid column numbers
			boolean valid = false;
			do{
				playerDropAt = currentPlayer.giveMove("Bot");
				if(isValidMove(playerDropAt)){
					valid = true;
				}
			} while(!valid);
			
			board.addChecker(playerDropAt, currentSymbol);
			currentRound += 1;
		}

		// 1.1s pause + Print current round info
		System.out.print("It's Player " + currentSymbol + "\'s turn. ");
		System.out.println("Turn #" + currentRound);
		System.out.println("\n" + "\n");
		Thread.sleep(1111);
		
		// Update the current player to the next one		
		if(currentPlayer == playerY){
			currentPlayer = playerR;
		} else{
			currentPlayer = playerY;
		}
		
	}

	// Check if moves made by the players is valid (Out of bounds and full column)
	private boolean isValidMove(int playerCol) {

		if (playerCol < 0 || playerCol >= board.getBoardColNum()) {
			return false;
		}
		
		if(board.columnFull(playerCol)){
			System.out.println("Invalid Column. It's full.");
			return false;
		}

		return true;
	
	}

	// Check if game is over
	public boolean isGameOver() {
		
		if (board.boardFull() || board.fourInRow()) {
			return true;
		}
		
		return false;

	}

	// Get the winner / tie message
	public String getWinner() {
		
		if(board.boardFull()){
			return "Board Full, TIE game!";
		}

		else if(currentPlayer.getSymbol() == "Y"){
			return "The Winner is: Player R!";
		}
		return "The Winner is: Player Y!";
		
	}
	
	// Reset the game - added since now no new TBGameGenerator created every new game
	public void reset(){
		
		board.resetBoard();
		currentRound = 0;
		currentPlayer = playerY;
		
	}
	
	// Ask player about their board size
	public void askBoardSize(){
		
		String askBoardSize = JOptionPane.showInputDialog("Choose \'Regular\' for 6X7 board, or \'Customize\'");
		
		// Default 6X7
		if(askBoardSize.contains("Regular")){
			board = new TBBoard();
		}
		
		// Customize
		else if(askBoardSize.contains("Customize")){
			
			// Initialization 
			boolean validInt = false;
			int rows, cols;
			String size = "";
			String size2 = "";
			
			// Ask customized row number
			size = JOptionPane.showInputDialog("Ok. Pls choose INTEGER board 3<rows<=10.");

			do{
				
			rows = Integer.parseInt(size);
			if(rows > 3 && rows <= 10){
				validInt = true;
			} else size = JOptionPane.showInputDialog("Invalid. Re-enter INTEGER 3<rows<=10.");
			
			} while(!validInt);
			
			// Ask customized column number
			validInt = false;
			size2 = JOptionPane.showInputDialog("Sure. Pls choose INTEGER board 3<cols<=10.");
			
			do{
				
			cols = Integer.parseInt(size2);
			if(cols > 3 && cols <= 10){
				validInt = true;
			} else size2 = JOptionPane.showInputDialog("Invalid. Re-enter INTEGER 3<cols<=10.");

			} while(!validInt);
			
			// Using the overloaded constructor in TBBoard class to initialize a (customized-sized) TBBoard object
			board = new TBBoard(rows, cols);
		}
		
	}
	
	
	// Game statistics
	public void recordStats(String whoWon){
		
		int YWinTime = 0;
		int RWinTime = 0;
		
		winners.add(whoWon);
		
		for(String player : winners){
			if(player == "The Winner is: Player R!"){
				RWinTime++;
			}
			else if(player == "The Winner is: Player Y!"){
				YWinTime++;
			}
		}
		
		System.out.println("PlayerY has won " + YWinTime + " time(s).");
		System.out.println("PlayerR has won " + RWinTime + " time(s).");

	}
	
}
