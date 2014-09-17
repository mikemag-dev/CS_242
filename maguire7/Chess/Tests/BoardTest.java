
import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	
	
	@Test
	public void testConstructor() {
		Board x = new Board(8, 8, false);
		//if(x == null) fail("NULL");
		
		                     
		if(!(x.getPieceAt(0, 0) instanceof Rook)) fail();
		if(!(x.getPieceAt(1, 0) instanceof Knight)) fail();
		if(!(x.getPieceAt(2, 0) instanceof Bishop)) fail();
		if(!(x.getPieceAt(3, 0) instanceof Queen)) fail();
		if(!(x.getPieceAt(4, 0) instanceof King)) fail();
		if(!(x.getPieceAt(5, 0) instanceof Bishop)) fail();
		if(!(x.getPieceAt(6, 0) instanceof Knight)) fail();
		if(!(x.getPieceAt(7, 0) instanceof Rook)) fail();
		if(!(x.getPieceAt(0, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(1, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(2, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(3, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(4, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(5, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(6, 1) instanceof Pawn)) fail();
		if(!(x.getPieceAt(7, 1) instanceof Pawn)) fail();

		if(!(x.getPieceAt(0, 7) instanceof Rook)) fail();
		if(!(x.getPieceAt(1, 7) instanceof Knight)) fail();
		if(!(x.getPieceAt(2, 7) instanceof Bishop)) fail();
		if(!(x.getPieceAt(3, 7) instanceof Queen)) fail();
		if(!(x.getPieceAt(4, 7) instanceof King)) fail();
		if(!(x.getPieceAt(5, 7) instanceof Bishop)) fail();
		if(!(x.getPieceAt(6, 7) instanceof Knight)) fail();
		if(!(x.getPieceAt(7, 7) instanceof Rook)) fail();
		if(!(x.getPieceAt(0, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(1, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(2, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(3, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(4, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(5, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(6, 6) instanceof Pawn)) fail();
		if(!(x.getPieceAt(7, 6) instanceof Pawn)) fail();		
	}

	@Test
	public void testInCheck()
	{
		Board board = new Board(8, 8, true);
		
		//check by Pawn
		board.setPieceAt(4, 4, new King(-1));
		board.setPieceAt(3, 3, new Pawn(1));
		
		if(!board.inCheck(-1)) fail();
		board.setPieceAt(3, 3, new Pawn(-1));
		if(board.inCheck(-1)) fail();
		
		
		//check by rook
		board = new Board(8, 8, true);
		board.setPieceAt(4, 4, new King(-1));
		board.setPieceAt(4, 7, new Rook(1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(4, 7, null);
		board.setPieceAt(1, 4, new Rook(1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(1, 4, null);
		board.setPieceAt(1, 1, new Rook(1));
		if(board.inCheck(-1)) fail();
		
		board.setPieceAt(1, 1, null);
		board.setPieceAt(4, 7, new Rook(1));
		board.setPieceAt(4, 6, new Pawn(-1));
		if(board.inCheck(-1)) fail();
		
		//check by knight
		board = new Board(8, 8, true);
		board.setPieceAt(4, 4, new King(-1));
		board.setPieceAt(3, 2, new Knight(1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(3, 2, null);
		board.setPieceAt(5, 2, new Knight(1));
		board.setPieceAt(3, 3, new Pawn(-1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(5, 2, null);
		board.setPieceAt(3, 3, new Knight(1));
		if(board.inCheck(-1)) fail();
		
		//check by bishop
		board = new Board(8, 8, true);
		board.setPieceAt(4, 4, new King(-1));
		board.setPieceAt(7, 7, new Bishop(1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(7, 7, null);
		board.setPieceAt(1, 7, new Bishop(1));
		if(!board.inCheck(-1)) fail();
		
		board.setPieceAt(1, 7, null);
		board.setPieceAt(1, 4, new Bishop(1));
		if(board.inCheck(-1)) fail();
		
		board.setPieceAt(1, 4, null);
		board.setPieceAt(7, 7, new Bishop(1));
		board.setPieceAt(6, 6, new Pawn(-1));
		if(board.inCheck(-1)) fail();
		
	}
	
	@Test
	public void testInCheckmate(){
		Board board = new Board(8, 8, true);
		board.setPieceAt(0, 0, new King(1));
		board.setPieceAt(1, 1, new Queen(-1));
		board.setPieceAt(2, 2, new Pawn(-1));
		if(!board.inCheckmate(1)) fail();
		
		board.setPieceAt(1, 1, new Pawn(-1));
		if(board.inCheckmate(1)) fail();
		
		board = new Board(8,8,true);
		board.setPieceAt(0, 0, new King(1));
		board.setPieceAt(1, 1, new Queen(-1));
		board.setPieceAt(2, 2, null);
		if(board.inCheckmate(1)) fail();

		board = new Board(8,8,true);
		board.setPieceAt(0, 0, new King(1));
		board.setPieceAt(2, 2, new Bishop(-1));
		board.setPieceAt(0, 2, new Rook(-1));
		if(board.inCheckmate(1)) fail();
		
		board = new Board(8,8,true);
		board.setPieceAt(4, 4, new King(1));
		board.setPieceAt(3, 3, new Pawn(1));
		board.setPieceAt(3, 4, new Pawn(1));
		board.setPieceAt(4, 3, new Pawn(1));
		board.setPieceAt(2, 2, new Bishop(-1));
		board.setPieceAt(0, 2, new Rook(-1));
		if(board.inCheckmate(1)) fail();
	}
	
	@Test
	public void testStalemate(){
		Board board = new Board(8, 8, true);
		board.setPieceAt(0, 0, new King(1));
		board.setPieceAt(1, 7, new Queen(-1));
		board.setPieceAt(7, 1, new Queen(-1));
		if(!board.inStalemate(Board.WHITE)) fail();
		
		board = new Board(8, 8, true);
		board.setPieceAt(0, 0, new King(1));
		board.setPieceAt(2, 2, new Queen(-1));
		board.setPieceAt(1, 1, new Queen(-1));
		if(board.inStalemate(Board.WHITE)) fail();
		
		board = new Board(8, 8, true);
		board.setPieceAt(0, 0, new King(Board.WHITE));
		board.setPieceAt(0, 1, new Pawn(Board.WHITE));
		board.setPieceAt(0, 2, new Pawn(Board.BLACK));
		board.setPieceAt(1, 7, new Queen(Board.BLACK));
		if(!board.inStalemate(Board.WHITE)) fail();
	}
	
	@Test
	public void putsSelfInCheck(){
		Board board = new Board(8, 8, true);
		board.setPieceAt(0, 1, new King(Board.WHITE));
		board.setPieceAt(1, 1, new Pawn(Board.WHITE));
		board.setPieceAt(2, 1, new Rook(Board.BLACK));
		if(board.tryMove(1,1,1,2)){
			fail();
		}
		
	}
	
}
