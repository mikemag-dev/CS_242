package edu.illinois.cs242.pieces;
import edu.illinois.cs242.chess.Board;



// TODO: Auto-generated Javadoc
/**
 * The Class Pawn.
 */
public class Pawn extends Piece {
	
	/** The step size. */
	private int stepSize;
	
	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
	}

	public int getStepSize() {
		return stepSize;
	}

	/**
	 * Instantiates a new pawn.
	 *
	 * @param player the player
	 */
	public Pawn(int color) {
		super(color);
		this.stepSize = 2*color;	
		pieceImageKey = color == Board.WHITE ? "res/white_pawn.png" : "res/black_pawn.png";
	}

	/**
	 * Can attack.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param board the board
	 * @param direction the direction
	 * @return true, if successful
	 */
	public boolean canAttack(int curX, int curY, Board board, int direction){
		int attackbleYCoord = (int) (curY + Math.signum(stepSize));
		int attackableXCoord = curX + direction;
		if ((0 <= attackbleYCoord && attackbleYCoord < board.height) &&
			(0 <= attackableXCoord && attackableXCoord < board.width)	){
			Piece toAttack = board.getPieceAt(attackableXCoord, attackbleYCoord);
			return null != toAttack && toAttack.color != this.color;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see Piece#isLegalMove(int, int, int, int, Board)
	 */
	@Override
	public boolean isLegalMove(int curX, int curY, int destX, int destY, Board board) {
		int oneStepForwardYCoord = (int) (curY + Math.signum(stepSize));
		int oneStepLeftXCoord = curX - 1;
		int oneStepRightXCoord = curX + 1;
		
		//pawns cannot attack forward, only diagonal
		if((board.getPieceAt(destX, destY) != null) && (curX == destX)){
			return false;
		}
		
		if(!passesStandardUniversalConstraints(curX, curY, destX, destY, board)){
			return false;
		}
		
		//left attack
		if(canAttack(curX, curY, board, -1) && 
			destX == oneStepLeftXCoord && 
			destY == oneStepForwardYCoord){
			return true;
		}
		
		//right attack
		if(canAttack(curX, curY, board, 1) && 
			destX == oneStepRightXCoord && 
			destY == oneStepForwardYCoord){
				return true;
		}	
		
		//already checked for attack cases
		if(destX != curX){
			return false;
		}
		
		if(destY - curY != stepSize && destY - curY != stepSize/2){
			return false;
		}
		
		return true;
	}

	
}
