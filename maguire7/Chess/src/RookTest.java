import static org.junit.Assert.*;

import org.junit.Test;


public class RookTest {

	@Test
	public void testMoveStraightOneStep() {
		Board board1 = new Board(8, 8, true);
		int cur_x = 4, cur_y = 4;
		
		board1.setPieceAt(cur_x, cur_y, new Rook(1));
		
		//test diags
		if(board1.tryMove(cur_x, cur_y, 5, 5)  || 
			(board1.tryMove(cur_x, cur_y, 3, 5)) || 
			(board1.tryMove(cur_x, cur_y, 5, 3)) || 
			(board1.tryMove(cur_x, cur_y, 3, 3))){
			fail();
		}
		
		//test 1 up
		if(!(board1.tryMove(cur_x, cur_y, 4, 5))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test 1 down
		if(!(board1.tryMove(cur_x, cur_y, 4, 3))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test 1 left
		if(!(board1.tryMove(cur_x, cur_y, 3, 4))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test 1 right
		if(!(board1.tryMove(cur_x, cur_y, 5, 4))){
			fail(); 
		}
	}
	
	@Test
	public void testMoveStraightThreeStep() {
		Board board1 = new Board(8, 8, true);
		int cur_x = 4, cur_y = 4, offset = 3;
		
		board1.setPieceAt(cur_x, cur_y, new Rook(1));
		
		//test diags
		if(	 board1.tryMove(cur_x, cur_y, cur_x+offset, cur_y+offset)  || 
			(board1.tryMove(cur_x, cur_y, cur_x-offset, cur_y+offset)) || 
			(board1.tryMove(cur_x, cur_y, cur_x+offset, cur_y-offset)) || 
			(board1.tryMove(cur_x, cur_y, cur_x-offset, cur_y-offset))){
			fail();
		}
		
		//test offset up
		if(!(board1.tryMove(cur_x, cur_y, cur_x, cur_y+offset))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test offset down
		if(!(board1.tryMove(cur_x, cur_y, cur_x, cur_y-offset))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test offset left
		if(!(board1.tryMove(cur_x, cur_y, cur_x+offset, cur_y))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
		
		//test offset right
		if(!(board1.tryMove(cur_x, cur_y, cur_x-offset, cur_y))){
			fail(); 
		}
		board1.setPieceAt(cur_x, cur_y, new Rook(-1));
	}
	@Test
	public void testMoveStraightObstructed(){
		Board board1 = new Board(8, 8, true);
		int cur_x = 4, cur_y = 4, offset = 3;
		
		board1.setPieceAt(cur_x, cur_y, new Rook(1));
		board1.setPieceAt(cur_x+1, cur_y, new Pawn(-1));
		board1.setPieceAt(cur_x, cur_y+1, new Pawn(-1));
		board1.setPieceAt(cur_x-1, cur_y, new Pawn(-1));
		board1.setPieceAt(cur_x, cur_y-1, new Pawn(-1));
		
		//test offset up
		if(board1.tryMove(cur_x, cur_y, cur_x, cur_y+offset)){
			fail(); 
		}
		
		//test offset down
		if(board1.tryMove(cur_x, cur_y, cur_x, cur_y-offset)){
			fail(); 
		}
		
		//test offset left
		if(board1.tryMove(cur_x, cur_y, cur_x+offset, cur_y)){
			fail(); 
		}
		
		//test offset right
		if(board1.tryMove(cur_x, cur_y, cur_x-offset, cur_y)){
			fail(); 
		}
	}
	

}
