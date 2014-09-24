package edu.illinois.cs242.chess;



import javax.swing.JFrame;

/**
 * The Class Chess.
 */
public class ChessActivity extends JFrame{
	
	/** The board. */
	private Board board;
	
	/** The curPlayer. */
	private int curPlayer;
	
	/**
	 * Instantiates a new chess.
	 */
	public ChessActivity(){
		boolean doNotPopulate = false;
		board = new Board(8, 8, doNotPopulate);
		curPlayer = Board.WHITE;
		
	}
	
	public static void main(String[] args){
		ChessActivity chess = new ChessActivity();
		ChessBoardPanel chessboardUI = new ChessBoardPanel(chess.board);
		chessboardUI.setVisible(true);
		
		while(true){
			
		}
		
	}
}
