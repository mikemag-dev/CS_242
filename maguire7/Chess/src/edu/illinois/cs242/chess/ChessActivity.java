package edu.illinois.cs242.chess;



import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Class Chess.
 */
public class ChessActivity extends JFrame{
	public boolean whiteAgreesToRestart;
	public boolean blackAgreesToRestart;
	public boolean whiteHasForfeited;
	public boolean blackHasForfeited;
	public int whiteWinCount;
	public int blackWinCount;
	private Label blackWinCountLabel;
	private Label whiteWinCountLabel;
	
	JPanel gameOptionController;
	ChessBoardController chessBoardController;
	
	/**
	 * Instantiates a new chess.
	 */
	public ChessActivity(){
		initUI();
	}
	
	private void initUI() {
		chessBoardController = new ChessBoardController();
		
		gameOptionController = new JPanel();
		gameOptionController.setLayout(new GridLayout(7,1));
		
		JButton whiteAgreesToRestartButton = whiteAgreesToRestartButton();
		JButton blackAgreesToRestartButton = blackAgreesToRestartButton();
		JButton whiteForfeitsButton = whiteForfeitButton();
		JButton blackForfeitsButton = blackForfeitsButton();
		JButton undoButton = undoButton();
		
		blackWinCountLabel = new Label("Black has won " + blackWinCount + " games.");
		whiteWinCountLabel = new Label("White has won " + whiteWinCount + " games.");
		
		gameOptionController.add(whiteForfeitsButton);
		gameOptionController.add(whiteAgreesToRestartButton);
		gameOptionController.add(undoButton);
		gameOptionController.add(blackAgreesToRestartButton);
		gameOptionController.add(blackForfeitsButton);
		gameOptionController.add(whiteWinCountLabel);
		gameOptionController.add(blackWinCountLabel);
		
		
		setTitle("Chess");
		setSize(1200,600);
		setLayout(new GridLayout(1,2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(chessBoardController);
		add(gameOptionController);
	}

	private JButton undoButton() {
		JButton undoButton = new JButton();
		
		undoButton.setText("Undo");		
		
		undoButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				chessBoardController.undoMove();
			}
		});
		return undoButton;
	}

	private JButton whiteAgreesToRestartButton() {
		JButton whiteAgreesToRestartButton = new JButton();
		whiteAgreesToRestartButton.setText("White agrees to restart");

		whiteAgreesToRestartButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				whiteAgreesToRestart = true;
				if(blackAgreesToRestart && whiteAgreesToRestart){
					chessBoardController.restartGame();
					whiteAgreesToRestart = blackAgreesToRestart = false;
				}
			}
		});
		return whiteAgreesToRestartButton;
	}

	private JButton blackAgreesToRestartButton() {
		JButton blackAgreesToRestartButton = new JButton();
		blackAgreesToRestartButton.setText("Black agrees to restart");

		blackAgreesToRestartButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				blackAgreesToRestart = true;
				if(blackAgreesToRestart && whiteAgreesToRestart){
					chessBoardController.restartGame();
					whiteAgreesToRestart = blackAgreesToRestart = false;
				}
			}
		});
		return blackAgreesToRestartButton;
	}

	private JButton blackForfeitsButton() {
		JButton blackForfeitsButton = new JButton();
		
		blackForfeitsButton.setText("Black forfeits");
		
		blackForfeitsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				blackHasForfeited = true;
			}
		});
		return blackForfeitsButton;
	}

	private JButton whiteForfeitButton() {
		JButton whiteForfeitsButton = new JButton();

		whiteForfeitsButton.setText("White forfeits");

		whiteForfeitsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				whiteHasForfeited = true;
			}
		});
		return whiteForfeitsButton;
	}
	
	private int hasWinner() {
		return this.chessBoardController.hasWinner();
	}

	private void updateWinCountLabels() {
		blackWinCountLabel.setText("Black has won " + blackWinCount + " games.");
		whiteWinCountLabel.setText("White has won " + whiteWinCount + " games.");
	}
	
	public static void main(String[] args){
		ChessActivity chessActivity = new ChessActivity();
		chessActivity.setVisible(true);
		int winner = 0;
		while(true){
			winner = chessActivity.hasWinner();
			if(winner == Board.BLACK || chessActivity.whiteHasForfeited){
				chessActivity.whiteHasForfeited = false;
				chessActivity.blackWinCount += 1;
				chessActivity.updateWinCountLabels();
				while(chessActivity.hasWinner() == Board.BLACK || chessActivity.whiteHasForfeited);
			}
			if(winner == Board.WHITE || chessActivity.blackHasForfeited){
				chessActivity.blackHasForfeited = false;
				chessActivity.whiteWinCount += 1;
				chessActivity.updateWinCountLabels();
				while(chessActivity.hasWinner() == Board.WHITE || chessActivity.blackHasForfeited);
			}
		}
	}

	

	
}
