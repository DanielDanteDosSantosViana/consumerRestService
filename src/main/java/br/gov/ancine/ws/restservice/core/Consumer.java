package br.gov.ancine.ws.restservice.core;

import java.io.Serializable;

import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.CommunicationProtocol;

public abstract class Consumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected <T> Object consumesGet(Search<T> searchParameterUri) throws ServiceErrorException{
		return new CommunicationProtocol<T>().httpGet(searchParameterUri.getType(),searchParameterUri.getTarget());
	}




}
