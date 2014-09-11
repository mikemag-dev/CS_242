
public class Rook extends Piece {

	public Rook(int player) {
		super(player);
		this.type = "ROOK";
		this.hasMoved = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y, Board board) {
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		if(Move.isStraight(cur_x, cur_y, dest_x, dest_y)){
			return true;
		}
		
		return false;
	}

}
