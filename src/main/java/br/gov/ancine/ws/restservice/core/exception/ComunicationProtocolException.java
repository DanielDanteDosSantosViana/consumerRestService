package br.gov.ancine.ws.restservice.core.exception;

public class ComunicationProtocolException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ComunicationProtocolException(){
		super();
	}
	
	public ComunicationProtocolException(String message){
		super(message);
	}
	public ComunicationProtocolException(Throwable cause){
		super(cause);
	}
	public ComunicationProtocolException(String message, Throwable cause) {
	        super(message, cause);
	}
	
}
