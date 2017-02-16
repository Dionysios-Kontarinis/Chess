package tests;

import common.MyChessGame;
import errors.ChessGameError;


public class Test_Positions_1 
{
	public static void main(String[] args)
	{
		MyChessGame game = new MyChessGame();
		game.clearChessBoard();
		game.human = 2;
		game.computer = 1;
		game.playerMoving = 1;
		game.whitePiecesTotalPower = 6;
		game.blackPiecesTotalPower = 9;

		game.evaluationOfPosition = -3.0;
		
		//There is no "last move" in our new game, so we can say:
		for (int i=0; i<4; i++)
		{
			game.lastMove[i] = -2; 
		}
	
		
		// This variable doesn't have a meaningful value yet...
		for (int i=0; i<4; i++)
		{
			game.bestMoveInPosition[i] = -2; 
		}
		
		//set the white king 
		game.getChessBoard()[0][0] = game.wKing;
		game.setWhiteKingPosition(0, 0);
		
		//set the white bishop
		game.getChessBoard()[4][3] = game.wQBishop;
		
		//set the white knight
		game.getChessBoard()[5][4] = game.wQKnight;
		
		
		//set the black king 
		game.getChessBoard()[7][0] = game.bKing;
		game.setBlackKingPosition(7, 0);
		
		//set the black pawn b 
		game.getChessBoard()[6][1] = game.bpawn2;
		
		//set the black Rook 
		game.getChessBoard()[7][1] = game.bQRook;
		
		//set the black bishop  
		game.getChessBoard()[6][0] = game.bQBishop;
		
		game.printChessBoard();
	
		System.err.println("Black is checked: " + game.isChecked(2));
		System.err.println("Black is mated: " + game.isMated(2));
		System.err.println("Black is stalemated: " + game.isStalemate(2));
	
		try 
		{	
			game.playTheGame();	
		}
		catch (ChessGameError e) 
		{
			System.err.println(e.errorMessage);
		}
	}	
}

