package br.gov.ancine.ws.restservice.core.searchParameter;

import java.util.Map;
import java.util.Set;


public class Search<T> extends SearchParameterUri<T> {

	public Search(String target , Map<String, String> searchParameter) {
	super(target, searchParameter);	
	}
	
	public Search(String target ) {
	super(target);	
	}
	
	@Override
	protected void insertParameterToUrl() {
		if(searchParameter!=null && !searchParameter.isEmpty()){
			   Set<String> keys = searchParameter.keySet();
			   for (String chave : keys) {
				  if(target.contains(chave)){
					  target = target.replace(chave,searchParameter.get(chave)); 
				  }
			   }
		  	 }
	}


}
