import static org.junit.Assert.*;

import org.junit.Test;


public class KnightTest {

	@Test
	public void testKnightStep() {
		Board board = new Board(8, 8, false);
		if(!board.tryMove(1, 0, 2, 2)) fail();
		if(!board.tryMove(2, 2, 1, 0)) fail();

		if(!board.tryMove(6, 0, 5, 2)) fail();
		if(!board.tryMove(5, 2, 6, 0)) fail();

		if(!board.tryMove(1, 7, 2, 5)) fail();
		if(!board.tryMove(2, 5, 1, 7)) fail();
		
		if(!board.tryMove(6, 7, 5, 5)) fail();
		if(!board.tryMove(5, 5, 6, 7)) fail();
	}

	@Test
	public void testKnightAttack() {
		Board board = new Board(8, 8, false);
		board.setPieceAt(2, 2, new Pawn(1));
		board.setPieceAt(5, 2, new Pawn(-1));

		if(board.tryMove(1, 0, 2, 2)) fail();
		if(board.tryMove(2, 2, 1, 0)) fail();
		
		if(!board.tryMove(6, 0, 5, 2)) fail();
		if(!board.tryMove(5, 2, 6, 0)) fail();
	}
}
