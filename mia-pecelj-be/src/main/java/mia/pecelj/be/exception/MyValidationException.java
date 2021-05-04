package mia.pecelj.be.exception;

public class MyValidationException extends MyApplicationException {

	private static final long serialVersionUID = 1L;

	public MyValidationException(String message) {
		super(message);
	}
}