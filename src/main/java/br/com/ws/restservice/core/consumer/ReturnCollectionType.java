/*===========================================================================
COPYRIGHT 2015 Daniel Viana ALL RIGHTS RESERVED.
This software cannot be copied, stored, distributed without
Daniel Viana prior authorization.
This file was made available on https://github.com/DanielDanteDosSantosViana and it
is free to be redistributed or used under Creative Commons license 2.5 br:
http://creativecommons.org/licenses/by-sa/2.5/br/
============================================================================*/
package br.com.ws.restservice.core.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import br.com.ws.restservice.core.exception.ComunicationProtocolException;
import br.com.ws.restservice.core.exception.ParserJsonException;
import br.com.ws.restservice.core.exception.ServiceErrorException;
import br.com.ws.restservice.core.parser.ParserJSON;
import br.com.ws.restservice.core.protocol.CommunicationProtocol;
import br.com.ws.restservice.core.protocol.mensage.BuildResponse;
import br.com.ws.restservice.core.protocol.mensage.Response;
import br.com.ws.restservice.core.protocol.mensage.ResponseProtocol;
import br.com.ws.restservice.core.searchParameter.Parameter;
import br.com.ws.restservice.core.searchParameter.SearchParameterUri;

public final class ReturnCollectionType<T extends Collection> implements ITypeConsumer<T>{
	protected String targetURL;
	protected Parameter param;
	protected ParserJSON parser = new ParserJSON();
	private Class<?> entityDTO;
	private Class<? extends Collection> typeCollection; 
	
	
	public ReturnCollectionType(String targetURL ,Parameter paramURL,Class<?> entityDTO,Class<? extends Collection> typeCollection){
		this.targetURL = targetURL;
		this.param = paramURL;
		this.entityDTO=entityDTO;
		this.typeCollection = typeCollection;
		
	}
	private Response<T> getWithParameter() throws ServiceErrorException {
		try{
			
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,param).getTarget());
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : "+ e.getMessage() , e);
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
	public Response<T> post() throws ServiceErrorException {
		  if(param!=null){
			  return postWithParameter();
		   }
			  return postEntity();
	}


	private Response<T> getEntity() throws ServiceErrorException {
		
		try{
			ResponseProtocol response = new CommunicationProtocol().httpGet(targetURL);
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : "+ e.getMessage() , e);
		}
	}
	


	private Response<T> postEntity() throws ServiceErrorException {
		try{	
			ResponseProtocol response = new CommunicationProtocol().httpPost(targetURL);
			
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	}

	private Response<T> postWithParameter() throws ServiceErrorException {
		try{
		
			ResponseProtocol response = new CommunicationProtocol().httpPost(param,entityDTO,targetURL);
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	
	}


}
