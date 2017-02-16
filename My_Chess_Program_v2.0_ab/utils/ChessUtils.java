package utils;

import java.util.Vector;
import common.Piece;
import errors.ChessGameError;


public class ChessUtils 
{
	/**
	 * This method is used (during the whole game) to check whether the King is exposed or not.
	 * It can serve other purposes too...
	 * @param matrixA
	 * @param matrixB
	 * @return
	 * @throws ChessGameError
	 */
	public static boolean compareCoordinates(int[] matrixA, int[] matrixB)
	{
		if (matrixA[0]==matrixB[0] && matrixA[1]==matrixB[1])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method is used by the Bishop and Queen classes. 
	 * It helps when searching for those pieces' moves at a particular position.
	 * @param board
	 * @param line
	 * @param column
	 * @return
	 */
	public static Vector findMovesInDiagonals(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		moves.addElement(new Integer(line));
		moves.addElement(new Integer(column));	//The piece's initial square. 
		
		int aux_line = 0;
		int aux_column = 0;
		
		// WHITE PIECE:
		if (board[line][column].getColor() == 1)
		{
			// up and right...
			aux_line = line;
			aux_column = column;
			while (aux_line<7 && aux_column<7)
			{
				if (board[++aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// down and left...
			aux_line = line;
			aux_column = column;
			while (aux_line>0 && aux_column>0)
			{
				if (board[--aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// up and left...
			aux_line = line;
			aux_column = column;
			while (aux_line<7 && aux_column>0)
			{
				if (board[++aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// down and right...
			aux_line = line;
			aux_column = column;
			while (aux_line>0 && aux_column<7)
			{
				if (board[--aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
		}
		// BLACK PIECE:
		else
		{
			//up and right...
			aux_line = line;
			aux_column = column;
			while (aux_line<7 && aux_column<7)
			{
				if (board[++aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			// down and left...
			aux_line = line;
			aux_column = column;
			while (aux_line>0 && aux_column>0)
			{
				if (board[--aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			// up and left...
			aux_line = line;
			aux_column = column;
			while (aux_line<7 && aux_column>0)
			{
				if (board[++aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			//down and right...
			aux_line = line;
			aux_column = column;
			while (aux_line>0 && aux_column<7)
			{
				if (board[--aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
		}
			
		return moves;
	}
	
	/**
	 * This method is used by the Rook and Queen classes. 
	 * It helps when searching for those pieces' moves at a particular position.
	 * @param board
	 * @param line
	 * @param column
	 * @return
	 */
	public static Vector findMovesInRowsAndColumns(Piece[][] board, int line, int column)
	{
		Vector moves = new Vector();
		moves.addElement(new Integer(line));
		moves.addElement(new Integer(column));
		
		int aux_line = 0;
		int aux_column = 0;
		
		// WHITE PIECE:
		if (board[line][column].getColor() == 1)
		{
			// up...
			aux_line = line;
			aux_column = column;
			while (aux_line<7)
			{
				if (board[++aux_line][aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (chessBrd.getBoard()[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// down...
			aux_line = line;
			aux_column = column;
			while (aux_line>0)
			{
				if (board[--aux_line][aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// left...
			aux_line = line;
			aux_column = column;
			while (aux_column>0)
			{
				if (board[aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
			// right...
			aux_line = line;
			aux_column = column;
			while (aux_column<7)
			{
				if (board[aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 2)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 1)
				{
					break;
				}
			}
		}
		// BLACK PIECE:
		else
		{
			// up...
			aux_line = line;
			aux_column = column;
			while (aux_line<7)
			{
				if (board[++aux_line][aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			// down...
			aux_line = line;
			aux_column = column;
			while (aux_line>0)
			{
				if (board[--aux_line][aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			// left...
			aux_line = line;
			aux_column = column;
			while (aux_column>0)
			{
				if (board[aux_line][--aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
			// right...
			aux_line = line;
			aux_column = column;
			while (aux_column<7)
			{
				if (board[aux_line][++aux_column]==null)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
				}
				else if (board[aux_line][aux_column].getColor() == 1)
				{
					moves.addElement(new Integer(aux_line));
					moves.addElement(new Integer(aux_column));
					break;
				}
				else //if (board[aux_line][aux_column].getColor() == 2)
				{
					break;
				}
			}
		}
		
		return moves;
	}
}
