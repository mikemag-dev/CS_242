import java.util.ArrayList;

public class Board {
	public int width, height;
	public Square[][] grid;

	public Board(int width, int height, boolean empty){
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
		
		
		for(int i = 0; i<width; i++){
			for(int j = 0; j<height; j++){
				this.grid[i][j] = new Square(true);
				if(empty) continue;
				
				//pawn logic
				if(j == 1) setPieceAt(i, j, new Pawn(1));
				if(j == height-2) setPieceAt(i, j, new Pawn(-1));
				
				//white piece logic
				if((j == 0 && i == 0) || (j == 0 && i == width-1)) setPieceAt(i, j, new Rook(1));
				if((j == 0 && i == 1) || (j == 0 && i == width-2)) setPieceAt(i, j, new Knight(1));
				if((j == 0 && i == 2) || (j == 0 && i == width-3)) setPieceAt(i, j, new Bishop(1));
				if(j == 0 && i == 3) setPieceAt(i, j, new Queen(1));
				if(j == 0 && i == 4) setPieceAt(i, j, new King(1));
				
				//black piece logic
				if((j == height-1 && i == 0) || (j == height-1 && i == width-1)) setPieceAt(i, j, new Rook(-1));
				if((j == height-1 && i == 1) || (j == height-1 && i == width-2)) setPieceAt(i, j, new Knight(-1));
				if((j == height-1 && i == 2) || (j == height-1 && i == width-3)) setPieceAt(i, j, new Bishop(-1));
				if(j == height-1 && i == 3) setPieceAt(i, j, new Queen(-1));
				if(j == height-1 && i == 4) setPieceAt(i, j, new King(-1));
			}
		}
	}
	
	public Piece getPieceAt(int x, int y) {
		return this.grid[x][y].getPiece();
	}

	public void setPieceAt(int x, int y, Piece piece) {
		this.grid[x][y].setPiece(piece);
	}

	public ArrayList<Integer> findKing(int cur_player) {
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				Piece piece_at_idx = this.getPieceAt(i, j);
				if (null != piece_at_idx && piece_at_idx.type.equals("KING")
						&& piece_at_idx.player == cur_player) {
					ArrayList<Integer> king_coord = new ArrayList<Integer>();
					king_coord.add(i);
					king_coord.add(j);
					return king_coord;
				}
			}
		}
		return null;
	}
	
	public boolean inCheck(int cur_player) {
		
		ArrayList<Integer> king_coord = this.findKing(cur_player);
		if(king_coord == null) return false;
		for(int i = 0; i<this.width; i++){
			for(int j = 0; j<this.height; j++){
				Piece piece_at_idx = this.getPieceAt(i, j);
				if(null != piece_at_idx && piece_at_idx.player != cur_player && piece_at_idx.isLegalMove(i, j, king_coord.get(0), king_coord.get(1), this)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean putsSelfInCheck(int cur_x, int cur_y, int dest_x,
			int dest_y) {
		boolean puts_self_in_check = false;
		int cur_player;
		Piece cur_piece, dest_piece;

		// move piece temporarily
		cur_piece = this.getPieceAt(cur_x, cur_y);
		this.setPieceAt(cur_x, cur_y, null);
		dest_piece = this.getPieceAt(dest_x, dest_y);
		this.setPieceAt(dest_x, dest_y, cur_piece);

		cur_player = cur_piece.player;

		if (inCheck(cur_player)) {
			puts_self_in_check = true;
		}

		setPieceAt(cur_x, cur_y, cur_piece);
		setPieceAt(dest_x, dest_y, dest_piece);
		return puts_self_in_check;
	}
	
	public boolean tryMove(int cur_x, int cur_y, int dest_x, int dest_y) {

		
		Piece pieceToMove = getPieceAt(cur_x, cur_y);

		if ((!pieceToMove.isLegalMove(cur_x, cur_y, dest_x, dest_y, this)) ||
			(putsSelfInCheck(cur_x, cur_y, dest_y, dest_y))) {
			return false;
		}

		this.grid[cur_x][cur_y].setPiece(null);
		this.grid[dest_x][dest_y].setPiece(pieceToMove);

		if (!pieceToMove.hasMoved) {
			pieceToMove.hasMoved = true;
			if (pieceToMove.type.equals("PAWN")) {
				Pawn pieceAsPawn = (Pawn) pieceToMove;
				pieceAsPawn.stepSize /= 2;
			}
		}

		return true;
	}

	//returns 1 if checkmate, 0 if no checkmate, and -1 if stalemate
	public boolean inCheckmate(int cur_color){
		if(!inCheck(cur_color)) return false;
		ArrayList<Integer> king_coord = findKing(cur_color);
		int cur_x = king_coord.get(0), cur_y = king_coord.get(1);
		King king = (King) getPieceAt(cur_x, cur_y);

		for(int i = -1; i<= 1; i++){
			for(int j = -1; j<= 1; j++){
				if(king.isLegalMove(cur_x, cur_y, cur_x + i, cur_y + j, this) && !putsSelfInCheck(cur_x, cur_y, cur_x + i, cur_y + j)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean inStalemate(int cur_player){
		//not yet implemented
		return true;
	}
}