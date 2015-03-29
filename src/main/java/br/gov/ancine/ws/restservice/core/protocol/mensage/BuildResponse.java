package br.gov.ancine.ws.restservice.core.protocol.mensage;



public class BuildResponse<T> implements Build<T> {
	
	private final int HTTP_OK = 200;
	private int statusCode;
	private Object objectParser;
	private T dtoParser;
	private ErrorResponse error;
	
	public BuildResponse(final int statusCode ,final Object objectParser ){
		this.statusCode = statusCode;
		this.objectParser = objectParser;

	}
	public Response<T> build(){
		
		if(HTTP_OK!=statusCode){
			return badRequest();
		}
		return goodRequest();
		
	}
	
	public Response<T> goodRequest(){
		dtoParser = (T)objectParser;
		return new ResponseConsumer<T>(dtoParser,null);
	}
	public Response<T> badRequest(){
		error = (ErrorResponse)objectParser;
		return new ResponseConsumer<T>(null,error);
	}
	
}
