import static java.lang.Math.abs;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Board. 
 */
public class Board {
	
	public static final int WHITE = 1;
	public static final int BLACK = -1;
	
	/** The width. */
	public int width;
	
	/** The height. */
	public int height;
	
	/** The grid. */
	public Square[][] grid;
	
	public ArrayList<Integer> whiteKingPos;
	public ArrayList<Integer> blackKingPos;

	/**
	 * Instantiates a new board.
	 *
	 * @param width the width
	 * @param height the height
	 * @param doNotPopulate whether or not to populate the board with standard 32 pieces
	 */
	public Board(int width, int height, boolean doNotPopulate){
		this.width = width;
		this.height = height;
		this.grid = new Square[width][height];
		
		
		for(int i = 0; i<width; i++){
			for(int j = 0; j<height; j++){
				this.grid[i][j] = new Square(true);
				if(doNotPopulate) continue;
				
				//pawn logic
				if(j == 1) setPieceAt(i, j, new Pawn(WHITE));
				if(j == height-2) setPieceAt(i, j, new Pawn(BLACK));
				
				//white piece logic
				if((j == 0 && i == 0) || (j == 0 && i == width-1)) setPieceAt(i, j, new Rook(WHITE));
				if((j == 0 && i == 1) || (j == 0 && i == width-2)) setPieceAt(i, j, new Knight(WHITE));
				if((j == 0 && i == 2) || (j == 0 && i == width-3)) setPieceAt(i, j, new Bishop(WHITE));
				if(j == 0 && i == 3) setPieceAt(i, j, new Queen(WHITE));
				if(j == 0 && i == 4) setPieceAt(i, j, new King(WHITE));
				
				
				//black piece logic
				if((j == height-1 && i == 0) || (j == height-1 && i == width-1)) setPieceAt(i, j, new Rook(BLACK));
				if((j == height-1 && i == 1) || (j == height-1 && i == width-2)) setPieceAt(i, j, new Knight(BLACK));
				if((j == height-1 && i == 2) || (j == height-1 && i == width-3)) setPieceAt(i, j, new Bishop(BLACK));
				if(j == height-1 && i == 3) setPieceAt(i, j, new Queen(BLACK));
				if(j == height-1 && i == 4)	setPieceAt(i, j, new King(BLACK));
				
			}
		}
	}
	
	/**
	 * Gets the piece at given coordinates.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the piece at given coordinates, null if no piece
	 */
	public Piece getPieceAt(int x, int y) {
		return this.grid[x][y].getPiece();
	}

	/**
	 * Sets the piece at given coordinates. Update's king position if piece being set is king.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param piece the piece to set at given coordinates
	 */
	public void setPieceAt(int x, int y, Piece piece) {
		this.grid[x][y].setPiece(piece);
		if(piece instanceof King){
			if (piece.player == WHITE){
				if (whiteKingPos == null){
					whiteKingPos = new ArrayList<Integer>();
					whiteKingPos.add(-1);
					whiteKingPos.add(-1);
				}
				whiteKingPos.set(0, x);
				whiteKingPos.set(1, y);
			}
			else{
				if (blackKingPos == null){
					blackKingPos = new ArrayList<Integer>();
					blackKingPos.add(-1);
					blackKingPos.add(-1);
				}
				blackKingPos.set(0, x);
				blackKingPos.set(1, y);
			}
		}
	}
	
	/**
	 * Confirms or denies whether the passed player is in check.
	 *
	 * @param curPlayer the curPlayer
	 * @return true, if passed player is in check
	 */
	public boolean inCheck(int curPlayer) {
		
		ArrayList<Integer> kingCoord = curPlayer == WHITE ? whiteKingPos : blackKingPos;
		if(kingCoord == null) return false;
		for(int i = 0; i<this.width; i++){
			for(int j = 0; j<this.height; j++){
				Piece pieceAtIdx = this.getPieceAt(i, j);
				if(null != pieceAtIdx && pieceAtIdx.player != curPlayer && pieceAtIdx.isLegalMove(i, j, kingCoord.get(0), kingCoord.get(1), this)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Puts self in check.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return true, if submitted move creates a self check
	 */
	public boolean putsSelfInCheck(int curX, int curY, int destX,
			int destY) {
		boolean putsSelfInCheck = false;
		int curPlayer;
		Piece curPiece, pieceAtDest;

		// move piece temporarily
		curPiece = this.getPieceAt(curX, curY);
		this.setPieceAt(curX, curY, null);
		pieceAtDest = this.getPieceAt(destX, destY);
		this.setPieceAt(destX, destY, curPiece);

		curPlayer = curPiece.player;

		if (inCheck(curPlayer)) {
			putsSelfInCheck = true;
		}

		setPieceAt(curX, curY, curPiece);
		setPieceAt(destX, destY, pieceAtDest);
		return putsSelfInCheck;
	}
	
	/**
	 * Tries move. Performs check to see if move is within piece's reachable positions and does not moving current player in check. 
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return true, if move gets submitted
	 */
	public boolean tryMove(int curX, int curY, int destX, int destY) {

		
		Piece pieceToMove = getPieceAt(curX, curY);

		if ((!pieceToMove.isLegalMove(curX, curY, destX, destY, this)) ||
			(putsSelfInCheck(curX, curY, destY, destY))) {
			return false;
		}

		this.grid[curX][curY].setPiece(null);
		this.grid[destX][destY].setPiece(pieceToMove);

		if (!pieceToMove.hasMoved) {
			pieceToMove.hasMoved = true;
			if (pieceToMove instanceof Pawn) {
				Pawn pieceAsPawn = (Pawn) pieceToMove;
				pieceAsPawn.stepSize /= 2;
			}
		}

		return true;
	}

	/**
	 * Whether or not current player is in checkmate.
	 *
	 * @param curPlayer the current player
	 * @return true, if passed player is in checkmate
	 */
	public boolean inCheckmate(int curPlayer){
		if(!inCheck(curPlayer)) return false;
		ArrayList<Integer> kingCoord = curPlayer == WHITE ? whiteKingPos : blackKingPos;
		int curX = kingCoord.get(0), curY = kingCoord.get(1);
		King king = (King) getPieceAt(curX, curY);

		//check if King has moves (for optimization)
		for(int i = -1; i<= 1; i++){
			for(int j = -1; j<= 1; j++){
				if(king.isLegalMove(curX, curY, curX + i, curY + j, this) && !putsSelfInCheck(curX, curY, curX + i, curY + j)){
					return false;
				}
			}
		}
		
		return !hasAtLeastOneMove(curPlayer);
	}

	/**
	 * Checks for at least one move.
	 *
	 * @param curPlayer the curPlayer
	 * @return true, if successful
	 */
	private boolean hasAtLeastOneMove(int curPlayer) {
		for(int i = 0; i<width; i++){
			for(int j = 0; j<height; j++){
				Piece curPiece = getPieceAt(i,j);
				if(curPiece != null && curPiece.player == curPlayer){
					for(int m = 0; m<width; m++){
						for(int n = 0; n<height; n++){
							if(curPiece.isLegalMove(i, j, m, n, this) && !putsSelfInCheck(i, j, m, n)){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not passed player is in stalemate.
	 *
	 * @param curPlayer the curPlayer
	 * @return true, if successful
	 */
	public boolean inStalemate(int curPlayer){
		if(inCheck(curPlayer)){
			return false;
		}
		return !hasAtLeastOneMove(curPlayer);
	}

	boolean isOneSquareAway(int curX, int curY, int destX, int destY) {
		int distanceX = abs(curX - destX);
		int distanceY = abs(curY - destY);		
		if(distanceX * distanceY == 1 || distanceX + distanceY == 1 ){
			return true;
		}
		else{
			return false;
		}
	}
}