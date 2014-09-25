package edu.illinois.cs242.chess;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.illinois.cs242.models.SubmittedMove;
import edu.illinois.cs242.pieces.ChessPiece;

public class ChessBoardController extends JPanel {
	private Board board;
	private int originX, originY, destX, destY;
	private int curPlayer;
	private ArrayList<JButton> boardButtons;
	private Stack<SubmittedMove> moveStack;
	
	public ChessBoardController(){
		super();
		initChessboardController();
	}

	public void restartGame(){
		board = new Board(8, 8, false);
		originX = originY = destX = destY = -1;
		curPlayer = Board.WHITE;
		moveStack = new Stack<>();
		updateBoardView();
	}

	private void initClassVariables() {
		board = new Board(8, 8, false);
		originX = originY = destX = destY = -1;
		curPlayer = Board.WHITE;
		boardButtons = new ArrayList<>(board.width*board.height);
		moveStack = new Stack<>();

	}

	public int hasWinner(){
		if(board.inCheck(Board.BLACK) && board.inCheckmate(Board.BLACK))
			return Board.WHITE;
		if(board.inCheck(Board.WHITE) && board.inCheckmate(Board.WHITE))
			return Board.BLACK;
		return 0;
	}
	
	private void initChessboardController() {
		initClassVariables();
		
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setLayout(new GridLayout(board.width, board.height));
		
		initChessSquareButtons();		
	}

	private void initChessSquareButtons() {
		for(int i = 0; i < board.width; i++){
			for(int j = 0; j < board.height; j++){
				JButton chessSquare = new JButton();
				ChessPiece pieceAtSquare = board.getPieceAt(j, i);
				if(pieceAtSquare != null)
					chessSquare.setIcon(new ImageIcon(pieceAtSquare.getPieceImageKey()));
				
				int x = j;
				int y = i;
				chessSquare.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						ChessPiece pieceAtSelected = board.getPieceAt(x, y);
						String playerColor = curPlayer == Board.WHITE ? "White " : "Black ";
						System.out.print(playerColor + "selected square " + x + ", " + y + ". ");
						
						if(originX == -1)
							if(pieceAtSelected != null && pieceAtSelected.getColor() == curPlayer) 
								updateOrigin(x,y);
							else
								System.out.println("Must select one of your own pieces.");
						else
							updateDest(x,y);
							
					}

					private void updateOrigin(int x, int y) {
						originX = x;
						originY = y;
						System.out.println("Origin set at " + x + ", " + y);
					}

					private void updateDest(int x, int y) {
						destX = x;
						destY = y;
						System.out.println("Dest set at " + x + ", " + y);
						submitMove();
					}
				});
				boardButtons.add(chessSquare);
				add(chessSquare);
				
			}
		}
	}
	
	private JButton getChessSquareAt(int x, int y){
		return boardButtons.get(y*board.width+x);
	}
	
	private void submitMove() {
		if(board.canMove(originX, originY, destX, destY)){
			curPlayer *= -1;
			addToMoveStack();
			board.tryMove(originX, originY, destX, destY);
			updateBoardView();
		}
		originX = originY = destX = destY = -1;
	}

	private void addToMoveStack() {
		ChessPiece pieceMoved = board.getPieceAt(originX, originY);
		ChessPiece pieceTaken = board.getPieceAt(destX, destY);
		SubmittedMove moveToPush = new SubmittedMove(originX, originY, destX, destY, pieceMoved, pieceTaken);
		moveStack.push(moveToPush);
	}

	public void undoMove() {
		if(moveStack.isEmpty()) return;
		
		SubmittedMove moveToUndo = moveStack.pop();
		curPlayer *= -1;
		
		int returnToX = moveToUndo.getOriginX();
		int returnToY = moveToUndo.getOriginY();
		int movedToX = moveToUndo.getDestX();
		int movedToY = moveToUndo.getDestY();
		
		ChessPiece pieceToMoveBack = moveToUndo.getPieceMoved();
		ChessPiece pieceToRevive = moveToUndo.getPieceTaken();
		
		board.setPieceAt(returnToX, returnToY, pieceToMoveBack);
		board.setPieceAt(movedToX, movedToY, pieceToRevive);
		
		updateBoardView();
	}
	
	private void updateBoardView() {
		for(int i = 0; i < board.width; i++){
			for(int j = 0; j < board.height; j++){
				ChessPiece pieceAtSelected = board.getPieceAt(j, i);
				JButton chessSquare = this.getChessSquareAt(j, i);
				if(pieceAtSelected != null)
					chessSquare.setIcon(new ImageIcon(pieceAtSelected.getPieceImageKey()));
				else
					chessSquare.setIcon(null);

				}
		}
	}
}
