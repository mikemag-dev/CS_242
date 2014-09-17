
// TODO: Auto-generated Javadoc
/**
 * The Class Charger. A charger piece can travel anywhere within the 3x3 grid in front of it,
 * including attacking pieces of the same color. Obstructions to not matter and are hopped over
 * if need be.
 * 
 */
public class Charger extends Piece {

	public Charger(int player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY,
			Board board) {
		if(!Move.isWithinBounds(destX, destY, board)){
			return false;
		}
		if(!Move.isZeroVector(curX, curY, destX, destY)){
			return false;
		}
		
		if( destX - curX < -1 || 1 < destX - curX){
			return false;
		}	
		
		int color = ((Piece) board.getPieceAt(curX, curY)).player;
		int minDestYDist = Math.min(1*color, 3*color);
		int maxDestYDist = Math.max(1*color, 3*color);
		int destYDist = destY - curY;
		if(minDestYDist <= destYDist && destYDist <= maxDestYDist){
			return true;
		}
		return false;
	}

}
