package br.com.ws.restservice.core.protocol.mensage;

public class ResponseConsumer<T> implements Response<T>{

	private T mensagem;
	private ErrorResponse error;
	
	public ResponseConsumer(T mensagem ,ErrorResponse error){
		this.mensagem = mensagem;
		this.error = error;
		
	}
	public ErrorResponse getError() {
		return error;
	}
	public void setError(ErrorResponse error) {
		this.error = error;
	}
	public T getMensagem() {
		return mensagem;
	}
	public void setMensagem(T mensagem) {
		this.mensagem = mensagem;
	}
	
}
