package edu.illinois.cs242.pieces;

import edu.illinois.cs242.chess.Board;

public class WannabeKing extends Piece {

	public WannabeKing(int color) {
		super(color);
	}

	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY,
			Board board) {
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		return board.isOneSquareAway(curX, curY, destX, destY);
	}

}
