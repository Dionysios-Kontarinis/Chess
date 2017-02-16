package common;

import java.util.Vector;


public class King extends Piece 
{
	public King(int color)
	{
		//this.inPlay = true;
		this.setValue(1000000);
		this.setColor(color);
		
		if (color == 1)
		{
			this.setName("wK");
		}
		else if (color == 2)
		{
			this.setName("bK");
		}
	}
	
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		moves.addElement(new Integer(line));
		moves.addElement(new Integer(column));
		
		if (this.getColor() == 1)
		{
			if (line>0 && column>0)
			{
				if (board[line-1][column-1]==null || board[line-1][column-1].getColor() == 2)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column-1));
				}
			}
			if (line>0)
			{	
				if (board[line-1][column]==null || board[line-1][column].getColor() == 2)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column));
				}
			}
			if (line>0 && column<7)
			{	
				if (board[line-1][column+1]==null || board[line-1][column+1].getColor() == 2)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column+1));
				}
			}
			if (column>0)
			{
				if (board[line][column-1]==null || board[line][column-1].getColor() == 2)
				{
					moves.addElement(new Integer(line));
					moves.addElement(new Integer(column-1));
				}
			}
			if (column<7)
			{	
				if (board[line][column+1]==null || board[line][column+1].getColor() == 2)
				{
					moves.addElement(new Integer(line));
					moves.addElement(new Integer(column+1));
				}
			}
			if (line<7 && column>0)
			{	
				if (board[line+1][column-1]==null || board[line+1][column-1].getColor() == 2)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column-1));
				}
			}
			if (line<7)
			{	
				if (board[line+1][column]==null || board[line+1][column].getColor() == 2)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column));
				}
			}
			if (line<7 && column<7)
			{	
				if (board[line+1][column+1]==null || board[line+1][column+1].getColor() == 2)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column+1));
				}
			}
		}
		else //for the black King...
		{
			if (line>0 && column>0)
			{
				if (board[line-1][column-1]==null || board[line-1][column-1].getColor() == 1)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column-1));
				}
			}
			if (line>0)
			{	
				if (board[line-1][column]==null || board[line-1][column].getColor() == 1)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column));
				}
			}
			if (line>0 && column<7)
			{	
				if (board[line-1][column+1]==null || board[line-1][column+1].getColor() == 1)
				{
					moves.addElement(new Integer(line-1));
					moves.addElement(new Integer(column+1));
				}
			}
			if (column>0)
			{
				if (board[line][column-1]==null || board[line][column-1].getColor() == 1)
				{
					moves.addElement(new Integer(line));
					moves.addElement(new Integer(column-1));
				}
			}
			if (column<7)
			{	
				if (board[line][column+1]==null || board[line][column+1].getColor() == 1)
				{
					moves.addElement(new Integer(line));
					moves.addElement(new Integer(column+1));
				}
			}
			if (line<7 && column>0)
			{	
				if (board[line+1][column-1]==null || board[line+1][column-1].getColor() == 1)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column-1));
				}
			}
			if (line<7)
			{	
				if (board[line+1][column]==null || board[line+1][column].getColor() == 1)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column));
				}
			}
			if (line<7 && column<7)
			{	
				if (board[line+1][column+1]==null || board[line+1][column+1].getColor() == 1)
				{
					moves.addElement(new Integer(line+1));
					moves.addElement(new Integer(column+1));
				}
			}
		}
		
		if (moves.size() <= 2)	 //The King has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		King clonedKing = new King(this.color);
		
		return clonedKing;
	}
	*/
}
