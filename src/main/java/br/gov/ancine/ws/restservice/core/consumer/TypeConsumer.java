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
import java.util.Set;

import br.gov.ancine.ws.restservice.core.exception.ComunicationProtocolException;
import br.gov.ancine.ws.restservice.core.exception.ParserJsonException;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.parser.ParserJSON;
import br.gov.ancine.ws.restservice.core.protocol.CommunicationProtocol;
import br.gov.ancine.ws.restservice.core.protocol.mensage.BuildResponse;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;
import br.gov.ancine.ws.restservice.core.protocol.mensage.ResponseProtocol;
import br.gov.ancine.ws.restservice.core.searchParameter.IParameterGET;
import br.gov.ancine.ws.restservice.core.searchParameter.IParameterPOST;
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
	protected IParameterGET paramGET;
	protected IParameterPOST paramPOST;
	
	public TypeConsumer(Class<?> entityDTO){
		this.entityDTO = entityDTO;		
	}

	public Response<T> getWithParameter() throws ServiceErrorException {
		try{
		
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,paramGET).getTarget());
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		}
	
	}
	public Response<T> getEntity() throws ServiceErrorException{
		try{	
			ResponseProtocol response = new CommunicationProtocol().httpGet(targetURL);
			
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : "+ e.getMessage() , e);
		}
	}
	
	public Response<T> get() throws ServiceErrorException {
	  if(paramGET!=null){
		  return getWithParameter();
	   }
		  return getEntity();
	}
	

	public ReturnCollectionType<Collection<T>> configureReturnCollection(Class<? extends Collection> configuration){		
		return new ReturnCollectionType<Collection<T>>(targetURL, paramGET,entityDTO,configuration,paramPOST);
	}

	protected TypeConsumer<T> configureGetWithParameter(String target , IParameterGET parameter){
		this.paramGET = parameter;
		this.targetURL=target;
		return this;
	}
	
	
	protected TypeConsumer<T> configureGet(String target) {
		this.targetURL=target;
		return this;
	}
	
	// Envio via POST
	
	protected TypeConsumer<T> configurePost(String target) {
		this.targetURL=target;
		return this;
	}
	
	protected TypeConsumer<T> configurePostWithParameter(String target , IParameterPOST parameter){
		this.paramPOST = parameter;
		this.targetURL=target;
		return this;
	}
	
	public Response<T> post() throws ServiceErrorException {
		  if(paramPOST!=null){
			  return postWithParameter();
		   }
			  return postEntity();
	}

	public Response<T> postEntity() throws ServiceErrorException {
		try{	
			ResponseProtocol response = new CommunicationProtocol().httpPost(paramPOST.getParameterPost(),entityDTO,targetURL);
			
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	}

	public Response<T> postWithParameter() throws ServiceErrorException {
		try{
			String key = null;
			Set<String> chaves = paramPOST.getParameterPost().keySet();
			for (String chave : chaves) {
				key = chave;
			}
			
			ResponseProtocol response = new CommunicationProtocol().httpPost(paramPOST.getParameterPost().get(key),entityDTO,targetURL);
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	
	}
	
	/*protected Response<T> post(){
		
	}
	protected Response<T> put(){
		
	}
	protected Response<T> delete(){
		
	}
	
	
	 */	

}
