package errors;


public class ChessGameError extends Exception
{
	public String errorMessage;
	
	public ChessGameError(String errorMsg)
	{
		this.errorMessage = errorMsg;
	}
}
