

public class WannabeKing extends Piece {

	public WannabeKing(int player) {
		super(player);
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
