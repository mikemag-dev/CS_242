
public class Chess {
	Player[] players;
	Board board;
	Clock clock;
	int cur_player;
	
	public Chess(){
		Board board = new Board(8, 8, false);
		int cur_player = 1;
		
	}
	
	public static void main(String args[]){
		Chess chess_game = new Chess();
		while((!chess_game.board.inCheckmate(chess_game.cur_player) && (!chess_game.board.inStalemate(chess_game.cur_player))) ){
			while(true){
				//get move
				/*if(board.tryMove(cur_x, cur_y, dest_x, dest_y)){
					break;
				}*/
				//chess_game.cur_player = (chess_game.curplayer+1)%2;
			}
		}
		if(chess_game.board.inStalemate(chess_game.cur_player)){
			System.out.print(String.format("Stalemate!"));	
		}
		else{
			System.out.print(String.format("%s has won!", chess_game.cur_player == 1 ? "Black" : "White" ));		
		}
	}
}
