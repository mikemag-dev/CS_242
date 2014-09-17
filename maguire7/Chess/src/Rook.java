
// TODO: Auto-generated Javadoc
/**
 * The Class Rook.
 */
public class Rook extends Piece {

	/**
	 * Instantiates a new rook.
	 *
	 * @param player the player
	 */
	public Rook(int player) {
		super(player);
		this.hasMoved = false;
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
