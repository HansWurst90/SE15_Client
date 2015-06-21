package common;

/**
 * 
 * BooleanResponse wird vom message system benutzt um booleans zurückzugeben
 * @author Andreas Prischep
 *
 */
public class BooleanResponse extends ReturncodeResponse {

	
	private static final long serialVersionUID = 292317176289587801L;
	
	private boolean successfull;

	public boolean isSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(boolean successfull) {
		this.successfull = successfull;
	}

	
	
	
}
