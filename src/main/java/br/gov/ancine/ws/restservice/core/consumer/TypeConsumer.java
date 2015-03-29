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


/**
 * @author daniel
 *
 * @param <T>
 */
public final class TypeConsumer<T> implements ITypeConsumer<T> {

	protected ParserJSON parser = new ParserJSON();
	private Class<?> entityDTO;
	protected String targetURL;
	protected Parameter paramURL;
	private ReturnCollectionType<Collection<T>> returnTypeCollection;
	
	public TypeConsumer(Class<?> entityDTO){
		this.entityDTO = entityDTO;
		
	}

	public Response<T> getWithParameter() throws ServiceErrorException {
		try{
		
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,paramURL).getTarget());
			Object objectParser = parser(response);
			return new BuildResponse<T>(response.getStatusCode(),objectParser).build();
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		}
	
	}
	public Response<T> getEntity() throws ServiceErrorException{
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
	
	public Response<T> get() throws ServiceErrorException {
	  if(paramURL!=null){
		  return getWithParameter();
	   }
		  return getEntity();
	}

	public ReturnCollectionType<Collection<T>> configureReturnCollection(Class<? extends Collection> configuration){		
		return returnTypeCollection = new ReturnCollectionType<Collection<T>>(targetURL, paramURL,entityDTO);
	}

	protected TypeConsumer<T> configureGetWithParameter(String target , Parameter parameter){
		this.paramURL = parameter;
		this.targetURL=target;
		return this;
	}
	
	protected TypeConsumer<T> configureGet(String target) {
		this.targetURL=target;
		return this;
	}
	
	private  Object parser(ResponseProtocol response) throws ParserJsonException{
		return  parser.fromJson(response.getJsonMensage(),entityDTO);
	}
	
	/*	protected Response<T> post(){
		
	}
	protected Response<T> put(){
		
	}
	protected Response<T> delete(){
		
	}
	
	
	 */	

}
