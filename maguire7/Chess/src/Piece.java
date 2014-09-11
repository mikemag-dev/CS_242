
/**
 * @author MaguireM
 * 
 */
abstract class Piece {

	
	public String type;
	public boolean hasMoved;
	public int player;
	
	
	/**
	 * @param player -1 if player is black, 1 is player is white
	 */
	public Piece(int player) {
		super();
		this.player = player;
		this.hasMoved = false;
	}


	public abstract boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y, Board board);
	
	protected boolean passesUniversalConstraints(int cur_x, int cur_y, int dest_x, int dest_y, Board board){
		if(!Move.isWithinBounds(dest_x, dest_y, board)){
			return false;
		}
		if(!Move.isZeroVector(cur_x, cur_y, dest_x, dest_y)){
			return false;
		}
		if(Move.isObstructed(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		return true;
	}

}
