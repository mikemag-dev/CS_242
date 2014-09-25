package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.chess.Move;

// TODO: Auto-generated Javadoc
/**
 * The Class Queen.
 */
public class Queen extends ChessPiece {
	
	

	/**
	 * Instantiates a new queen.
	 *
	 * @param player the player
	 */
	public Queen(int color) {
		super(color);
		pieceImageKey = color == Board.WHITE ? "res/white_queen.png" : "res/black_queen.png";
	}
	
	public Queen(Queen queen) {
		super(queen);
	}

	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY, Board board) {
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		
		if(Move.isDiagonal(curX, curY, destX, destY)||
		   Move.isStraight(curX, curY, destX, destY)){
			return true;
		}
		
		return false;
	}

	

}
