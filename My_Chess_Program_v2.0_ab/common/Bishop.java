package common;

import java.util.Vector;
import utils.ChessUtils;


public class Bishop extends Piece 
{	
	public Bishop(int color)
	{
		//this.inPlay = true;
		this.setValue(3);
		this.setColor(color);
		
		if (color == 1)
		{
			this.setName("wB");
		}
		else if (color == 2)
		{
			this.setName("bB");
		}
	}
	
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = ChessUtils.findMovesInDiagonals(board, line, column);
		
		if (moves.size() <= 2)	 //The Bishop has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		Bishop clonedBishop = new Bishop(this.color);
		
		return clonedBishop;
	}
	*/
}

