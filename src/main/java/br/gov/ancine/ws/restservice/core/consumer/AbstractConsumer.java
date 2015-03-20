package br.gov.ancine.ws.restservice.core.consumer;

import java.io.Serializable;

import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.CommunicationProtocol;
import br.gov.ancine.ws.restservice.core.searchParameter.Search;

public abstract class AbstractConsumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected <T> Object consumesGet(Search<T> searchParameterUri) throws ServiceErrorException{
		return new CommunicationProtocol<T>().httpGet(searchParameterUri.getType(),searchParameterUri.getTarget());
	}




}
