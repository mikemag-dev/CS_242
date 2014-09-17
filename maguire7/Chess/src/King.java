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
	public King(int player) {
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
		return board.isOneSquareAway(curX, curY, destX, destY);
	}

}
