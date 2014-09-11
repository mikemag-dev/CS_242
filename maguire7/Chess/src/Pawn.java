public class Pawn extends Piece {
	int stepSize;
	
	public Pawn(int player) {
		super(player);
		this.type = "PAWN";
		this.stepSize = 2*player;	
	}

	public boolean canAttack(int cur_x, int cur_y, Board board, int direction){
		int attackable_y_coord = (int) (cur_y + Math.signum(stepSize));
		int attackable_x_coord = cur_x + direction;
		if ((0 <= attackable_y_coord && attackable_y_coord < board.height) &&
			(0 <= attackable_x_coord && attackable_x_coord < board.width)	){
			Piece toAttack = board.getPieceAt(attackable_x_coord, attackable_y_coord);
			return null != toAttack && toAttack.player != this.player;
		}
		return false;
	}
	
	@Override
	public boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y, Board board) {
		int one_step_forward_y_coord = (int) (cur_y + Math.signum(stepSize));
		int one_step_left_x_coord = cur_x - 1;
		int one_step_right_x_coord = cur_x + 1;
		
		//pawns cannot attack forward, only diagonal
		if((board.getPieceAt(dest_x, dest_y) != null) && (cur_x == dest_x)){
			return false;
		}
		
		if(!passesUniversalConstraints(cur_x, cur_y, dest_x, dest_y, board)){
			return false;
		}
		
		//left attack
		if(canAttack(cur_x, cur_y, board, -1) && 
			dest_x == one_step_left_x_coord && 
			dest_y == one_step_forward_y_coord){
			return true;
		}
		
		//right attack
		if(canAttack(cur_x, cur_y, board, 1) && 
			dest_x == one_step_right_x_coord && 
			dest_y == one_step_forward_y_coord){
				return true;
		}	
		
		//already checked for attack cases
		if(dest_x != cur_x){
			return false;
		}
		
		if(dest_y - cur_y != stepSize && dest_y - cur_y != stepSize/2){
			return false;
		}
		
		return true;
	}

	
}
