package common;

import java.util.Vector;
import utils.ChessUtils;


public class Queen extends Piece 
{
	public Queen(int color)
	{
		//this.inPlay = true;
		this.setValue(9);
		this.setColor(color);
		
		if (color == 1)
		{
			this.setName("wQ");
		}
		else if (color == 2)
		{
			this.setName("bQ");
		}
	}
	
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		
		Vector diagonals = ChessUtils.findMovesInDiagonals(board, line, column);
		Vector rowsAndColumns = ChessUtils.findMovesInRowsAndColumns(board, line, column);
		
		for(int i=0; i<diagonals.size(); i++)
		{
			moves.addElement(diagonals.elementAt(i));
		}
		
		// Begin with i=2 since I don't want to put (AGAIN) the initial coordinates of the piece in the moves Vector!!!
		/////////////////////////////////////////////////////////////////////////////////
		// HERE INITIAL VALUE OF INT J IS 2 AND NOT 1 (AS IN INITIAL VERSION) ANYMORE!!! 
		/////////////////////////////////////////////////////////////////////////////////
		
		for(int i=2; i<rowsAndColumns.size(); i++)
		{
			moves.addElement(rowsAndColumns.elementAt(i));
		}
		
		if (moves.size() <= 2)	 //The Queen has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		Queen clonedQueen = new Queen(this.color);
		
		return clonedQueen;
	}
	*/
}
