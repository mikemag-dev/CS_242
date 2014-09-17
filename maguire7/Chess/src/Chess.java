import javax.swing.*;

/**
 * The Class Chess.
 */
public class Chess {
	
	/** The board. */
	Board board;
	
	/** The curPlayer. */
	int curPlayer;
	
	/**
	 * Instantiates a new chess.
	 */
	public Chess(){
		boolean doNotPopulate = false;
		Board board = new Board(8, 8, doNotPopulate);
		int curPlayer = Board.WHITE;
		
	}
	
	private static void createAndShowChessboard(){
		JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Chess");
        frame.setSize(500, 500);
        JLabel label = new JLabel("Hello World");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.getContentPane().add(label);

        //Display the window.
        //frame.pack();
        //frame.setVisible(true);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/*public static void main(String args[]){
		Chess chessGame = new Chess();
		createAndShowChessboard();
	}*/
}
