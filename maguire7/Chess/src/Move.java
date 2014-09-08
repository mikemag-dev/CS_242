import static java.lang.Math.*;

public class Move {
	
	
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
		
		if (x_distance != 0 && x_distance == y_distance){
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
		int leftmost_pos, rightmost_pos, topmost_pos, bottommost_pos;
		leftmost_pos = min(cur_x, dest_x);
		rightmost_pos = max(cur_x, dest_x);
		bottommost_pos = min(cur_y, dest_y);
		topmost_pos = max(cur_y, dest_y);
		
		if(isDiagonal(cur_x, cur_y, dest_x, dest_y)){
			for(int i = 1; i<topmost_pos-bottommost_pos; i++){
				if(board.grid[bottommost_pos + i][leftmost_pos + i].isOccupied()){
					return true;
				}
			}
		}
		else if(isStraight(cur_x, cur_y, dest_x, dest_y)){
			if(cur_x != dest_x){
				for(int i = leftmost_pos+1; i<rightmost_pos; i++){
					if(board.grid[i][dest_y].isOccupied()){
						return true;
					}
				}
			}
			if(cur_y != dest_y){
				for(int i = topmost_pos+1; i<bottommost_pos; i++){
					if(board.grid[i][dest_y].isOccupied()){
						return true;
					}
				}
			}
		}
		else{
			return false;
		}
	}
	
}
