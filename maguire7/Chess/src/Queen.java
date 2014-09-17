// TODO: Auto-generated Javadoc
/**
 * The Class Queen.
 */
public class Queen extends Piece {
	
	

	/**
	 * Instantiates a new queen.
	 *
	 * @param player the player
	 */
	public Queen(int player) {
		super(player);
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
