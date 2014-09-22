package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.chess.Move;




// TODO: Auto-generated Javadoc
/**
 * The Class Knight.
 */
public class Knight extends Piece {

	/**
	 * Instantiates a new knight.
	 *
	 * @param color the color
	 */
	public Knight(int color) {
		super(color);
		pieceImageKey = color == Board.WHITE ? "res/white_knight.png" : "res/black_knight.png";
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
		
		if(Move.isLShaped(curX, curY, destX, destY)){
			return true;
		}
		
		return false;
	}

}
