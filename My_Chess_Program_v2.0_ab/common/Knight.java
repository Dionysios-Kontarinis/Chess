package common;

import java.util.Vector;


public class Knight extends Piece 
{
	public Knight(int color)
	{
		//this.inPlay = true;
		this.setValue(3);
		this.setColor(color);
		
		if (color == 1)
		{	
			// TO CHANGE:
			this.setName("wH"); // Temporarily made this way (H: Horse) for alignment of the display reasons.
		}
		else if (color == 2)
		{
			//TO CHANGE:
			this.setName("bH");
			//Temporarily made this way (H: Horse) for alignment of the display reasons.
		}
	}
	
	public Vector findMoves(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		moves.addElement(new Integer(line));
		moves.addElement(new Integer(column));
		
		//FOR THE WHITE KNIGHT:
		if (this.color == 1) 
		{	//check: 	xI
			//			x
			//			F		(I: Initial position, F: Final position)
			if (line>=2 && column>=1 && (board[line-2][column-1]==null || board[line-2][column-1].color == 2) ) 
			{
				moves.addElement(new Integer(line-2));
				moves.addElement(new Integer(column-1));
			}
			//check: 	Ix
			//			 x
			//			 F		(I: Initial position, F: Final position)
			if (line>=2 && column<=6 && (board[line-2][column+1]==null || board[line-2][column+1].color == 2) )
			{
				moves.addElement(new Integer(line-2));
				moves.addElement(new Integer(column+1));
			}
			//check: 	xxI
			//			F		(I: Initial position, F: Final position)
			if (line>=1 && column>=2 && (board[line-1][column-2]==null || board[line-1][column-2].color == 2) )
			{
				moves.addElement(new Integer(line-1));
				moves.addElement(new Integer(column-2));
			}
			//check: 	Ixx
			//			  F		(I: Initial position, F: Final position)
			if (line>=1 && column<=5 && (board[line-1][column+2]==null || board[line-1][column+2].color == 2))
			{
				moves.addElement(new Integer(line-1));
				moves.addElement(new Integer(column+2));
			}
			
			//check: 	Fxx
			//	  		  I  	(I: Initial position, F: Final position)
			if (line<=6 && column>=2 && (board[line+1][column-2]==null || board[line+1][column-2].color == 2))
			{
				moves.addElement(new Integer(line+1));
				moves.addElement(new Integer(column-2));
			}
			//check: 	xxF
			//			I  	(I: Initial position, F: Final position)
			if (line<=6 && column<=5 && (board[line+1][column+2]==null || board[line+1][column+2].color == 2))
			{
				moves.addElement(new Integer(line+1));
				moves.addElement(new Integer(column+2));
			}
			//check:	 Fx 
			// 			  x
			//	  		  I  	(I: Initial position, F: Final position)
			if (line<=5 && column>=1 && (board[line+2][column-1]==null || board[line+2][column-1].color == 2))
			{
				moves.addElement(new Integer(line+2));
				moves.addElement(new Integer(column-1));
			}
			//check:	xF
			// 			x
			//			I  		(I: Initial position, F: Final position)
			if (line<=5 && column<=6 && (board[line+2][column+1]==null || board[line+2][column+1].color == 2))
			{
				moves.addElement(new Integer(line+2));
				moves.addElement(new Integer(column+1));
			}
		}
		//FOR THE BLACK KNIGHT:
		else if (this.color == 2) 
		{	//check: 	xI
			//			x
			//			F		(I: Initial position, F: Final position)
			if (line>=2 && column>=1 && (board[line-2][column-1]==null || board[line-2][column-1].color == 1) )
			{
				moves.addElement(new Integer(line-2));
				moves.addElement(new Integer(column-1));
			}
			//check: 	Ix
			//			 x
			//			 F		(I: Initial position, F: Final position)
			if (line>=2 && column<=6 && (board[line-2][column+1]==null || board[line-2][column+1].color == 1) )
			{
				moves.addElement(new Integer(line-2));
				moves.addElement(new Integer(column+1));
			}
			//check: 	xxI
			//			F		(I: Initial position, F: Final position)
			if (line>=1 && column>=2 && (board[line-1][column-2]==null || board[line-1][column-2].color == 1) )
			{
				moves.addElement(new Integer(line-1));
				moves.addElement(new Integer(column-2));
			}
			//check: 	Ixx
			//			  F		(I: Initial position, F: Final position)
			if (line>=1 && column<=5 && (board[line-1][column+2]==null || board[line-1][column+2].color == 1))
			{
				moves.addElement(new Integer(line-1));
				moves.addElement(new Integer(column+2));
			}
			
			//check: 	Fxx
			//	  		  I  	(I: Initial position, F: Final position)
			if (line<=6 && column>=2 && (board[line+1][column-2]==null || board[line+1][column-2].color == 1))
			{
				moves.addElement(new Integer(line+1));
				moves.addElement(new Integer(column-2));
			}
			//check: 	xxF
			//			I  	(I: Initial position, F: Final position)
			if (line<=6 && column<=5 && (board[line+1][column+2]==null || board[line+1][column+2].color == 1))
			{
				moves.addElement(new Integer(line+1));
				moves.addElement(new Integer(column+2));
			}
			//check:	 Fx 
			// 			  x
			//	  		  I  	(I: Initial position, F: Final position)
			if (line<=5 && column>=1 && (board[line+2][column-1]==null || board[line+2][column-1].color == 1))
			{
				moves.addElement(new Integer(line+2));
				moves.addElement(new Integer(column-1));
			}
			//check:	xF
			// 			x
			//			I  		(I: Initial position, F: Final position)
			if (line<=5 && column<=6 && (board[line+2][column+1]==null || board[line+2][column+1].color == 1))
			{
				moves.addElement(new Integer(line+2));
				moves.addElement(new Integer(column+1));
			}
		}
		
		if (moves.size() <= 2)	 //The Knight has no valid moves...
		{
			return null;
		}
		
		return moves;
	}
	
	/*
	public Piece clonePiece()
	{
		Knight clonedKnight = new Knight(this.color);
		
		return clonedKnight;
	}
	*/
}
