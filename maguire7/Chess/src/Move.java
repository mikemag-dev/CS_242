import static java.lang.Math.*;


// TODO: Auto-generated Javadoc
/**
 * The Class Move.
 */
public class Move {
	

	/**
	 * Checks if is zero vector.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return true, if is zero vector
	 */
	public static boolean isZeroVector(int curX, int curY, int destX,
			int destY) {
		return ((curX != destX) || (curY != destY));
	}
	
	/**
	 * Checks if is diagonal.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return returns true if horizontal movement distance is equal to vertical movement distance and both are greater than 1
	 */
	public static boolean isDiagonal(int curX, int curY, int destX, int destY){
		int distanceInX = abs(curX - destX);
		int distanceInY = abs(curY - destY);
		
		
		
		if (distanceInX != 0 && distanceInY != 0 && distanceInX == distanceInY){
			return true;
		}
		else return false;
	}
	
	
	/**
	 * Checks if is straight.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return returns true if movement is only horizontal or vertical and the movement in the direction is greater than 0
	 */
	public static boolean isStraight(int curX, int curY, int destX, int destY){
		int distanceInX = abs(curX - destX);
		int distanceInY = abs(curY - destY);
		
		
		if (distanceInX*distanceInY == 0 && distanceInX + distanceInY != 0){
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if is within bounds.
	 *
	 * @param destX the destX
	 * @param destY the destY
	 * @param board the board
	 * @return true, if is within bounds
	 */
	public static boolean isWithinBounds(int destX, int destY, Board board){
				
		if(0 <= destX && destX < board.width && 0 <= destY && destY < board.height){
			return true;
		}
		
		else{
			return false;
		}
	}
	
	/**
	 * Checks if is obstructed.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @param board the board
	 * @return true, if is obstructed
	 */
	public static boolean isObstructed(int curX, int curY, int destX, int destY, Board board){
		Piece curPiece = board.getPieceAt(curX, curY);
		Piece pieceAtDest = board.getPieceAt(destX, destY);

		int leftmostPos, rightmostPos, topmostPos, bottommostPos;
		leftmostPos = min(curX, destX);
		rightmostPos = max(curX, destX);
		bottommostPos = min(curY, destY);
		topmostPos = max(curY, destY);
		
		//check if destination has a teammate already there
		if( pieceAtDest!=null && pieceAtDest.player == curPiece.player){
			return true;
		}
		
		if(isDiagonal(curX, curY, destX, destY)){
			int slopeSign = (int) Math.signum((destY - curY)/(destX - curX));
			for(int i = 1; i<rightmostPos - leftmostPos; i++){
				Piece pieceBetweenDest = board.getPieceAt(leftmostPos + i, curX == leftmostPos ? curY + i*slopeSign : destY + i*slopeSign);
				if(pieceBetweenDest != null){
					return true;
				}
			}
		}
		else if(isStraight(curX, curY, destX, destY)){
			//is horizontal move
			if(curX != destX){
				for(int i = leftmostPos+1; i<rightmostPos; i++){
					Piece pieceBetweenDest = board.getPieceAt(i, curY);
					if(pieceBetweenDest != null){
						return true;
					}
				}
			}
			//is vertical move
			else if(curY != destY){
				for(int i = bottommostPos+1; i<topmostPos; i++){
					Piece pieceBetweenDest = board.getPieceAt(curX, i);
					if(pieceBetweenDest != null){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if is l shaped.
	 *
	 * @param curX the curX
	 * @param curY the curY
	 * @param destX the destX
	 * @param destY the destY
	 * @return true, if is l shaped
	 */
	public static boolean isLShaped(int curX, int curY, int destX, int destY) {
		int distanceInX = abs(curX - destX);
		int distanceInY = abs(curY - destY);
		
		if (distanceInX * distanceInY == 2){
			return true;
		}
		
		return false;
	}

	

	

	
}
