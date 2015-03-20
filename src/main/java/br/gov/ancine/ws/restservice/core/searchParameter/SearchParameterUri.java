package br.gov.ancine.ws.restservice.core.searchParameter;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public abstract class SearchParameterUri<T>{

	protected String target;
	 
	protected Map<String, String> searchParameter = new HashMap<String, String>();

	private Class<T> type;
	
	
	@SuppressWarnings("unchecked")
	public SearchParameterUri(String target , Map<String, String> searchParameter){
		this.target = target;
		this.searchParameter = searchParameter;
		this.type = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0]; 
		System.out.print("TESTE");
		
	}
	
	@SuppressWarnings("unchecked")
	public SearchParameterUri(String target){
		this.target =target;
		this.type = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0]; 
	}
	@SuppressWarnings("unchecked")
	public SearchParameterUri(){
		this.type = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0]; 
	}

	
	public String getTarget() {
		   modifyTargetUri();
		return target;
	
	}
	
	public void modifyTargetUri(){insertParameterToUrl();}

	protected abstract void insertParameterToUrl();
	
	public Class<T> getType() {
		return type;
	}




	
	
	 


}
