import static java.lang.Math.abs;

public class King extends Piece {

	public King(int player) {
		super(player);
		this.type = "KING";
	}

	
	
	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y, Board board) {
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		if(x_distance * y_distance == 1 || x_distance + y_distance == 1 ){
			return true;
		}
		else{
			return false;
		}
	}

}
