
public class Bishop extends Piece {

	public Bishop(int player) {
		super(player);
		this.type = "BISHOP";
	}

	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y,
			Board board) {
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		if(Move.isDiagonal(cur_x, cur_y, dest_x, dest_y)){
			return true;
		}
		
		return false;
	}

}
