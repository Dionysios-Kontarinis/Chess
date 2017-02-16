package common;

import java.util.Vector;
import utils.ChessUtils;


public class Rook extends Piece 
{
	public Rook(int color)
	{
		//this.inPlay = true;
		this.setValue(5);
		this.setColor(color);
		
		if (color == 1)
		{
			this.setName("wR");
		}
		else if (color == 2)
		{
			this.setName("bR");
		}
	}
	
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = ChessUtils.findMovesInRowsAndColumns(board, line, column);
		
		if (moves.size() <= 2)	 //The Rook has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		Rook clonedRook = new Rook(this.color);
		
		return clonedRook;
	}
	*/
}
