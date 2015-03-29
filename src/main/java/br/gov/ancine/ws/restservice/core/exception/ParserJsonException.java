package br.gov.ancine.ws.restservice.core.exception;

public class ParserJsonException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ParserJsonException(){
		super();
	}
	
	public ParserJsonException(String message){
		super(message);
	}
	public ParserJsonException(Throwable cause){
		super(cause);
	}
	public ParserJsonException(String message, Throwable cause) {
	        super(message, cause);
	}
}
