package fr.cedricsevestre.exception;

public class TagException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TagException(String message) {
		super(message);
	}
	
	public TagException(String message, Throwable e) {
		super(message, e);
	}
}
