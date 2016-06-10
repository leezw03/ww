package ww.core.exception;

public class InnerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	
	public InnerException(String message) {
		super(message);
	}
	
	public InnerException(String message, Exception exception) {
		super(message);
		exception.printStackTrace();
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
