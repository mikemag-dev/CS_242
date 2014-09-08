
abstract class Piece {


	public int color;
	public String type;
	
	
	public Piece(int color) {
		super();
		this.color = color;
	}

	public int getPieceType(PieceEnum piece_name){
		switch(piece_name){
		case PAWN:
			return 0;
		case ROOK:
			return 1;
		case KNIGHT:
			return 2;
		case BISHOP:
			return 3;
		case QUEEN:
			return 4;
		case KING:
			return 5;
		default:
			return -1;
		}
	}
	

	public abstract boolean isLegalMove(int cur_x, int cur_y, int dest_x, int dest_y); 
}
