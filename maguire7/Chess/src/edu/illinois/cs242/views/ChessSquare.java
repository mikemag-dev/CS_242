package edu.illinois.cs242.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ChessSquare extends JButton {
	private int x, y;
	
	
	public ChessSquare(int i, String pieceImageKey) {
		if(i == 0) setBackground(Color.DARK_GRAY);
		else setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		setPieceImage(pieceImageKey);
		initButtonListener();
	}

	public void setPieceImage(String pieceImageKey) {
		if(pieceImageKey != null) this.setIcon(new ImageIcon(pieceImageKey));
	}

	private void initButtonListener() {
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
