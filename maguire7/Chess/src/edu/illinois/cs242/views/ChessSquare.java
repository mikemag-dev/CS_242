package edu.illinois.cs242.views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ChessSquare extends JButton {
	private int x, y;
	private boolean isSelected;
	private String pieceImageKey;
	
	
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public ChessSquare(int x, int y, String pieceImageKey) {
		/*if((x+y)%2 == 0) setBackground(Color.DARK_GRAY);
		else setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		this.x = x;
		this.y = y;
		this.isSelected = false;
		setPieceImage(pieceImageKey);*/
	}

	public ChessSquare() {
		
	}

	public void setPieceImage(String pieceImageKey) {
		if(pieceImageKey != null){
			this.setIcon(new ImageIcon(pieceImageKey));
			this.pieceImageKey = pieceImageKey; 
		}
	}
	
	public String getPieceImageKey() {
		return this.pieceImageKey;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
