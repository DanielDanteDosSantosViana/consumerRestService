package br.com.ws.restservice.core.exception;


public class ServiceErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceErrorException(){
		super();
	}
	
	public ServiceErrorException(String message){
		super(message);
	}
	public ServiceErrorException(Throwable cause){
		super(cause);
	}
	public ServiceErrorException(String message, Throwable cause) {
	        super(message, cause);
	}
	

}
