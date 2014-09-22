package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.chess.Move;




// TODO: Auto-generated Javadoc
/**
 * The Class Piece.
 *
 * @author MaguireM
 */
public abstract class Piece {
	
	/** The has moved. */
	public boolean hasMoved;
	
	/** The player. */
	public int color;
	
	public String getPieceImageKey() {
		return pieceImageKey;
	}


	public int getColor() {
		return color;
	}

	protected String pieceImageKey;
	
	/**
	 * Instantiates a new piece.
	 *
	 * @param player -1 if player is black, 1 is player is white
	 */
	public Piece(int player) {
		super();
		this.color = player;
		this.hasMoved = false;
	}


	/**
	 * Checks if is legal move relative to piece-specific constraints and obstructions on the board.
	 *
	 * @param curX the current x coordinate of the piece
	 * @param curY the current y coordinate of the piece
	 * @param destX the destination x coordinate of the piece
	 * @param destY the destination y coordinate of the piece
	 * @param board the current board
	 * @return true, if is legal move
	 */
	public abstract boolean isLegalMove(int curX, int curY, int destX, int destY, Board board);
	
	/**
	 * Passes universal constraints for all moves, that is,
	 * 		destination is within playable board grid
	 * 		destination square is not the same as a the starting square
	 * 		current position to destination position is not obstructed
	 *
	 * @param curX the current x coordinate of the piece
	 * @param curY the current y coordinate of the piece
	 * @param destX the destination x coordinate of the piece
	 * @param destY the destination y coordinate of the piece
	 * @param board the current board
	 * @return true, if board situation passes all the constraints
	 */
	protected boolean passesStandardUniversalConstraints(int curX, int curY, int destX, int destY, Board board){
		if(!Move.isWithinBounds(destX, destY, board)){
			return false;
		}
		if(!Move.isZeroVector(curX, curY, destX, destY)){
			return false;
		}
		if(Move.isObstructed(curX, curY, destX, destY, board)){
			return false;
		}
		return true;
	}

}
