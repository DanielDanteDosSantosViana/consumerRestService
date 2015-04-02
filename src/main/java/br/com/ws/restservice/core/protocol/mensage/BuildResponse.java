package br.com.ws.restservice.core.protocol.mensage;

import java.util.Collection;

import br.com.ws.restservice.core.exception.ParserJsonException;
import br.com.ws.restservice.core.parser.ParserJSON;



public class BuildResponse<T> implements Build<T> {
	
	private final int HTTP_OK = 200;
	private int statusCode;
	private T dtoParser;
	private ErrorResponse error;
	private Class<? extends Collection> typeCollection;
	private Class<?> entity;
	protected ParserJSON parser = new ParserJSON();
	private String json;
	
	public BuildResponse(final int statusCode ,final Class<? extends Collection> typeCollection,Class<?> entity ){
		this.statusCode = statusCode;
		this.typeCollection = typeCollection;
		this.entity = entity;

	}
	
	
	public BuildResponse(final int statusCode,Class<?> entity){
		this.statusCode = statusCode;
		this.entity = entity;

	}
	public Response<T> build(String json) throws ParserJsonException{
		this.json = json;
		
		if(HTTP_OK!=statusCode){
			return badRequest();
		}
		return goodRequest();
		
	}
	
	public Response<T> goodRequest() throws ParserJsonException{
		
		if(typeCollection!=null){
			dtoParser = (T)parserCollection(json);
			return new ResponseConsumer<T>(dtoParser,null);
		}
		dtoParser = (T)parserEntity(json);
		return new ResponseConsumer<T>(dtoParser,null);
	}
	public Response<T> badRequest() throws ParserJsonException{
		entity = ErrorResponse.class;
		error = (ErrorResponse)parserEntity(json);
		return new ResponseConsumer<T>(null,error);
	}
	
	private  Object parserEntity(String json) throws ParserJsonException{
		return   parser.fromJson(json,entity);
	}

	private  Object parserCollection(String json) throws ParserJsonException{
		return  parser.collectionfromJson(json,entity,typeCollection);
	}


}
