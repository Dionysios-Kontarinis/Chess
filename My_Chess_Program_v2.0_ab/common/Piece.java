package common;

import java.util.Vector;


/**
 * This is the parent class of every class that represents a particular chess-piece.
 * All these classes (like Pawn, Knight etc) inherit from this class.
 * @author Dionisis
 */
public abstract class Piece 
{
	public int value;
	public int color;
	public String name;
	// public boolean inPlay;
	
	
	
	// No Constructor...

	
	
	/**
	 * It returns the piece's color.
	 * @return
	 */
	public int getColor()
	{
		return this.color;
	}
	
	/**
	 * It sets the piece's color.
	 * @return
	 */
	public void setColor(int color)
	{
		this.color = color;
	}
	
	
	/**
	 * It returns the piece's name. 
	 * (e.g. "wR" meaning: "white Rook", or "bQ" meaning : "blackQueen).
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * It sets the piece's name. 
	 * (e.g. "wR" meaning: "white Rook", or "bQ" meaning : "blackQueen).
	 * @return
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	/**
	 * It returns the piece's value. 
	 * (e.g. the Rook's value should be 5, the Knight's value should be 3 etc.).
	 * @return
	 */
	public int getValue()
	{
		return this.value;
	}
	
	/**
	 * It sets the piece's value. 
	 * (e.g. the Rook's value should be 5, the Knight's value should be 3 etc.).
	 * @return
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
	
	
	/**
	 * This method finds all the moves a particular piece can make at a given situation on the chessboard.
	 * It does not check if those moves expose the King, so its call has to be followed by a "validation process"
	 * in order to decide which of those moves are REALLY valid at the given situation.  
	 * IT IS OVERRIDEN IN ALL THE PARTICULAR "CHESS-PIECE" CLASSES!
	 * @param chessBrd
	 * @param line
	 * @param column
	 * @return
	 */
	public abstract Vector findMoves(Piece[][] board, int line, int column);
	
	
	/*
	public abstract Piece clonePiece();
	If we use static variables for the pieces, probably this method is not necessary.
	*/
	
}






