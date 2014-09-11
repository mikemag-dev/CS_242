import static java.lang.Math.*;


public class Move {
	

	public static boolean isZeroVector(int cur_x, int cur_y, int dest_x,
			int dest_y) {
		return ((cur_x != dest_x) || (cur_y != dest_y));
	}
	
	/**
	 * @param cur_x
	 * @param cur_y
	 * @param dest_x
	 * @param dest_y
	 * @return returns true if horizontal movement distance is equal to vertical movement distance and both are greater than 1
	 */
	public static boolean isDiagonal(int cur_x, int cur_y, int dest_x, int dest_y){
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		
		
		
		if (x_distance != 0 && y_distance != 0 && x_distance == y_distance){
			return true;
		}
		else return false;
	}
	
	
	/**
	 * @param cur_x
	 * @param cur_y
	 * @param dest_x
	 * @param dest_y
	 * @return returns true if movement is only horizontal or vertical and the movement in the direction is greater than 0
	 */
	public static boolean isStraight(int cur_x, int cur_y, int dest_x, int dest_y){
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		
		
		if (x_distance*y_distance == 0 && x_distance + y_distance != 0){
			return true;
		}
		else return false;
	}
	
	public static boolean isWithinBounds(int dest_x, int dest_y, Board board){
				
		if(0 <= dest_x && dest_x < board.width && 0 <= dest_y && dest_y < board.height){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	public static boolean isObstructed(int cur_x, int cur_y, int dest_x, int dest_y, Board board){
		Piece cur_piece = board.getPieceAt(cur_x, cur_y);
		Piece piece_at_dest = board.getPieceAt(dest_x, dest_y);

		int leftmost_pos, rightmost_pos, topmost_pos, bottommost_pos;
		leftmost_pos = min(cur_x, dest_x);
		rightmost_pos = max(cur_x, dest_x);
		bottommost_pos = min(cur_y, dest_y);
		topmost_pos = max(cur_y, dest_y);
		
		//check if destination has a teammate already there
		if( piece_at_dest!=null && piece_at_dest.player == cur_piece.player){
			return true;
		}
		
		if(isDiagonal(cur_x, cur_y, dest_x, dest_y)){
			int slope_sign = (int) Math.signum((dest_y - cur_y)/(dest_x - cur_x));
			for(int i = 1; i<rightmost_pos - leftmost_pos; i++){
				Piece piece_between_dest = board.getPieceAt(leftmost_pos + i, cur_x == leftmost_pos ? cur_y + i*slope_sign : dest_y + i*slope_sign);
				if(piece_between_dest != null){
					return true;
				}
			}
		}
		else if(isStraight(cur_x, cur_y, dest_x, dest_y)){
			//is horizontal move
			if(cur_x != dest_x){
				for(int i = leftmost_pos+1; i<rightmost_pos; i++){
					Piece piece_between_dest = board.getPieceAt(i, cur_y);
					if(piece_between_dest != null){
						return true;
					}
				}
			}
			//is vertical move
			else if(cur_y != dest_y){
				for(int i = bottommost_pos+1; i<topmost_pos; i++){
					Piece piece_between_dest = board.getPieceAt(cur_x, i);
					if(piece_between_dest != null){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	public static boolean passesUniversalConstraints(int cur_x, int cur_y, int dest_x, int dest_y, Board board) {
		if(!Move.isWithinBounds(dest_x, dest_y, board)){
			return false;
		}		
		if(Move.isZeroVector(cur_x, cur_y, dest_x, dest_y)){
			return false;
		}
		return true;
	}
	
	public static boolean isLShaped(int cur_x, int cur_y, int dest_x, int dest_y) {
		int x_distance = abs(cur_x - dest_x);
		int y_distance = abs(cur_y - dest_y);
		
		if (x_distance * y_distance == 2){
			return true;
		}
		
		return false;
	}

	

	

	
}
