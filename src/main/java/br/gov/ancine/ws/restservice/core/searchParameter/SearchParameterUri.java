package br.gov.ancine.ws.restservice.core.searchParameter;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchParameterUri implements Search{

	static final Logger logger = LoggerFactory.getLogger(SearchParameterUri.class);

	protected String target;
	protected Parameter searchParameter;
	
	
	
	public SearchParameterUri(String target , Parameter ParamUri){
		this.target = target;
		this.searchParameter = ParamUri;
	}
	public String getTarget() {
		   modifyTargetUri();
		return target;
	}

	public void modifyTargetUri(){insertParameterToUrl();}
	
	private void insertParameterToUrl() {
		if(searchParameter!=null && !searchParameter.getParameter().isEmpty()){
			   Set<String> keys = searchParameter.getParameter().keySet();
			   for (String chave : keys) {
				  if(target.contains(chave)){
					  target = target.replace(chave,searchParameter.getParameter().get(chave)); 
				  }
			   }
		  	 }
	}





	
	
	 


}
