

import static org.junit.Assert.*;

import org.junit.Test;


public class PawnTest {

	@Test
	public void testMovePawnOneStep() {
		Board board = new Board(8, 8, false);
		if(!board.tryMove(0, 1, 0, 2)){
			fail();
		}
		if(!board.tryMove(0, 6, 0, 5)){
			fail();
		}
	}

	@Test
	public void testMovePawnTwoStep() {
		Board board = new Board(8, 8, false);
		if(!board.tryMove(0, 1, 0, 3)){
			fail();
		}
		if(board.tryMove(0, 3, 0, 5)){
			fail();
		}
		if(!board.tryMove(0, 3, 0, 4)){
			fail();
		}
		
		
		if(!board.tryMove(1, 6, 1, 4)){
			fail();
		}
		if(board.tryMove(1, 4, 1, 2)){
			fail();
		}
		if(!board.tryMove(1, 4, 1, 3)){
			fail();
		}
	}
	
	@Test
	public void testPawnAttack()
	{
		Board board = new Board(8, 8, true);
		board.setPieceAt(5, 5, new Pawn(-1));
		board.setPieceAt(4, 4, new Pawn(1));
		if(!board.tryMove(4, 4, 5, 5)){
			fail();
		}	
		
		board.setPieceAt(5, 5, new Pawn(-1));
		board.setPieceAt(4, 4, new Pawn(1));
 		if(!board.tryMove(5, 5, 4, 4)){
			fail();
		}
 		
		board.setPieceAt(4, 5, new Pawn(-1));
		board.setPieceAt(5, 4, new Pawn(1));
		if(!board.tryMove(4, 5, 5, 4)){
			fail();
		}	
		
		board.setPieceAt(4,5, new Pawn(-1));
		board.setPieceAt(5,4, new Pawn(1));
 		if(!board.tryMove(5, 4, 4, 5)){
			fail();
		}
 		
 		board.setPieceAt(4, 6, new Pawn(-1));
		board.setPieceAt(5, 4, new Pawn(1));
		if(board.tryMove(4, 6, 5, 4)){
			fail();
		}	
		
		board.setPieceAt(4,6, new Pawn(-1));
		board.setPieceAt(5,4, new Pawn(1));
 		if(board.tryMove(5, 4, 4, 6)){
			fail();
		}
	}

}
