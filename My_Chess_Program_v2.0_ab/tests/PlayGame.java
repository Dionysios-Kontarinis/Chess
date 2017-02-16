package tests;

import common.MyChessGame;
import errors.ChessGameError;

public class PlayGame 
{
	public static void main(String[] args) 
	{	
		MyChessGame game = new MyChessGame();
		
		game.setStartingPosition(1);
		game.printChessBoard();
		
		
		try {
			
			game.playTheGame();
			
			
		} catch (ChessGameError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
