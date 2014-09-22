package edu.illinois.cs242.pieces;

import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.chess.Move;

public class Rook extends Piece {

	/**
	 * Instantiates a new rook.
	 *
	 * @param player the player
	 */
	public Rook(int player) {
		super(player);
		pieceImageKey = color == Board.WHITE ? "res/white_rook.png" : "res/black_rook.png";
	}

	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY, Board board) {
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		
		if(Move.isStraight(curX, curY, destX, destY)){
			return true;
		}
		
		return false;
	}

}
