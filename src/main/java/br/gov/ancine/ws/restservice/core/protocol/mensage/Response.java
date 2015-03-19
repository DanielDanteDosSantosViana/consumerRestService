package br.gov.ancine.ws.restservice.core.protocol.mensage;

public class Response<T> {

	private ErrorResponse error;
	private T result;
	
	public Response(ErrorResponse error , T result){
		this.error = error;
		this.result = result;
	}

	public Response(){
		
	}

	public ErrorResponse getError() {
		return error;
	}
	public void setError(ErrorResponse error) {
		this.error = error;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}


}
