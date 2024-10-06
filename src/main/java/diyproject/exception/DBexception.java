package diyproject.exception;

public class DBexception extends RuntimeException {

	public DBexception() {
	}

	public DBexception(String message) {
		super(message);
	}

	public DBexception(Throwable cause) {
		super(cause);
	}

	public DBexception(String message, Throwable cause) {
		super(message, cause);
	}

}