package common;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import errors.ChessGameError;
import utils.ChessUtils;


public class MyChessGame 
{
	//////////////////////////////////////   FIELDS   //////////////////////////////////////
	
	/**
	 * Defines the color of pieces of the human player. 
	 * "1" means "white" , "2" means "black"
	 */
	public int human;	
	
	/**
	 * Defines the color of pieces of the computer.
	 * "1" means "white" , "2" means "black"
	 */
	public int computer;	
	
	/**
	 * Shows who is currently playing.
	 * "1" means "white" , "2" means "black"
	 */
	public int playerMoving;	
	
	
	/**
	 * The "chessboard" that corresponds to a specific "chessgame".
	 */
	public Piece[][] chessBoard;
	
	
	/**
	 * The chessboard's size is always 8x8.
	 */
	public static final int BOARD_SIZE = 8;
	
	
	// WHITE PIECES:
	public static Pawn wpawn1 = new Pawn(1);
	public static Pawn wpawn2 = new Pawn(1);
	public static Pawn wpawn3 = new Pawn(1);
	public static Pawn wpawn4 = new Pawn(1);
	public static Pawn wpawn5 = new Pawn(1);
	public static Pawn wpawn6 = new Pawn(1);
	public static Pawn wpawn7 = new Pawn(1);
	public static Pawn wpawn8 = new Pawn(1);
	
	public static Rook wQRook = new Rook(1);
	public static Rook wKRook = new Rook(1);
	public static Knight wQKnight = new Knight(1);
	public static Knight wKKnight = new Knight(1);
	public static Bishop wQBishop = new Bishop(1);
	public static Bishop wKBishop = new Bishop(1);
	public static Queen wQueen = new Queen(1);
	public static King wKing = new King(1);
	
	// BLACK PIECES:
	public static Pawn bpawn1 = new Pawn(2);
	public static Pawn bpawn2 = new Pawn(2);
	public static Pawn bpawn3 = new Pawn(2);
	public static Pawn bpawn4 = new Pawn(2);
	public static Pawn bpawn5 = new Pawn(2);
	public static Pawn bpawn6 = new Pawn(2);
	public static Pawn bpawn7 = new Pawn(2);
	public static Pawn bpawn8 = new Pawn(2);
	
	public static Rook bQRook = new Rook(2);
	public static Rook bKRook = new Rook(2);
	public static Knight bQKnight = new Knight(2);
	public static Knight bKKnight = new Knight(2);
	public static Bishop bQBishop = new Bishop(2);
	public static Bishop bKBishop = new Bishop(2);
	public static Queen bQueen = new Queen(2);
	public static King bKing = new King(2);
	
	
	/**
	 * We want to be able to know whether the Kings have been moved during the course of
	 * the game or not. This way we can decide on the validity of castling. 
	 */
	boolean hasWhiteKingMoved;
	boolean hasBlackKingMoved;
	
	/**
	 * We want to be able to know whether the Rooks have been moved during the course of
	 * the game or not. This way we can decide on the validity of castling. 
	 */
	boolean hasWhiteQRookMoved;
	boolean hasWhiteKRookMoved;
	boolean hasBlackQRookMoved;
	boolean hasBlackKRookMoved;


	/**
	 * We want the ChessBoard object to hold the Kings' positions because we
	 * frequently use this information. This way we don't have to search all the
	 * squares of the chessboard every time we want to find the Kings' positions. 
	 */
	public int[] whiteKingPosition;
	public int[] blackKingPosition;
	
	
	/**
	 * If the last move played on the chessboard was a "possible en-passant enabler" (eg. b2b4)
	 * the 2 first elements of the matrix show the initial square
	 * of the pawn which was moved and the 2 last elements show its final square.
	 * But, if the last move was not a "possible en-passant enabler" the first element
	 * of the lastMove matrix has the value -2.
	 */
	public int[] lastMove;
	
	
	/**
	 * We need to keep track of the total "piece-power" each opponent has on the board.
	 * This is the base of the AI algorithms. 
	 */
	public int whitePiecesTotalPower;	
	public int blackPiecesTotalPower;	
	
	
	/////////////////////////////////////////////
	public static final int DEPTH_OF_SEARCH = 4;
	public static int currentDepth = 0;
	
	public double evaluationOfPosition;
	public int[] bestMoveInPosition; 
	////////////////////////////////////////////
	
	//////////////////////////////////////   METHODS   //////////////////////////////////////
	
	/**
	 * Default Constructor.
	 */
	public MyChessGame()
	{
		this.chessBoard = new Piece[BOARD_SIZE][BOARD_SIZE];
	
		this.whiteKingPosition = new int[2];
		this.blackKingPosition = new int[2];
		
		this.lastMove = new int[4];
		
		this.bestMoveInPosition = new int[4];
	}
	
	
	/**
	 * This method is called in order to begin a new game. 
	 * It follows the call to the Constructor of this class (which creates the chessboard).
	 * It creates all the required Piece objects and "sets" them in their appropriate positions.
	 * It also instantiates the other class variables.
	 */
	public void setStartingPosition(int color)
	{
		if (color == 1)
		{
			this.human = 1;
			this.computer = 2;
		}
		else
		{
			this.human = 2;
			this.computer = 1;
		}
		
		this.playerMoving = 1;
		
		// SET THE WHITE PIECES
		chessBoard[0][0] = wQRook;
		chessBoard[0][1] = wQKnight;
		chessBoard[0][2] = wQBishop;
		chessBoard[0][3] = wQueen;
		chessBoard[0][4] = wKing;
		chessBoard[0][5] = wKBishop;
		chessBoard[0][6] = wKKnight;
		chessBoard[0][7] = wKRook;
		
		chessBoard[1][0] = wpawn1;
		chessBoard[1][1] = wpawn2;
		chessBoard[1][2] = wpawn3;
		chessBoard[1][3] = wpawn4;
		chessBoard[1][4] = wpawn5;
		chessBoard[1][5] = wpawn6;
		chessBoard[1][6] = wpawn7;
		chessBoard[1][7] = wpawn8;
		
		// SET THE BLACK PIECES
		chessBoard[7][0] = bQRook;
		chessBoard[7][1] = bQKnight;
		chessBoard[7][2] = bQBishop;
		chessBoard[7][3] = bQueen;
		chessBoard[7][4] = bKing;
		chessBoard[7][5] = bKBishop;
		chessBoard[7][6] = bKKnight;
		chessBoard[7][7] = bKRook;
		
		chessBoard[6][0] = bpawn1;
		chessBoard[6][1] = bpawn2;
		chessBoard[6][2] = bpawn3;
		chessBoard[6][3] = bpawn4;
		chessBoard[6][4] = bpawn5;
		chessBoard[6][5] = bpawn6;
		chessBoard[6][6] = bpawn7;
		chessBoard[6][7] = bpawn8;
		
		//The Kings have not already been moved...
		this.hasWhiteKingMoved = false;
		this.hasBlackKingMoved = false;
		
		// And neither the Rooks...
		this.hasWhiteQRookMoved = false;
		this.hasWhiteKRookMoved = false;
		this.hasBlackQRookMoved = false;
		this.hasBlackKRookMoved = false;
		
		//Hold the info of the Kings' positions:
		this.setWhiteKingPosition(0, 4);
		this.setBlackKingPosition(7, 4);
		
		//There is no "last move" in our new game, so we can say:
		for (int i=0; i<4; i++)
		{
			lastMove[i] = -2; 
		}
		
		// Initial total "power" of each of the colors' pieces (without taking into account the Kings).
		this.whitePiecesTotalPower = 39;
		this.blackPiecesTotalPower = 39;
		
		// We consider the initial position to be equal.
		// This is certainly true as far as we only take the material into account.
		this.evaluationOfPosition = 0.0;	
		
		// This variable doesn't have a meaningful value yet...
		for (int i=0; i<4; i++)
		{
			this.bestMoveInPosition[i] = -2; 
		}
	}
	
	
	/**
	 * This method creates a copy of the current MyChessGame object.
	 * 
	 * The creation of a "cloned" game is essential in 2 cases:
	 * 	A)	When we are searching for all the valid moves in a position.
	 * 	B)	When the AI analyses the variations starting from a particular position. 
	 * 
	 * The new MyChessGame object has references to the same (static) variables which represent the pieces.
	 * @return
	 */
	public MyChessGame cloneMyChessGame()
	{
		////////////////////////////////////////////////
		MyChessGame clonedChessGame = new MyChessGame();
		////////////////////////////////////////////////
		
		clonedChessGame.human = this.human;
		clonedChessGame.computer = this.computer;
		
		//////////////////////////////////////////////////
		//The value of this variable IS NOT CHANGED!!!
		clonedChessGame.playerMoving = this.playerMoving;
		//////////////////////////////////////////////////
		
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				if (this.chessBoard[i][j] != null)
				{
					clonedChessGame.chessBoard[i][j] = this.chessBoard[i][j];
				}
			}
		}
		
		clonedChessGame.hasWhiteKingMoved = this.hasWhiteKingMoved;
		clonedChessGame.hasBlackKingMoved = this.hasBlackKingMoved;
		
		clonedChessGame.hasWhiteQRookMoved = this.hasWhiteQRookMoved;
		clonedChessGame.hasWhiteKRookMoved = this.hasWhiteKRookMoved;
		clonedChessGame.hasBlackQRookMoved = this.hasBlackQRookMoved;
		clonedChessGame.hasBlackKRookMoved = this.hasBlackKRookMoved;
		
		clonedChessGame.setWhiteKingPosition(this.getWhiteKingPosition()[0], this.getWhiteKingPosition()[1]);
		clonedChessGame.setBlackKingPosition(this.getBlackKingPosition()[0], this.getBlackKingPosition()[1]);
		
		clonedChessGame.lastMove = new int[4];
		for (int i=0; i<4; i++)
		{
			clonedChessGame.lastMove[i] = this.lastMove[i];
		}
		
		clonedChessGame.whitePiecesTotalPower = this.whitePiecesTotalPower;
		clonedChessGame.blackPiecesTotalPower = this.blackPiecesTotalPower;
		
		
		// If the clone was created to decide on the validity of a move, the
		// value of the evaluationOfPosition variable is totally unimportant.
		// If the clone was created inside the deepSearch method, then 
		// the initial value of the variable is set to be the same as
		// the value of the "original" object.
		clonedChessGame.evaluationOfPosition = this.evaluationOfPosition;
		
		// Finally, the bestMoveInPosition variable has no use in a "cloned" object,
		// but only inside the "original" MyChessGame object.
		
		
		return clonedChessGame;
	}
	
	
	/**
	 * It returns the chessBoard element (which is of the type Piece[][]).
	 * @return
	 */
	public Piece[][] getChessBoard()
	{
		return this.chessBoard;
	}
	
	
	/**
	 * It returns the white King's coordinates on the chessboard.
	 * @return
	 */
	public int[] getWhiteKingPosition()
	{
		return this.whiteKingPosition;
	}
	
	/**
	 * It sets the white King's coordinates on the chessboard as instructed.
	 * @return
	 */
	public void setWhiteKingPosition(int a, int b)
	{
		this.whiteKingPosition[0] = a;
		this.whiteKingPosition[1] = b;
	}
	
	/**
	 * It returns the black King's coordinates on the chessboard.
	 * @return
	 */
	public int[] getBlackKingPosition()
	{
		return this.blackKingPosition;
	}
	
	/**
	 * It sets the black King's coordinates on the chessboard as instructed.
	 * @return
	 */
	public void setBlackKingPosition(int a, int b)
	{
		this.blackKingPosition[0] = a;
		this.blackKingPosition[1] = b;
	}
	
	
	/**
	 * This method "clears" the chessboard from all pieces.
	 */
	public void clearChessBoard()
	{
		for (int i=0; i<BOARD_SIZE; i++)
		{
			for (int j=0; j<BOARD_SIZE; j++)
			{
				this.getChessBoard()[i][j] = null;
			}
		}
	}
	
	/**
	 * This method returns the value of the heuristic which is based on the material.
	 * Returns POSITIVE values if white is ahead in material 
	 * or NEGATIVE values if black is ahead in material.
	 * @return
	 */
	public int getMaterialHeuristicValue()
	{
		return this.whitePiecesTotalPower - this.blackPiecesTotalPower;
	}
	
	
	/**
	 * It returns ALL MOVES (EXCEPT THE CASTLING MOVES AND THE EN-PASSANT MOVES!!)
	 * that the player (having the corresponding color of pieces)
	 * can make in the current position (BOTH VALID AND INVALID!).
	 * This method returns a Vector which contains other Vectors!
	 * Each inner Vector contains all the (VALID AND INVALID) moves of a particular piece.
	 * @return
	 */
	public Vector findAllMovesInPosition(int color)
	{
		Vector my = new Vector();	// The Vector containing ALL MOVES in the current position.
									// (Except the castling moves and the en-passant moves!!).
		Piece currentPiece = null;
		
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				currentPiece = this.getChessBoard()[i][j];
				if (currentPiece != null && currentPiece.getColor() == color)
				{
					Vector currentPieceMoves = currentPiece.findMoves(this.getChessBoard(), i, j);
					if (currentPieceMoves != null) 	// The current piece had at least one valid move...
					{
						for (int k=0; k<currentPieceMoves.size(); k++)
						{
							my.addElement(currentPieceMoves.elementAt(k));
						}
						my.addElement(new Integer(-1));	// MARKER!!
					}
				}
			}
		}
		return my;
	}
	
	
	/**
	 * It returns ALL VALID MOVES that the player (having the corresponding color of pieces)
	 * can make in the current position.
	 * This method returns a Vector which contains other Vectors!
	 * Each inner Vector contains all the valid moves of a particular piece.
	 * @param color
	 * @return
	 */
	public Vector findValidMovesInPosition(int color)
	{
		/**
		 *  The Vector we want to "build",
		 *  containing ALL VALID MOVES in the current position.
		 */
		Vector my = new Vector();	 
		
		Piece currentPiece = null;
		
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				currentPiece = this.getChessBoard()[i][j];
				if (currentPiece != null && currentPiece.getColor() == color)
				{	
					// The findMoves method (in its current implementation) does not
					// check for any castling or en-passant moves.
					///////////////////////////////////////////////////////////////////////////////
					Vector currentPieceMoves = currentPiece.findMoves(this.getChessBoard(), i, j); 
					///////////////////////////////////////////////////////////////////////////////
						
					//////////////////////////////////////////////
					Vector currentPieceValidMoves = new Vector();
					//////////////////////////////////////////////
					
					
					/////////////////////////////////////////////////////////////////////////
					// EN-PASSANT (FOR WHITE):
					if ( color==1 && currentPiece instanceof Pawn 
							&& i==4 && this.lastMove[0] != -2 &&
							(j==this.lastMove[1]+1 || j==this.lastMove[1]-1))
					{
						if (currentPieceMoves==null)
						{
							currentPieceMoves = new Vector();
							currentPieceMoves.addElement(new Integer(4));
							currentPieceMoves.addElement(new Integer(j));
							currentPieceMoves.addElement(new Integer(-5));
							currentPieceMoves.addElement(new Integer(-this.lastMove[1]));
						}
						else
						{
							currentPieceMoves.addElement(new Integer(-5));
							currentPieceMoves.addElement(new Integer(-this.lastMove[1]));
						}
					}
					// EN-PASSANT (FOR BLACK):
					else if ( color==2 && currentPiece instanceof Pawn 
								&& i==3 && this.lastMove[0] != -2 &&
								(j==this.lastMove[1]+1 || j==this.lastMove[1]-1))
					{
						if (currentPieceMoves==null)
						{
							currentPieceMoves = new Vector();
							currentPieceMoves.addElement(new Integer(3));
							currentPieceMoves.addElement(new Integer(j));
							currentPieceMoves.addElement(new Integer(-2));
							currentPieceMoves.addElement(new Integer(-this.lastMove[1]));
						}
						else
						{	
							currentPieceMoves.addElement(new Integer(-2));
							currentPieceMoves.addElement(new Integer(-this.lastMove[1]));
						}
					}
					/////////////////////////////////////////////////////////////////////////
						
						
					// If the piece had at least 1 move, the returned value of
					// the findMoves function is not null...
					if (currentPieceMoves != null)
					{
						// Since the current piece has at least 1 move,
						// put its initial coordinates in the currentPieceValidMoves Vector.
						currentPieceValidMoves.addElement(currentPieceMoves.elementAt(0));
						currentPieceValidMoves.addElement(currentPieceMoves.elementAt(1));
						
						////////////////////////////////////////////////////////////////////////
						// CASTLING:	If the piece is a King, then check the castling moves...
						if (currentPiece instanceof King && this.canCastleInKingSide(color))
						{
							// The "destination" of the King when he castles in the king-side
							// is "[8][8]". This is our convention since we know that there are
							// no such coordinates on the chessboard (so conflicts are avoided).
							currentPieceValidMoves.addElement(new Integer(8));
							currentPieceValidMoves.addElement(new Integer(8));
						}
						if (currentPiece instanceof King && this.canCastleInQueenSide(color))
						{
							// The "destination" of the King when he castles in the queen-side
							// is "[9][9]". This is our convention since we know that there are
							// no such coordinates on the chessboard (so conflicts are avoided).
							currentPieceValidMoves.addElement(new Integer(9));
							currentPieceValidMoves.addElement(new Integer(9));
						}
						/////////////////////////////////////////////////////////////////////////
							
							
						// Vector currentPieceMoves contains all the moves of the current piece. 
						// We want to put only the valid ones inside Vector currentPieceValidMoves, so:
						for (int k=2; k<currentPieceMoves.size(); k+=2)
						{
							// **********************************************
							// CHECK IF THIS PARTICULAR MOVE IS VALID OR NOT!
							// **********************************************
							if (moveIsLegal(  ( (Integer)currentPieceMoves.elementAt(0)).intValue(), ( (Integer)currentPieceMoves.elementAt(1)).intValue(),
									( (Integer)currentPieceMoves.elementAt(k)).intValue(), ( (Integer)currentPieceMoves.elementAt(k+1)).intValue()  ) )
							{
								currentPieceValidMoves.addElement(currentPieceMoves.elementAt(k));
								currentPieceValidMoves.addElement(currentPieceMoves.elementAt(k+1));
							}
						}
						// Add something to the Vector which will be returned,
						// only if we have found that there is at least one valid move for the current piece. 
						if (currentPieceValidMoves.size() > 2)
						{
							for (int k=0; k<currentPieceValidMoves.size(); k++)
							{
								my.addElement(currentPieceValidMoves.elementAt(k));
							}
							my.addElement(new Integer(-1)); 	// MARKER!!
						}
					}
				}
			}
		}
		
		return my;
	}
	
	
	/**
	 * This method checks if a move is legal or not.
	 * It returns false if that move puts the player's King under attack.
	 * @param color
	 * @param initialSquare
	 * @param finalSquare
	 * @return
	 */
	public boolean moveIsLegal(int initialLine, int initialColumn, int finalLine, int finalColumn)
	{
		MyChessGame clonedChessGame = this.cloneMyChessGame();
		clonedChessGame.executeMove(initialLine, initialColumn, finalLine, finalColumn, true); 	// computerMove --> true !!
		
		if (clonedChessGame.isChecked(clonedChessGame.playerMoving))
			return false;
		else
			return true;
	}
	
	/**
	 * This method executes a VALID OR INVALID move on the board...
	 * (We want to be able to execute invalid moves in order to find out that they are "really" invalid!!)
	 * @param initialSquare
	 * @param finalSquare
	 * @param computerMove:	Used in a promotion, to help ask the player 
	 * 						what he wishes to promote his pawn to.
	 */
	public void executeMove(int initialLine, int initialColumn, int finalLine, int finalColumn, boolean computerPromotion)
	{
		Piece init = this.getChessBoard()[initialLine][initialColumn];	// We know that:  init != null
		int pieceColor = init.getColor();
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// EXECUTING A CASTLING MOVE (AND THE "FIRST PART" OF ANY OTHER KING MOVE)...
		// If a King moves we have to update the whiteKingPosition or blackKingPosition variable.
		// We also have to remember that he has been moved during the course of the game (so he is no longer able to castle). 
		// We also have to check if the move to be executed is a castling move!
		if (init == this.wKing)
		{
			// If the white King castles short...
			if (finalLine == 8) 	// or: finalColumn==8 , or: finalLine==8 && finalColumn==8   
			{	// move the King...
				this.getChessBoard()[0][6] = this.getChessBoard()[0][4];	
				this.getChessBoard()[0][4] = null;
				// move the Rook...
				this.getChessBoard()[0][5] = this.getChessBoard()[0][7];	
				this.getChessBoard()[0][7] = null;
				// Update the variables...
				this.setWhiteKingPosition(0, 6);
				this.hasWhiteKingMoved = true;
				this.hasWhiteKRookMoved = true;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// Nothing else to do...
				return;
			}
			// If the white King castles long...
			if (finalLine == 9)	// or: finalColumn==9 , or: finalLine==9 && finalColumn==9  
			{	// move the King...
				this.getChessBoard()[0][2] = this.getChessBoard()[0][4];	
				this.getChessBoard()[0][4] = null;
				// move the Rook...
				this.getChessBoard()[0][3] = this.getChessBoard()[0][0];	
				this.getChessBoard()[0][0] = null;
				// Update the variables...
				this.setWhiteKingPosition(0, 2);
				this.hasWhiteKingMoved = true;
				this.hasWhiteQRookMoved = true;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// Nothing else to do...
				return;
			}
			
			// FOR ANY OTHER (NON-CASTLING) MOVE OF THE WHITE KING...
			this.setWhiteKingPosition(finalLine, finalColumn);
			this.hasWhiteKingMoved = true;
			// no return-statement here!...
		}
		else if (init == this.bKing)
		{
			// If the black King castles short...
			if (finalLine == 8)	// or: finalColumn==8 , or: finalLine==8 && finalColumn==8 
			{	// move the King...
				this.getChessBoard()[7][6] = this.getChessBoard()[7][4];	
				this.getChessBoard()[7][4] = null;
				// move the Rook...
				this.getChessBoard()[7][5] = this.getChessBoard()[7][7];	
				this.getChessBoard()[7][7] = null;
				// Update the variables...
				this.setBlackKingPosition(7, 6);
				this.hasBlackKingMoved = true;
				this.hasBlackKRookMoved = true;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// Nothing else to do...
				return;
			}
			// If the black King castles long...
			if (finalLine == 9)	// or: finalColumn==9 , or: finalLine==9 && finalColumn==9  
			{	// move the King...
				this.getChessBoard()[7][2] = this.getChessBoard()[7][4];	
				this.getChessBoard()[7][4] = null;
				// move the Rook...
				this.getChessBoard()[7][3] = this.getChessBoard()[7][0];	
				this.getChessBoard()[7][0] = null;
				// Update the variables...
				this.setBlackKingPosition(7, 2);
				this.hasBlackKingMoved = true;
				this.hasBlackQRookMoved = true;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// Nothing else to do...
				return;
			}
			
			// FOR ANY OTHER (NON-CASTLING) MOVE OF THE BLACK KING...
			this.setBlackKingPosition(finalLine, finalColumn);
			this.hasBlackKingMoved = true;
			// no return-statement here!...
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// EXECUTING AN EN-PASSANT MOVE...
		else if (init instanceof Pawn && finalLine<0)
		{
			if (init.getColor()==1)
			{
				this.getChessBoard()[-finalLine][-finalColumn] = this.getChessBoard()[initialLine][initialColumn];
				this.getChessBoard()[-finalLine-1][-finalColumn] = null;	 //differs for black!
				this.getChessBoard()[initialLine][initialColumn] = null;
				this.blackPiecesTotalPower -= 1;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// nothing else to do...
				return;
			}
			else if (init.getColor()==2)
			{
				this.getChessBoard()[-finalLine][-finalColumn] = this.getChessBoard()[initialLine][initialColumn];
				this.getChessBoard()[-finalLine+1][-finalColumn] = null;	// differs for white!
				this.getChessBoard()[initialLine][initialColumn] = null;
				this.whitePiecesTotalPower -= 1;
				this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
				// nothing else to do...
				return;
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// If either the QRook or the KRook is about to be moved, we have to keep track of that info...
		else if (init == this.wQRook)
		{
			this.hasWhiteQRookMoved = true;
		}
		else if (init == this.wKRook)
		{
			this.hasWhiteKRookMoved = true;
		}
		else if (init == this.bQRook)
		{
			this.hasBlackQRookMoved = true;
		}
		else if (init == this.bKRook)
		{
			this.hasBlackKRookMoved = true;
		}
		
		
		////////////////////////////////////////////////////////////////////////////////////
		Piece fin = this.getChessBoard()[finalLine][finalColumn];
		
		// When a piece is taken with this move we have to update the following variables.
		if (fin instanceof Piece && fin.getColor() == 1)
		{
			this.whitePiecesTotalPower -= fin.getValue();
		}
		else if (fin instanceof Piece && fin.getColor() == 2)
		{
			this.blackPiecesTotalPower -= fin.getValue();
		}
		////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// IF A PAWN IS MOVED, WE CHECK WHETHER IT IS PROMOTED OR NOT!...
		if (init instanceof Pawn  &&  ( (finalLine==7 && pieceColor == 1) || (finalLine==0 && pieceColor == 2) ))//%%%%%%%%%%%CUT SMTHNG
		{
			if (computerPromotion==false)
			{	
				Scanner sc = new Scanner(System.in);
				
				System.err.println("Choose into what you want to promote your pawn: ");
				System.err.println("1 for Queen, 2 for Rook, 3 for Bishop or 4 for Knight.");
				
				int choice = sc.nextInt();
				switch (choice)
				{
					case 1:
						this.getChessBoard()[finalLine][finalColumn] = new Queen(pieceColor);
						break;
					case 2:
						this.getChessBoard()[finalLine][finalColumn] = new Rook(pieceColor);
						break;
					case 3:
						this.getChessBoard()[finalLine][finalColumn] = new Bishop(pieceColor);
						break;
					case 4:
						this.getChessBoard()[finalLine][finalColumn] = new Knight(pieceColor);
				}
			}
			else 	// The computer plays...(It has to be changed so that the computer can asses other promotions too...) 
			{
				this.getChessBoard()[finalLine][finalColumn] = new Queen(pieceColor);
			}
			
			this.getChessBoard()[initialLine][initialColumn] = null;
			
			if (pieceColor == 1)
			{
				this.whitePiecesTotalPower += (this.getChessBoard()[finalLine][finalColumn].getValue()-1) ;	// Value of the new piece minus 1.
			}
			else if (pieceColor == 2)
			{
				this.blackPiecesTotalPower += (this.getChessBoard()[finalLine][finalColumn].getValue()-1);	// Value of the new piece minus 1.
			}
			this.lastMove[0] = -2;	// the move isn't an en-passant enabler!
			return;
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// FOR ALL MOVES EXCEPT PROMOTIONS, EN-PASSANT AND CASTLING:
		// EXECUTE THE MOVE "ON THE CHESSBOARD"!... 
		this.getChessBoard()[finalLine][finalColumn] = this.getChessBoard()[initialLine][initialColumn];
		this.getChessBoard()[initialLine][initialColumn] = null;
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// If the move executed was an "en-passant enabler" we update the variable lastMove.
		// If it wasn't, we set the lastMove[0] to be -2.
		if (init instanceof Pawn && pieceColor==1 && initialLine==1 && finalLine==3)
		{
			this.lastMove[0] = 1;
			this.lastMove[1] = initialColumn;
			this.lastMove[2] = 3;
			this.lastMove[2] = finalColumn;
		}
		else if (init instanceof Pawn && pieceColor==2 && initialLine==6 && finalLine==4)
		{
			this.lastMove[0] = 6;
			this.lastMove[1] = initialColumn;
			this.lastMove[2] = 4;
			this.lastMove[2] = finalColumn;
		}
		else
		{
			this.lastMove[0] = -2;
		}
		
		return;
	}
	
	
	/**
	 * Returns true if the player whose color is passed as the parameter 
	 * is being checked and false if he isn't.
	 * @param color
	 * @return
	 */
	public boolean isChecked(int color)
	{
		if (color == 2)
		{
			Vector aux = this.findAllMovesInPosition(1);
			// eg:   < 2 2 3 3 4 4 -1 4 5 6 6 7 7 -1 ... >
			for (int i=2; i<aux.size(); i+=2)
			{
				int num = ((Integer)aux.elementAt(i)).intValue();
				if (num == -1)
				{
					i--;
					continue;
				}
				else
				{
					if (num == this.getBlackKingPosition()[0] && ((Integer)aux.elementAt(i+1)).intValue() == getBlackKingPosition()[1])
					{
						return true;
					}
				}
			}
		}
		else if (color == 1)
		{
			Vector aux = this.findAllMovesInPosition(2);
			// eg:   < 2 2 3 3 4 4 -1 4 5 6 6 7 7 -1 ... >
			for (int i=2; i<aux.size(); i+=2)
			{
				int num = ((Integer)aux.elementAt(i)).intValue();
				if (num == -1)
				{
					i--;
					continue;
				}
				else
				{
					if (num == this.getWhiteKingPosition()[0] && ((Integer)aux.elementAt(i+1)).intValue() == getWhiteKingPosition()[1])
					{
						return true;
					}
				}
			}
		}
		
		return false; 
	}
	
	/**
	 * This method decides if in the current position the player having the "color" pieces
	 * is checkmated or not. IN THAT CASE THE GAME SHOULD END IMMEDIATELY.
	 * @param color
	 * @return
	 */
	public boolean isMated(int color)
	{
		if (this.isChecked(color) && this.findValidMovesInPosition(color).isEmpty()) 	// %%%%%%%%%redundancy later...
		{
			return true;
		}
				
		return false;
	}
	
	/**
	 * This method decides if in the current position the player having the "color" pieces
	 * is stalemated or not. IN THAT CASE THE GAME SHOULD END IMMEDIATELY AS A DRAW.
	 * @param color
	 * @return
	 */
	public boolean isStalemate(int color)
	{
		if (!this.isChecked(color) && this.findValidMovesInPosition(color).isEmpty())
		{
			return true;
		}
				
		return false;
	}
	
	/**
	 * This method is used to let the player enter his move which is then validated
	 * and if it is legal it is played on the chessboard.
	 * Else, the player is prompted to enter another move.
	 * @param color
	 * @throws ChessGameError
	 */
	public void processPlayerMove(int color) throws ChessGameError 
	{
		Scanner sc = new Scanner(System.in);
		
		while (true)
		{
			System.err.println("Enter your move:");
			String playerMove = sc.next();
		
			if (!playerMove.equals("0-0") && !playerMove.equals("0-0-0") 
					&& playerMove.length() != 4 && !(playerMove.length() == 6 && playerMove.substring(4).equals("ep")))
			{
				System.err.println("Incorrect move format!");
				continue;
			}
			
			int initialLine=0;
			int initialColumn=0;
			int finalLine=0;
			int finalColumn=0;
			
			// THE PLAYER WANTS TO CASTLE...
			// white castles short...
			if (playerMove.equals("0-0") && color == 1)
			{
				initialLine = 0;
				initialColumn = 4;
				finalLine = 8;
				finalColumn = 8;
			}
			// white castles long...
			else if (playerMove.equals("0-0-0") && color == 1)
			{
				initialLine = 0;
				initialColumn = 4;
				finalLine = 9;
				finalColumn = 9;
			}
			// black castles short...
			else if (playerMove.equals("0-0") && color == 2)
			{
				initialLine = 7;
				initialColumn = 4;
				finalLine = 8;
				finalColumn = 8;
			}
			// black castles long...
			else if (playerMove.equals("0-0-0") && color == 2)
			{
				initialLine = 7;
				initialColumn = 4;
				finalLine = 9;
				finalColumn = 9;
			}
			
			// THE PLAYER WANTS TO PLAY EN-PASSANT...
			// example: d5c6ep
			// white plays en-passant...
			else if (playerMove.length()==6 && color == 1)
			{
				initialLine = Integer.parseInt(Character.toString(playerMove.charAt(1)))-1; 	
				
				switch (playerMove.charAt(0))
				{
					case 'a':
						initialColumn = 0;
						break;
					case 'b':
						initialColumn = 1;
						break;
					case 'c':
						initialColumn = 2;
						break;
					case 'd':
						initialColumn = 3;
						break;
					case 'e':
						initialColumn = 4;
						break;
					case 'f':
						initialColumn = 5;
						break;
					case 'g':
						initialColumn = 6;
						break;
					case 'h':
						initialColumn = 7;
				}
				
				finalLine = - (Integer.parseInt(Character.toString(playerMove.charAt(3)))-1);		// with minus!!
		
				switch (playerMove.charAt(2))
				{
					case 'a':
						finalColumn = -0;
						break;
					case 'b':
						finalColumn = -1;
						break;
					case 'c':
						finalColumn = -2;
						break;
					case 'd':
						finalColumn = -3;
						break;
					case 'e':
						finalColumn = -4;
						break;
					case 'f':
						finalColumn = -5;
						break;
					case 'g':
						finalColumn = -6;
						break;
					case 'h':
						finalColumn = -7;
				}
			}
			// black plays en-passant...
			else if (playerMove.length()==6 && color == 2)
			{
				initialLine = Integer.parseInt(Character.toString(playerMove.charAt(1)))-1; 	
				
				switch (playerMove.charAt(0))
				{
					case 'a':
						initialColumn = 0;
						break;
					case 'b':
						initialColumn = 1;
						break;
					case 'c':
						initialColumn = 2;
						break;
					case 'd':
						initialColumn = 3;
						break;
					case 'e':
						initialColumn = 4;
						break;
					case 'f':
						initialColumn = 5;
						break;
					case 'g':
						initialColumn = 6;
						break;
					case 'h':
						initialColumn = 7;
				}
				
				finalLine = - (Integer.parseInt(Character.toString(playerMove.charAt(3)))-1);
		
				switch (playerMove.charAt(2))
				{
					case 'a':
						finalColumn = -0;
						break;
					case 'b':
						finalColumn = -1;
						break;
					case 'c':
						finalColumn = -2;
						break;
					case 'd':
						finalColumn = -3;
						break;
					case 'e':
						finalColumn = -4;
						break;
					case 'f':
						finalColumn = -5;
						break;
					case 'g':
						finalColumn = -6;
						break;
					case 'h':
						finalColumn = -7;
				}
			}
			
			// THE USER ENTERS ANY MOVE WHICH IS NOT CASTLING OR EN-PASSANT!...
			else
			{
				initialLine = Integer.parseInt(Character.toString(playerMove.charAt(1)))-1; 	
		
				switch (playerMove.charAt(0))
				{
					case 'a':
						initialColumn = 0;
						break;
					case 'b':
						initialColumn = 1;
						break;
					case 'c':
						initialColumn = 2;
						break;
					case 'd':
						initialColumn = 3;
						break;
					case 'e':
						initialColumn = 4;
						break;
					case 'f':
						initialColumn = 5;
						break;
					case 'g':
						initialColumn = 6;
						break;
					case 'h':
						initialColumn = 7;
				}
				
				finalLine = Integer.parseInt(Character.toString(playerMove.charAt(3)))-1;
		
				switch (playerMove.charAt(2))
				{
					case 'a':
						finalColumn = 0;
						break;
					case 'b':
						finalColumn = 1;
						break;
					case 'c':
						finalColumn = 2;
						break;
					case 'd':
						finalColumn = 3;
						break;
					case 'e':
						finalColumn = 4;
						break;
					case 'f':
						finalColumn = 5;
						break;
					case 'g':
						finalColumn = 6;
						break;
					case 'h':
						finalColumn = 7;
				}
			}//else
			
			Vector valids = this.findValidMovesInPosition(color);
			
			for (int i=0; i<valids.size(); i++)      // SEEMS OK!!       
			{
				if ( ((Integer)valids.elementAt(i)).intValue() != initialLine  ||  ((Integer)valids.elementAt(i+1)).intValue() != initialColumn )
				{
					while (((Integer)valids.elementAt(i)).intValue() != -1)
						i++;
				}
				else // we have the initial position...
				{
					i += 2;
					while ( ((Integer)valids.elementAt(i)).intValue() != -1 && 
							( ((Integer)valids.elementAt(i)).intValue() != finalLine  ||  ((Integer)valids.elementAt(i+1)).intValue() != finalColumn ) )
					{
						i+=2;
					}
					if ( ((Integer)valids.elementAt(i)).intValue() != -1 )
					{
						this.executeMove(initialLine, initialColumn, finalLine, finalColumn, false);
						return;
					}
					else
					{
						break;
					}
				}
			}
			System.err.println("Illegal move!");
			continue;
		}
	}
	
	/**
	 * This method holds all the AI algorithms the computer uses in order to play chess.
	 * @param color
	 * @throws ChessGameError
	 */
	public void AI(int color) throws ChessGameError  
	{
		//Vector valids = this.findValidMovesInPosition(color);	// %%%%%%%%%%%%%%%%%%%%%%%%redundancy (check isMated)...
		//this.executeMove(  ((Integer)(valids.elementAt(0))).intValue(), ((Integer)(valids.elementAt(1))).intValue(),
		//					((Integer)(valids.elementAt(2))).intValue(), ((Integer)(valids.elementAt(3))).intValue(), true);
		
//		// In order to measure the time needed to perform a full search:
//		long time1 = (long) System.nanoTime() / 1000000 ;
		
//		// Initially the evaluation is...
//		System.out.println("This position's initial evaluation is: " + this.evaluationOfPosition);
		
//		// Our first possible choice is to use the "minmax_search" method.
//		// This leads to the slowest computation of the variations-tree. 
//		minmax_search(color);
//		
//		// In order to measure the time needed to perform a full search:
//		long time2 = (long) System.nanoTime() / 1000000;
//		System.out.println("Spent " + (time2 - time1) + " msec, to find that this position's evaluation is: " + evaluationOfPosition);

		// Our second possible choice is to use the "ab_search" method.
		// Compared to the use of the "minmax_search" method, the "ab_search" method leads to a considerable decrease of 
		// the time required for the computation of the variations-tree.
		
//		ArrayList<Integer> curr_path = new ArrayList<Integer>();
//		ArrayList<Integer> best_path = new ArrayList<Integer>();
//		ArrayList<Integer> possible_path = new ArrayList<Integer>();
		ArrayList<Integer> principal_line = new ArrayList<Integer>();
		// Update the evaluation of the current position, according to the results of the following search.
		this.evaluationOfPosition = ab_search(color, this.DEPTH_OF_SEARCH, -1000001 , 1000001, principal_line);
		
//		// In order to measure the time needed to perform a full search:
//		long time2 = (long) System.nanoTime() / 1000000;
//		System.out.println("Spent " + (time2 - time1) + " msec, to find the following better estimate of this position's evaluation: " + this.evaluationOfPosition);
		this.bestMoveInPosition[0] = principal_line.get(0);
		this.bestMoveInPosition[1] = principal_line.get(1);
		this.bestMoveInPosition[2] = principal_line.get(2);
		this.bestMoveInPosition[3] = principal_line.get(3);
//		System.out.println("The best path of moves is: " + principal_line);
		//System.exit(0);
		
		// Finally, play the best move on the chessboard.
		this.executeMove(this.bestMoveInPosition[0], this.bestMoveInPosition[1],
							this.bestMoveInPosition[2], this.bestMoveInPosition[3], true);
		
		return;
	}
	
	/**
	 * This method performs an Alpha-Beta search to a fixed depth.
	 * @param color
	 */
	public double ab_search(int color, int depth, double alpha, double beta, ArrayList<Integer> pline)
	{
		// Helps find the principal variation of the search.
		ArrayList<Integer> line = new ArrayList<Integer>();
		double val = 0.0;
		if (depth == 0) {
			return this.getMaterialHeuristicValue();
		}
		
		Vector valids = this.findValidMovesInPosition(color);
		Integer initialLine = (Integer)valids.elementAt(0);
		Integer initialColumn = (Integer)valids.elementAt(1);
		Integer finalLine;
		Integer finalColumn;
		
		for (int i=2; i<valids.size(); i+=2) {		
			Integer num = (Integer)valids.elementAt(i);
			if (num.intValue() == -1 && i<valids.size()-1) {  // Don't do it if you are at the end of the Vector 
				// Go to the next piece that can move...
				i++;
				initialLine = (Integer)valids.elementAt(i);
				initialColumn = (Integer)valids.elementAt(i+1);
				continue;
			}
			else if (i<valids.size()-1) {
				/////////////////////////////////////////////////
				MyChessGame clonedGame = this.cloneMyChessGame();
				/////////////////////////////////////////////////
				
				finalLine = (Integer)valids.elementAt(i);
				finalColumn = (Integer)valids.elementAt(i+1);
				
				clonedGame.executeMove(initialLine.intValue(), initialColumn.intValue(), finalLine.intValue(), finalColumn.intValue(), true);
				
				if (clonedGame.playerMoving==1) {
					clonedGame.playerMoving = 2;
				}
				else {
					clonedGame.playerMoving = 1;
				}
				
				// MAX NODE! (WHITE HAS JUST PLAYED A MOVE)
				if (playerMoving == 1) {
					// If white mated his opponent...
					if (clonedGame.isMated(2)) {
						val = 1000000.0;
					}
					// If white stalemated black...
					else if (clonedGame.isStalemate(2)) {
						val = 0.0;
					}
					else {	// RECURSION !!!
						/////////////////////////
						val = clonedGame.ab_search(2, depth-1, alpha, beta, line);
						/////////////////////////
					}	
					if (val >= beta) {
						return beta;
					} 
					if (val > alpha) {
						//................
						pline.clear();
						pline.add(initialLine);
						pline.add(initialColumn);
						pline.add(finalLine);
						pline.add(finalColumn);
						for (int j=0; j<line.size(); j++) {
							pline.add(line.get(j));
						}
						//................
						alpha = val;
					}
				}
				// MIN NODE! (BLACK JUST PLAYED A MOVE)
				else if (playerMoving == 2) {
					// If black mated his opponent...
					if (clonedGame.isMated(1)) {
						val = -1000000.0;
					}
					// If black stalemated white...
					else if (clonedGame.isStalemate(1)) {
						val = 0.0;
					}
					else { // RECURSION !!!
						/////////////////////////
						val = clonedGame.ab_search(1, depth-1, alpha, beta, line);
						/////////////////////////
					}	
					if (val <= alpha) {
						return alpha;
					} 
					if (val < beta) {
						//................
						pline.clear();
						pline.add(initialLine);
						pline.add(initialColumn);
						pline.add(finalLine);
						pline.add(finalColumn);
						for (int j=0; j<line.size(); j++) {
							pline.add(line.get(j));
						}
						//................
						beta = val;
					}
				}
			}
		} // end-for (all moves)
		if (playerMoving == 1) {
			return alpha;
		}
		else {
			return beta;
		}	
	}

	
	/**
	 * This method performs a simple MinMax search to a fixed depth.
	 * In our first implementations of the "My_Chess_Program" project, this method was called "deep_search" instead.
	 * @param color
	 */
	public void minmax_search(int color)
	{
		Vector valids = this.findValidMovesInPosition(color);
		
		Integer initialLine = (Integer)valids.elementAt(0);
		Integer initialColumn = (Integer)valids.elementAt(1);
		Integer finalLine;
		Integer finalColumn;
		
		for (int i=2; i<valids.size(); i+=2)	// 
		{		
			Integer num = (Integer)valids.elementAt(i);
			if (num.intValue() == -1 && i<valids.size()-1) 	// Don't do it if you are at the end of the Vector 
			{
				// Go to the next piece that can move...
				i++;
				
				initialLine = (Integer)valids.elementAt(i);
				initialColumn = (Integer)valids.elementAt(i+1);
				
				continue;
			}
			else if (i<valids.size()-1)
			{
				/////////////////////////////////////////////////
				MyChessGame clonedGame = this.cloneMyChessGame();
				/////////////////////////////////////////////////
				
				finalLine = (Integer)valids.elementAt(i);
				finalColumn = (Integer)valids.elementAt(i+1);
				
				clonedGame.executeMove(initialLine.intValue(), initialColumn.intValue(), finalLine.intValue(), finalColumn.intValue(), true); 	// to check!
				
				if (clonedGame.playerMoving==1)
				{
					clonedGame.playerMoving = 2;
				}
				else
				{
					clonedGame.playerMoving = 1;
				}
				
				MyChessGame.currentDepth++;
				
				
				// WHITE JUST PLAYED A MOVE...
				if (clonedGame.playerMoving == 2)
				{
					// If white mated his opponent...
					if (clonedGame.isMated(2))
					{
						// Always set the evaluation.
						this.evaluationOfPosition = 1000000;
						// Set the bestMoveInPosition[x] only when referring to the position on the board
						// and not inside an "analysis" tree.
						// This approach is used in all following similar points.
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();			
						}
					}
					// If black is now in a stalemate...
					else if ( i == 2 && clonedGame.isStalemate(2))
					{
						this.evaluationOfPosition = 0.0;
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();		
						}
					}
					else if (clonedGame.isStalemate(2) && this.evaluationOfPosition < 0)
					{
						this.evaluationOfPosition = 0.0;
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();			
						}
					}
					// If we are at the "leaves" of the analysis-tree... 
					else if (MyChessGame.currentDepth == MyChessGame.DEPTH_OF_SEARCH)
					{
						int heuristic = clonedGame.getMaterialHeuristicValue();
						// If this is the first time we try a move (i==2)
						// we have to give a value to the variables.
						if (i == 2)
						{
							this.evaluationOfPosition = heuristic;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
						// If we have found a good move for white...
						else if (heuristic >= this.evaluationOfPosition)
						{
							this.evaluationOfPosition = heuristic;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
					}
					else 	// RECURSION !!!
					{
						/////////////////////////
						clonedGame.minmax_search(2);
						/////////////////////////
						
						if (i == 2)
						{
							this.evaluationOfPosition = clonedGame.evaluationOfPosition;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();		
							}
						}
						else if (clonedGame.evaluationOfPosition >= this.evaluationOfPosition )
						{
							this.evaluationOfPosition = clonedGame.evaluationOfPosition;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
					}
				}
				// BLACK JUST PLAYED A MOVE...
				else if (clonedGame.playerMoving == 1)
				{
					// If black mated his opponent...
					if (clonedGame.isMated(1))
					{
						this.evaluationOfPosition = -1000000;
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();			
						}
					}
					// If white is now in a stalemate...
					else if (i == 2 && clonedGame.isStalemate(1))
					{
						this.evaluationOfPosition = 0.0;
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();			
						}
					}
					else if (clonedGame.isStalemate(1) && this.evaluationOfPosition > 0)
					{
						this.evaluationOfPosition = 0.0;
						if (MyChessGame.currentDepth==1)
						{
							this.bestMoveInPosition[0] = initialLine.intValue();
							this.bestMoveInPosition[1] = initialColumn.intValue();
							this.bestMoveInPosition[2] = finalLine.intValue();
							this.bestMoveInPosition[3] = finalColumn.intValue();		
						}
					}
					// If we are at the "leaves" of the analysis-tree... 
					else if (MyChessGame.currentDepth == MyChessGame.DEPTH_OF_SEARCH)
					{
						int heuristic = clonedGame.getMaterialHeuristicValue();
						
						if (i == 2)
						{
							this.evaluationOfPosition = heuristic;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
						
						// If we have found a good move for black...
						else if (heuristic <= this.evaluationOfPosition)
						{
							this.evaluationOfPosition = heuristic;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
					}
					else 	// RECURSION !!!
					{
						/////////////////////////
						clonedGame.minmax_search(1);
						/////////////////////////
						
						if (i == 2)
						{
							this.evaluationOfPosition = clonedGame.evaluationOfPosition;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
						else if (clonedGame.evaluationOfPosition <= this.evaluationOfPosition )
						{
							this.evaluationOfPosition = clonedGame.evaluationOfPosition;
							if (MyChessGame.currentDepth==1)
							{
								this.bestMoveInPosition[0] = initialLine.intValue();
								this.bestMoveInPosition[1] = initialColumn.intValue();
								this.bestMoveInPosition[2] = finalLine.intValue();
								this.bestMoveInPosition[3] = finalColumn.intValue();			
							}
						}
					}
				}
				MyChessGame.currentDepth--;
			}	
		}
		return;
	}
	
	/**
	 * This method is used to start a new game between the computer and a human player.
	 * @throws ChessGameError
	 */
	public void playTheGame() throws ChessGameError  
	{
		while (!this.isMated(this.playerMoving))
		{
			if (this.human == playerMoving) 
			{	
				System.err.print("You can castle in the Kingside: " + this.canCastleInKingSide(playerMoving) + "\n");
				System.err.print("You can castle in the Queenside: " + this.canCastleInQueenSide(playerMoving) + "\n");
				this.processPlayerMove(playerMoving);
				this.printChessBoard();
			}
			else
			{
				this.AI(playerMoving);
				this.printChessBoard();
			}
			// The other player moves now...
			if (this.playerMoving == 2)	
			{
				this.playerMoving = 1;
			}
			else 
			{
				this.playerMoving = 2;	
			}
		}
		
		if (playerMoving == this.computer)
		{
			System.err.println("It is a mate.");
			System.err.println("Congratulations, you won!");
		}
		else if (playerMoving == this.human) 
		{
			System.err.println("It is a mate.");
			System.err.println("I won!");
		}		
	}

	public boolean canCastleInKingSide(int color)
	{
		// In order to be able castle, certain conditions must be met...
		// For white...
		if (color == 1 && !this.hasWhiteKingMoved && !this.isChecked(1)
				&& this.getChessBoard()[0][7] == wKRook && !this.hasWhiteKRookMoved 
				&& this.getChessBoard()[0][5]==null && this.getChessBoard()[0][6]==null)
		{
			Vector aux = this.findAllMovesInPosition(2);
			for (int i=2; i<aux.size(); i+=2)
			{
				if (  ( ((Integer)aux.elementAt(i)).intValue() == 0  &&  ((Integer)aux.elementAt(i+1)).intValue() == 5 ) 
							|| ( ((Integer)aux.elementAt(i)).intValue() == 0  &&  ((Integer)aux.elementAt(i+1)).intValue() == 6 ) )
				{
					return false;
				}
				else if (((Integer)aux.elementAt(i)).intValue() == -1)
				{
					i--;
					continue;
				}
			}
			return true;
		}
		// ...and for black...
		else if (color == 2 && !this.hasBlackKingMoved && !this.isChecked(2)
					&& this.getChessBoard()[7][7] == bKRook  && !this.hasBlackKRookMoved 
					&& this.getChessBoard()[7][5]==null && this.getChessBoard()[7][6]==null)
		{
			Vector aux = this.findAllMovesInPosition(1);
			for (int i=2; i<aux.size(); i+=2)
			{
				if (  ( ((Integer)aux.elementAt(i)).intValue() == 7  &&  ((Integer)aux.elementAt(i+1)).intValue() == 5 ) 
							|| ( ((Integer)aux.elementAt(i)).intValue() == 7  &&  ((Integer)aux.elementAt(i+1)).intValue() == 6 ) )
				{
					return false;
				}
				else if (((Integer)aux.elementAt(i)).intValue() == -1)
				{
					i--;
					continue;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean canCastleInQueenSide(int color)		
	{
		// In order to be able castle, certain conditions must be met...
		// For white...
		if (color == 1 && !this.hasWhiteKingMoved && !this.isChecked(1)
				&& this.getChessBoard()[0][0] == wQRook && !this.hasWhiteQRookMoved 
				&& this.getChessBoard()[0][1]==null && this.getChessBoard()[0][2]==null && this.getChessBoard()[0][3]==null)
		{
			Vector aux = this.findAllMovesInPosition(2);
			for (int i=2; i<aux.size(); i+=2)
			{
				if (  ( ((Integer)aux.elementAt(i)).intValue() == 0  &&  ((Integer)aux.elementAt(i+1)).intValue() == 2 ) 
							|| ( ((Integer)aux.elementAt(i)).intValue() == 0  &&  ((Integer)aux.elementAt(i+1)).intValue() == 3 ) )
				{
					return false;
				}
				else if (((Integer)aux.elementAt(i)).intValue() == -1)
				{
					i--;
					continue;
				}
			}
			return true;
		}
		// ...and for black...
		else if (color == 2 && !this.hasBlackKingMoved && !this.isChecked(2)
					&& this.getChessBoard()[7][0] == bQRook && !this.hasBlackQRookMoved 
					&& this.getChessBoard()[7][1]==null && this.getChessBoard()[7][2]==null && this.getChessBoard()[7][3]==null)
			{
				Vector aux = this.findAllMovesInPosition(1);
				for (int i=2; i<aux.size(); i+=2)
				{
					if (  ( ((Integer)aux.elementAt(i)).intValue() == 7  &&  ((Integer)aux.elementAt(i+1)).intValue() == 2 ) 
								|| ( ((Integer)aux.elementAt(i)).intValue() == 7  &&  ((Integer)aux.elementAt(i+1)).intValue() == 3 ) )
					{
						return false;
					}
					else if (((Integer)aux.elementAt(i)).intValue() == -1)
					{
						i--;
						continue;
					}
				}
				return true;
			}
		
		return false;
	}
	
	
	/**
	 * Displays the chessboard to the console.
	 */
	public void printChessBoard()
	{	//System.err.println(" [H]  [G]  [F]  [E]  [D]  [C]  [B]  [A]    ");
		System.err.println("    -----------------------------------------");
		
		for (int i=7; i>=0; i--) // ATTENTION HERE!!!
		{
			System.err.print("[" + (i+1) + "]" + " ");
			for (int j=0; j<8; j++)
			{
				System.err.print("| ");
				if (this.chessBoard[i][j] != null)
				{
					System.err.print(this.chessBoard[i][j].getName() + " ");
				}
				else 
				{
					System.err.print("   ");
				}
			}
			System.err.println("|");
			System.err.println("    -----------------------------------------");
			//System.err.println("___________________________________________");
		}
		System.err.println("     [A]  [B]  [C]  [D]  [E]  [F]  [G]  [H]    ");
		System.err.println();	
	}
}

