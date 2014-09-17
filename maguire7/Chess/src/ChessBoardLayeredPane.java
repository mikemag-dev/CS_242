import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class ChessBoardLayeredPane extends JPanel {
	private Color[] gridColors = { Color.LIGHT_GRAY, Color.WHITE};
	
	private JLayeredPane layeredPane;
	
	public ChessBoardLayeredPane(int width, int height){
		setLayout(new GridLayout(width, height));
		
		for(int j = 0; j<height; j++){
			for(int i = 0; i<width; i++){
	            JLabel label = createColoredLabel(gridColors[(i+j)%2]);
					            
				//pawn logic
				if(j == 1) label.setIcon(new ImageIcon("res/white_pawn.png"));

				if(j == height-2) label.setIcon(new ImageIcon("res/black_pawn.png"));
				
				//white piece logic
				if((j == 0 && i == 0) || (j == 0 && i == width-1)) label.setIcon(new ImageIcon("res/white_rook.png"));
				if((j == 0 && i == 1) || (j == 0 && i == width-2)) label.setIcon(new ImageIcon("res/white_knight.png"));
				if((j == 0 && i == 2) || (j == 0 && i == width-3)) label.setIcon(new ImageIcon("res/white_bishop.png"));
				if(j == 0 && i == 3) label.setIcon(new ImageIcon("res/white_queen.png"));
				if(j == 0 && i == 4) label.setIcon(new ImageIcon("res/white_king.png"));
				
				
				//black piece logic
				if((j == height-1 && i == 0) || (j == height-1 && i == width-1)) label.setIcon(new ImageIcon("res/black_rook.png"));  
				if((j == height-1 && i == 1) || (j == height-1 && i == width-2)) label.setIcon(new ImageIcon("res/black_knight.png"));
				if((j == height-1 && i == 2) || (j == height-1 && i == width-3)) label.setIcon(new ImageIcon("res/black_bishop.png"));  
				if(j == height-1 && i == 3) label.setIcon(new ImageIcon("res/black_queen.png"));
				if(j == height-1 && i == 4)	label.setIcon(new ImageIcon("res/black_king.png")); 
		        add(label, new Integer(i));
			}
		}
		
		/*for (int i = 0; i < width*height; i++) {
            JLabel label = createColoredLabel(gridColors[(i+i/8)%2]);
            label.setIcon(new ImageIcon("res/black_bishop.png"));
            add(label, new Integer(i));
        }*/
	}
	
	
	private JLabel createColoredLabel(Color squareColor) {
		JLabel label = new JLabel();
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(squareColor);
		label.setForeground(Color.red);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setPreferredSize(new Dimension(50, 50));
		return label;
	}
	   
	   private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Chess");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        JComponent chessBoardPane = new ChessBoardLayeredPane(8,8);
	        chessBoardPane.setOpaque(true); //content panes must be opaque
	        frame.setContentPane(chessBoardPane);

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	   
	   public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
}
