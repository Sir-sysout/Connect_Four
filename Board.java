
/** This class defines the board of the Connect Four game
 * By @TommyBi bisi@thayer.org
 * February.13.2021
 */

// import javax.swing.JOptionPane;

public class Board {

	// Private Instance Variables
	private String[][] board;
	

	// Default constructor
	public Board() {
		
		board = new String[6][7];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = "-";
			}
		}
		
	}

	// Overloaded constructor
	public Board(int row, int col) {
		
		board = new String[row][col];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = "-";
			}
		}
		
	}
	
	
	// Methods

	// Get the number of rows of the board
	public int getBoardRowNum() {
		return board.length;
	}

	// Get the number of columns of the board
	public int getBoardColNum() {
		return board[0].length;
	}
	
	// Reset the board
	public void resetBoard() {
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = "-";
			}
		}
		
	}

	// Add a checker in a column and display the board
	public void addChecker(int col, String symbol) {

		boolean filled = false;

		for (int i = board.length - 1; i >= 0; i--) {
			if (board[i][col] == "-" && !filled) {
				board[i][col] = symbol;
				filled = true;
			}
		}

		display();

	}

	// Check if there is ANY "four in a row" situation
	public boolean fourInRow() {

		// Initialing variable, the currentSymbol variable makes sure
		// we're always checking the "four in a row" situation for the same letter
		String currentSymbol = "";

		// Check horizontal
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length - 3; c++) {
				if (board[r][c] != "-") {
					currentSymbol = board[r][c];
					if (board[r][c] == currentSymbol && board[r][c + 1] == currentSymbol
							&& board[r][c + 2] == currentSymbol && board[r][c + 3] == currentSymbol) {
						return true;
					}
				}
			}
		}

		// Check vertical
		currentSymbol = "";
		for (int c = 0; c < board[0].length; c++) {
			for (int r = 0; r < board.length - 3; r++) {
				if (board[r][c] != "-") {
					currentSymbol = board[r][c];
					if (board[r][c] == currentSymbol && board[r + 1][c] == currentSymbol
							&& board[r + 2][c] == currentSymbol && board[r + 3][c] == currentSymbol) {
						return true;
					}
				}
			}
		}

		// Check "\" Diagonal
		currentSymbol = "";
		for (int r = 0; r < board.length - 3; r++) {
			for (int c = 0; c < board[0].length - 3; c++) {
				if (board[r][c] != "-") {
					currentSymbol = board[r][c];
					if (board[r][c] == currentSymbol && board[r + 1][c + 1] == currentSymbol
							&& board[r + 2][c + 2] == currentSymbol && board[r + 3][c + 3] == currentSymbol) {
						return true;
					}
				}
			}
		}

		// Check "/" Diagonal
		currentSymbol = "";
		for (int r = 3; r < board.length; r++) {
			for (int c = 0; c < board[0].length - 3; c++) {
				if (board[r][c] != "-") {
					currentSymbol = board[r][c];
					if (board[r][c] == currentSymbol && board[r - 1][c + 1] == currentSymbol
							&& board[r - 2][c + 2] == currentSymbol && board[r - 3][c + 3] == currentSymbol) {
						return true;
					}
				}
			}
		}

		return false;

	}

	// Check if the given column is full
	public boolean columnFull(int col) {
		
		int filledCount = 0;
		
		for (int r = 0; r < board.length; r++) {
			if (board[r][col] != "-") {
				filledCount++;
			}
		}
		
		if(filledCount == board.length){
			return true;
		}
		return false;

	}

	// Check if the whole board is full
	public boolean boardFull() {
		
		for (int c = 0; c < board[0].length; c++) {
			if (!columnFull(c)) {
				return false;
			}
		}	
		return true;
		
	}

	// Display the current board with the row and column numbers
	public void display() {

		for (int j = 0; j < board[0].length; j++) {
			System.out.print("   [" + (j) + "] ");
		}
		System.out.println();

		for (int r = 0; r < board.length; r++) {
			System.out.print("");
			System.out.print("[" + (r) + "] ");
			for (int c = 0; c < board[0].length; c++) {
				System.out.print(board[r][c] + "      ");
			}
			System.out.println();
		}
		
	}
	
}
