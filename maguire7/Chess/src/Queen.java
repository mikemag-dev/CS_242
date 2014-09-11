public class Queen extends Piece {
	
	

	public Queen(int player) {
		super(player);
		this.type = "QUEEN";
	}

	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y, Board board) {
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		if(Move.isDiagonal(cur_x, cur_y, dest_x, dest_y)||
		   Move.isStraight(cur_x, cur_y, dest_x, dest_y)){
			return true;
		}
		
		return false;
	}

	

}
