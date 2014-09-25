package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.chess.Move;



/**
 * The Class Bishop.
 */
public class Bishop extends ChessPiece {

	/**
	 * Instantiates a new bishop.
	 *
	 * @param player the player
	 */
	public Bishop(int player) {
		super(player);
		pieceImageKey = color == Board.WHITE ? "res/white_bishop.png" : "res/black_bishop.png";
	}
	
	public Bishop(Bishop bishop) {
		super(bishop);
	}

	
	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY,
			Board board) {
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		
		if(Move.isDiagonal(curX, curY, destX, destY)){
			return true;
		}
		
		return false;
	}

}
