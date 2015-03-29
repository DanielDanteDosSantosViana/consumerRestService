package br.gov.ancine.ws.restservice.core.consumer;

import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;

public interface ITypeConsumer<T> {
	 Response<T> getWithParameter() throws ServiceErrorException;
	 Response<T> getEntity() throws ServiceErrorException;
	 Response<T> get() throws ServiceErrorException;
}
