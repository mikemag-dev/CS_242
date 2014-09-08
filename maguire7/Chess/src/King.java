import static java.lang.Math.abs

public class King extends Piece {

	public King(int color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y) {
		// TODO Auto-generated method stub
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		
		if(x_distance * y_distance == 1 || x_distance + y_distance == 1 ){
			return true;
		}
		else{
			return false;
		}
	}

}
