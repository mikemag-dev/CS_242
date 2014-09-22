package edu.illinois.cs242.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import edu.illinois.cs242.chess.Board;
import edu.illinois.cs242.pieces.Charger;
import edu.illinois.cs242.pieces.Pawn;


public class ChargerTest {

	@Test
	public void takeBothColors() {
		Board board = new Board(8, 8, true);
		board.setPieceAt(5, 5, new Pawn(Board.WHITE));
		board.setPieceAt(4, 4, new Pawn(Board.BLACK) );
		board.setPieceAt(3, 3, new Charger(Board.WHITE));
		
		if(!board.tryMove(3,3,4,4)){
			fail();
		}
		
		if(!board.tryMove(4,4,5,5)){
			fail();
		}
	
	}

	
	@Test
	public void testJumpOver() {
		Board board = new Board(8, 8, true);
		board.setPieceAt(5, 5, new Charger(Board.BLACK));
		board.setPieceAt(4, 2, new Pawn(Board.WHITE) );
		board.setPieceAt(3, 3, new Pawn(Board.WHITE));
		
		if(board.tryMove(5,5,3,3)){
			fail();
		}	
		
		if(!board.tryMove(5,5,4,2)){
			fail();
		}
	}
	
}
