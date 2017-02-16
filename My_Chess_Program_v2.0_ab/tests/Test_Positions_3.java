package tests;

import common.MyChessGame;
import errors.ChessGameError;


public class Test_Positions_3 
{
	public static void main(String[] args)
	{
		MyChessGame game = new MyChessGame();
		game.clearChessBoard();
		game.human = 2;
		game.computer = 1;
		game.playerMoving = 1;
		game.whitePiecesTotalPower = 11;
		game.blackPiecesTotalPower = 9;

		game.evaluationOfPosition = 2;
		
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
		game.getChessBoard()[1][0] = game.wKing;
		game.setWhiteKingPosition(1, 0);
		
		//set the white queen 
		game.getChessBoard()[6][2] = game.wQueen;
		
		//set the white rook
		game.getChessBoard()[7][3] = game.wQRook;
		
		//set the white knight
		game.getChessBoard()[5][4] = game.wQKnight;
		
		// white pawns
		game.getChessBoard()[2][0] = game.wpawn1;
		game.getChessBoard()[1][1] = game.wpawn2;
		game.getChessBoard()[5][2] = game.wpawn3;
		
		//set the black king 
		game.getChessBoard()[7][0] = game.bKing;
		game.setBlackKingPosition(7, 0);
		
		//set the black queen 
		game.getChessBoard()[0][2] = game.bQueen;
		
		//set the black Qrook 
		game.getChessBoard()[7][1] = game.bQRook;
		
		//set the black KRook 
		game.getChessBoard()[0][4] = game.bKRook;
		
		//set the black Qbishop  
		game.getChessBoard()[6][0] = game.bQBishop;
		
		//set the black Qknight 
		game.getChessBoard()[5][3] = game.bQKnight;
		
		//set the black pawns
		game.getChessBoard()[3][0] = game.bpawn1;
		game.getChessBoard()[6][1] = game.bpawn2;
		
		
		
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

