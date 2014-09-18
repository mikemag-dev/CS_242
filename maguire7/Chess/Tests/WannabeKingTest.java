import static org.junit.Assert.*;

import org.junit.Test;


public class WannabeKingTest {

	@Test
	public void testPossibleMoves() {
		Board board = new Board(8, 8, true);
		board.setPieceAt(2, 2, new Pawn(Board.BLACK));
		board.setPieceAt(2, 3, new Pawn(Board.BLACK) );
		board.setPieceAt(2, 4, new Pawn(Board.BLACK));
		board.setPieceAt(3, 2, new Pawn(Board.BLACK) );
		board.setPieceAt(3, 4, new Pawn(Board.BLACK));
		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );

		if(!board.tryMove(3,3,2,2)){
			fail();
		}

		
		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );
		if(!board.tryMove(3,3,2,3)){
			fail();
		}
		
		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );
		if(!board.tryMove(3,3,2,4)){
			fail();
		}
		
		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );
		if(!board.tryMove(3,3,3,4)){
			fail();
		}
		

		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );
		if(!board.tryMove(3,3,4,4)){
			fail();
		}
		
		board.setPieceAt(3, 3, new WannabeKing(Board.WHITE) );
		board.setPieceAt(4, 4, new WannabeKing(Board.WHITE) );
		if(board.tryMove(3,3,4,4)){
			fail();
		}
		
		if(board.tryMove(4, 4, 6, 6)){
			fail();
		}
	}
}
