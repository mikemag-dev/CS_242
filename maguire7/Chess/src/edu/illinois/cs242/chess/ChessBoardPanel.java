package edu.illinois.cs242.chess;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.illinois.cs242.views.ChessSquare;

public class ChessBoardPanel extends JPanel {
	private Board board;
	private int originX, originY, destX, destY;
	private int cur_player;
	
	
	public ChessBoardPanel(Board board){
		this.board = board;
		originX = originY = destX = destY = -1;
		cur_player = Board.WHITE;
		initChessboardPanel();
	}

	private void initChessboardPanel() {
		
		JPanel chessboardPanel = new JPanel();
		
		//chessboardPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		chessboardPanel.setLayout(new GridLayout(board.width, board.height));
		chessboardPanel.setSize(500, 500);
		
		for(int i = 0; i < board.width; i++){
			for(int j = 0; j < board.height; j++){
				String pieceImageKey = board.getPieceAt(j, i) != null ? board.getPieceAt(j, i).getPieceImageKey() : null;
				ChessSquare squareButton = new ChessSquare(i, j, pieceImageKey);
				squareButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						edu.illinois.cs242.pieces.Piece pieceAtSelected = board.getPieceAt(squareButton.getX(), squareButton.getY());
						//no piece at selected Square
						if(pieceAtSelected == null) return; 
						if(pieceAtSelected.getColor() == cur_player){
							if(originX == -1){
								originX = squareButton.getX();
								originY = squareButton.getY();
							}
							else{
								destX = squareButton.getX();
								destY = squareButton.getY();
								submitMove();
							}
						}
					}
				});
				chessboardPanel.add(squareButton);
				
			}
		}
		this.add(chessboardPanel);
		
	}
	
	private void submitMove() {
		if(originX == originY && destX == destY){
			
		}
	}
}
