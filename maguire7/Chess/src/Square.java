
public class Square {
	private boolean playable;
	private Piece piece;
	
	public Square(boolean playable){
		this.playable = playable;
		this.piece = null;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isPlayable() {
		return playable;
	}
	public void setPlayable(boolean playable) {
		this.playable = playable;
	}

	public boolean isOccupied() {
		return piece != null;
	}
}
