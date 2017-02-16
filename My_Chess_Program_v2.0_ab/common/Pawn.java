package common;

import java.util.Vector;


public class Pawn extends Piece 
{
	/**
	 * Constructor with a String parameter.
	 * It creates a new Pawn object and sets its color to "white" or "black".
	 * @param color
	 */
	public Pawn(int color)
	{
		//this.inPlay = true;
		this.setValue(1);
		this.setColor(color);
		
		if (color == 1)
		{
			this.setName("wp");
		}
		else if (color == 2)
		{
			this.setName("bp");
		}
	}
	
	/**
	 *Implements the findMoves method for the Pawn class.
	 *It finds the respective pawn's moves (not taking into account whether the King is exposed or not).
	 *
	 *FORMAT IN WHICH THE VALID MOVES ARE RERESENTED:
	 *
	 *A Vector consisting of Arrays of integers (The length of all arrays is 2).
	 *The first array contains the initial coordinates of the piece on the chessboard.
	 *Each other array contains the coordinates of a possible move.
	 *If the Vector has only 1 element, then that pawn has no moves in the position.
	 *e.g.:	<[3,3] , [3,4] , [3,5]>
	 */
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		
		moves.addElement(new Integer(line));		// first add the piece's initial position coordinates.
		moves.addElement(new Integer(column));
		
		// FIRST CASE: A WHITE PAWN
		if (this.color == 1) 
		{
			/////////////////////////////////////////////
			// FIRST CHECK THE PAWN'S FORWARD MOVES:
			/////////////////////////////////////////////
			
			// Check if it can move 1 square:
			if (board[line+1][column] == null)
			{
				moves.addElement(new Integer(line+1));
				moves.addElement(new Integer(column));
				// Additionally, if the pawn has not been moved:
				if (line == 1)
				{
					// Check if it can move 2 squares ahead:
					if (board[3][column] == null)
					{
						moves.addElement(new Integer(3));
						moves.addElement(new Integer(column));
					}
				}
			}
			
			/////////////////////////////////////////
			// THEN CHECK THE PAWN'S PARSING MOVES:
			/////////////////////////////////////////
			
			if (column != 7)
			{
				if (board[line+1][column+1] != null) 
				{
					if (board[line+1][column+1].getColor() == 2) 
					{
						moves.addElement(new Integer(line+1));
						moves.addElement(new Integer(column+1));
					}
				}
			}
			if (column != 0)
			{	
				if (board[line+1][column-1] != null) 
				{
					if (board[line+1][column-1].getColor() == 2) 
					{
						moves.addElement(new Integer(line+1));
						moves.addElement(new Integer(column-1));
					}
				}	
			}
		}

		// SECOND CASE: A BLACK PAWN
		else
		{
			/////////////////////////////////////////////
			// FIRST CHECK THE PAWN'S FORWARD MOVES:
			/////////////////////////////////////////////
			
			// Check if it can move 1 square:
			if (board[line-1][column] == null)
			{
				moves.addElement(new Integer(line-1));
				moves.addElement(new Integer(column));
				// Additionally, if the pawn has not been moved:
				if (line == 6)
				{
					// Check if it can move 2 squares ahead:
					if (board[4][column] == null)
					{
						moves.addElement(new Integer(4));
						moves.addElement(new Integer(column));
					}
				}
			}
			
			/////////////////////////////////////////
			// THEN CHECK THE PAWN'S PARSING MOVES:
			/////////////////////////////////////////
			
			if (column != 7)
			{
				if (board[line-1][column+1] != null) 
				{
					if (board[line-1][column+1].getColor() == 1) 
					{
						moves.addElement(new Integer(line-1));
						moves.addElement(new Integer(column+1));
					}
				}
			}
			if (column != 0)
			{	
				if (board[line-1][column-1] != null) 
				{
					if (board[line-1][column-1].getColor() == 1) 
					{
						moves.addElement(new Integer(line-1));
						moves.addElement(new Integer(column-1));
					}
				}	
			}
		}
		
		if (moves.size() <= 2)	 //The pawn has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		Pawn clonedPawn = new Pawn(this.color);
		
		return clonedPawn;
	}
	*/
}
