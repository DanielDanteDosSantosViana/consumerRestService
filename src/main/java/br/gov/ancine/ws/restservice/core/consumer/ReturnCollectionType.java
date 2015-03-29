/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.gov.ancine.ws.restservice.core.consumer;

import java.util.Collection;

import br.gov.ancine.ws.restservice.core.exception.ComunicationProtocolException;
import br.gov.ancine.ws.restservice.core.exception.ParserJsonException;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.parser.ParserJSON;
import br.gov.ancine.ws.restservice.core.protocol.CommunicationProtocol;
import br.gov.ancine.ws.restservice.core.protocol.mensage.BuildResponse;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;
import br.gov.ancine.ws.restservice.core.protocol.mensage.ResponseProtocol;
import br.gov.ancine.ws.restservice.core.searchParameter.Parameter;
import br.gov.ancine.ws.restservice.core.searchParameter.SearchParameterUri;

public final class ReturnCollectionType<T extends Collection> implements ITypeConsumer<T>{
	protected String targetURL;
	protected Parameter paramURL;
	protected ParserJSON parser = new ParserJSON();
	private Class<?> entityDTO;
	
	
	public ReturnCollectionType(String targetURL ,Parameter paramURL,Class<?> entityDTO){
		this.targetURL = targetURL;
		this.paramURL = paramURL;
		this.entityDTO=entityDTO;
		
	}
	public Response<T> getWithParameter() throws ServiceErrorException {
		try{
			
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,paramURL).getTarget());
			Object objectParser = parser(response);
			return new BuildResponse<T>(response.getStatusCode(),objectParser).build();
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : "+ e.getMessage() , e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : "+ e.getMessage() , e);
		}
	
	}
	
	public Response<T> get() throws ServiceErrorException {
		 if(paramURL!=null){
			  return getWithParameter();
	     }
		    return getEntity();
	}

	public Response<T> getEntity() throws ServiceErrorException {
		
		try{
			ResponseProtocol response = new CommunicationProtocol().httpGet(targetURL);
			Object objectParser = parser(response);			
			return new BuildResponse<T>(response.getStatusCode(),objectParser).build();	
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : "+ e.getMessage() , e);
		}
	}
	
	private  Object parser(ResponseProtocol response) throws ParserJsonException{
			return parser.collectionfromJson(response.getJsonMensage(),entityDTO);
	}
	


}
