package edu.illinois.cs242.chess;

import edu.illinois.cs242.pieces.ChessPiece;


// TODO: Auto-generated Javadoc
/**
 * The Class Square.
 */
public class Square {
	
	/** The playable. */
	private boolean playable;
	
	/** The piece. */
	private ChessPiece piece;
	
	/**
	 * Instantiates a new square.
	 *
	 * @param playable the playable
	 */
	public Square(boolean playable){
		this.playable = playable;
		this.piece = null;
	}
	
	/**
	 * Gets the piece off the square.
	 *
	 * @return the piece
	 */
	public ChessPiece getPiece() {
		return piece;
	}

	/**
	 * Sets the piece.
	 *
	 * @param piece the new piece
	 */
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}

	/**
	 * Checks if is playable.
	 *
	 * @return true, if is playable
	 */
	public boolean isPlayable() {
		return playable;
	}
	
	/**
	 * Sets the playable.
	 *
	 * @param playable whether the square is playable
	 */
	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied() {
		return piece != null;
	}
}
