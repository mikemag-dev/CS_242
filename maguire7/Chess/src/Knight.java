
public class Knight extends Piece {

	public Knight(int color) {
		super(color);
		this.type = "KNIGHT";
	}

	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y,
			Board board) {
		
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		if(Move.isLShaped(cur_x, cur_y, dest_x, dest_y)){
			return true;
		}
		
		return false;
	}

}
