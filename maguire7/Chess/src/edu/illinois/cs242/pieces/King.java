package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;



// TODO: Auto-generated Javadoc
/**
 * The Class King.
 */
public class King extends Piece {

	/**
	 * Instantiates a new king.
	 *
	 * @param player the player
	 */
	public King(int color) {
		super(color);
		pieceImageKey = color == Board.WHITE ? "res/white_king.png" : "res/black_king.png";
	}

	
	
	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY, Board board) {
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		return board.isOneSquareAway(curX, curY, destX, destY);
	}

}
