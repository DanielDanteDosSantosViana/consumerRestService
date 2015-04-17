/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.ws.restservice.core.consumer;

import java.util.Collection;

import br.com.ws.restservice.core.exception.ComunicationProtocolException;
import br.com.ws.restservice.core.exception.ParserJsonException;
import br.com.ws.restservice.core.exception.ServiceErrorException;
import br.com.ws.restservice.core.parser.ParserJSON;
import br.com.ws.restservice.core.protocol.CommunicationProtocol;
import br.com.ws.restservice.core.protocol.mensage.BuildResponse;
import br.com.ws.restservice.core.protocol.mensage.Response;
import br.com.ws.restservice.core.protocol.mensage.ResponseProtocol;
import br.com.ws.restservice.core.searchParameter.IParameter;
import br.com.ws.restservice.core.searchParameter.SearchParameterUri;


/**
 * @author daniel
 *
 * @param <T>
 */
public final class TypeConsumer<T> implements ITypeConsumer<T> {

	protected ParserJSON parser = new ParserJSON();
	private Class<?> entityDTO;
	protected String targetURL;
	protected IParameter param;

	
	public TypeConsumer(Class<?> entityDTO){
		this.entityDTO = entityDTO;		
	}

	private Response<T> getWithParameter() throws ServiceErrorException {
		try{
		
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,param).getTarget());
			param.cleanMap();
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		}
	
	}
	private Response<T> getEntity() throws ServiceErrorException{
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
	  if(param!=null){
		  return getWithParameter();
	   }
		  return getEntity();
	}
	

	public ReturnCollectionType<Collection<T>> configureReturnCollection(Class<? extends Collection> configuration){		
		return new ReturnCollectionType<Collection<T>>(targetURL, param,entityDTO,configuration);
	}

	protected TypeConsumer<T> configureGetWithParameter(String target , IParameter parameter){
		this.param = parameter;
		this.targetURL=target;
		return this;
	}
	
	
	protected TypeConsumer<T> configureGet(String target) {
		this.targetURL=target;
		return this;
	}
	
	protected TypeConsumer<T> configurePost(String target) {
		this.targetURL=target;
		return this;
	}
	
	protected TypeConsumer<T> configurePostWithParameter(String target , IParameter parameter){
		this.param = parameter;
		this.targetURL=target;
		return this;
	}
	
	public Response<T> post() throws ServiceErrorException {
		  if(param!=null){
			  return postWithParameter();
		   }
			  return postEntity();
	}

	private Response<T> postEntity() throws ServiceErrorException {
		try{	
			ResponseProtocol response = new CommunicationProtocol().httpPost(targetURL);
			
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	}

	private Response<T> postWithParameter() throws ServiceErrorException {
		try
		{
			ResponseProtocol response = new CommunicationProtocol().httpPost(param,entityDTO,targetURL);
			param.cleanMap();
			return new BuildResponse<T>(response.getStatusCode(),entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	
	}
	
	/*
	protected Response<T> put(){
		
	}
	protected Response<T> delete(){
		
	}
	
	
	 */	

}
