package edu.illinois.cs242.models;

import edu.illinois.cs242.pieces.Bishop;
import edu.illinois.cs242.pieces.ChessPiece;
import edu.illinois.cs242.pieces.King;
import edu.illinois.cs242.pieces.Knight;
import edu.illinois.cs242.pieces.Pawn;
import edu.illinois.cs242.pieces.Queen;
import edu.illinois.cs242.pieces.Rook;

public class SubmittedMove {
	ChessPiece pieceMoved, pieceTaken;
	
	public ChessPiece getPieceMoved() {
		return pieceMoved;
	}

	int originX, originY, destX, destY;
	
	public ChessPiece getPieceTaken() {
		return pieceTaken;
	}

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}

	public int getDestX() {
		return destX;
	}

	public int getDestY() {
		return destY;
	}

	public SubmittedMove(int fromX, int fromY, int toX, int toY, ChessPiece pieceMoved, ChessPiece pieceTaken){
		this.originX = fromX;
		this.originY = fromY;
		this.destX = toX;
		this.destY = toY;
		if(pieceTaken instanceof Pawn) this.pieceTaken = new Pawn( (Pawn) pieceTaken);
		if(pieceTaken instanceof Rook) this.pieceTaken = new Rook( (Rook) pieceTaken);
		if(pieceTaken instanceof Knight) this.pieceTaken = new Knight( (Knight) pieceTaken);
		if(pieceTaken instanceof Bishop) this.pieceTaken = new Bishop( (Bishop) pieceTaken);
		if(pieceTaken instanceof Queen) this.pieceTaken = new Queen( (Queen) pieceTaken);
		if(pieceTaken instanceof King) this.pieceTaken = new King( (King) pieceTaken);
		
		if(pieceMoved instanceof Pawn) this.pieceMoved = new Pawn( (Pawn) pieceMoved);
		if(pieceMoved instanceof Rook) this.pieceMoved = new Rook( (Rook) pieceMoved);
		if(pieceMoved instanceof Knight) this.pieceMoved = new Knight( (Knight) pieceMoved);
		if(pieceMoved instanceof Bishop) this.pieceMoved = new Bishop( (Bishop) pieceMoved);
		if(pieceMoved instanceof Queen) this.pieceMoved = new Queen( (Queen) pieceMoved);
		if(pieceMoved instanceof King) this.pieceMoved = new King( (King) pieceMoved);
	}
}
