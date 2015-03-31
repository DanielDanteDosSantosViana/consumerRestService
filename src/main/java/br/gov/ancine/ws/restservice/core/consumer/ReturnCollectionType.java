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

public final class ReturnCollectionType<T extends Collection> implements ITypeConsumer<T>{
	protected String targetURL;
	protected IParameterGET paramURL;
	protected IParameterPOST paramPOST;
	protected ParserJSON parser = new ParserJSON();
	private Class<?> entityDTO;
	private Class<? extends Collection> typeCollection; 
	
	
	public ReturnCollectionType(String targetURL ,IParameterGET paramURL,Class<?> entityDTO,Class<? extends Collection> typeCollection,IParameterPOST paramPost){
		this.targetURL = targetURL;
		this.paramURL = paramURL;
		this.paramPOST=paramPost;
		this.entityDTO=entityDTO;
		this.typeCollection = typeCollection;
		
	}
	public Response<T> getWithParameter() throws ServiceErrorException {
		try{
			
			ResponseProtocol response = new CommunicationProtocol().httpGet(new SearchParameterUri(targetURL,paramURL).getTarget());
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
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
	public Response<T> post() throws ServiceErrorException {
		  if(paramPOST!=null){
			  return postWithParameter();
		   }
			  return postEntity();
	}


	public Response<T> getEntity() throws ServiceErrorException {
		
		try{
			ResponseProtocol response = new CommunicationProtocol().httpGet(targetURL);
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
		} catch (ComunicationProtocolException e) {
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : "+ e.getMessage() , e);
		}
	}
	


	public Response<T> postEntity() throws ServiceErrorException {
		try{	
			ResponseProtocol response = new CommunicationProtocol().httpPost(paramPOST.getParameterPost(),entityDTO,targetURL);
			
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
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
			return new BuildResponse<T>(response.getStatusCode(),typeCollection,entityDTO).build(response.getJsonMensage());
		
		} catch (ParserJsonException e) {
			throw  new ServiceErrorException("Error ao fazer o parser do servico : " + e.getMessage() , e);
		} catch (ComunicationProtocolException e){
			throw new ServiceErrorException("Error no protocoloca de comunicacao : " + e.getMessage(), e);
		}
	
	}


}
