package studeasy.common;

public class NoSessionException extends StudeasyException {

	private static final long serialVersionUID = -1201698533694759093L;
	private static final int CODE = 10;

	public NoSessionException(String message) {
		super(CODE, message);
		
	}
}
