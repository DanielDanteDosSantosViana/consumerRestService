package br.com.ws.restservice.core.consumer;

import br.com.ws.restservice.core.exception.ServiceErrorException;
import br.com.ws.restservice.core.protocol.mensage.Response;

public interface ITypeConsumer<T> {
	 Response<T> get() throws ServiceErrorException;
	 Response<T> post() throws ServiceErrorException;
	 
}
