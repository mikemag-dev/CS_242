package edu.illinois.cs242.chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.illinois.cs242.views.ChessSquare;

public class ChessBoard extends JFrame {
	
	JPanel chessboardPanel;
	
	public ChessBoard(Board board){
		initChessBoard(board);
	}

	private void initChessBoard(Board board) {
		
		JPanel chessboardPanel = new JPanel();
		
		//chessboardPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		chessboardPanel.setLayout(new GridLayout(board.width, board.height));
		chessboardPanel.setSize(300, 300);
		chessboardPanel.setMaximumSize(new Dimension(300,300));
		
		for(int i = 0; i < board.width; i++){
			for(int j = 0; j < board.height; j++){
				String pieceImageKey = board.getPieceAt(j, i) != null ? board.getPieceAt(j, i).getPieceImageKey() : null;
				ChessSquare squareButton = new ChessSquare((i+j)%2, pieceImageKey);
				chessboardPanel.add(squareButton);
			}
		}
		this.add(chessboardPanel);
		
		setTitle("Chess");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
