package br.com.ws.restservice.core.exception;

import br.com.ws.restservice.core.protocol.mensage.ErrorResponse;

public class ServiceViewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private ErrorResponse error;
	
	 public ServiceViewException() {
	        super();
     }

	 public ServiceViewException(ErrorResponse error) {
	        this.error = error;
	 }

		public ErrorResponse getError() {
			return error;
		}

	




}
