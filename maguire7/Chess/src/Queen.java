import static java.lang.Math.abs;


public class Queen extends Piece {
	
	

	public Queen(int color) {
		super(color);
		this.type = "Queen";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y) {
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		
		
		if(x_distance * y_distance == 1 || x_distance + y_distance == 1 ){
			return true;
		}
		else{
			return false;
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	

}
