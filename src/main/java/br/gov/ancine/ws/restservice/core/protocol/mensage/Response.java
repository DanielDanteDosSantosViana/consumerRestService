package br.gov.ancine.ws.restservice.core.protocol.mensage;

public interface Response<T> {
	public T getMensagem();
	public ErrorResponse getError();
}
