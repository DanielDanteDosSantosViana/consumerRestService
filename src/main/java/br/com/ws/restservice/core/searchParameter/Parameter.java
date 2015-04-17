package br.com.ws.restservice.core.searchParameter;

import java.util.HashMap;
import java.util.Map;

public final class Parameter implements IParameter {

	private static Map<String, Object> parameter = new HashMap<String, Object>();
	
	public void putParameter(String key, Object value){
	   parameter.put(key, value);
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}

	public void cleanMap() {
		parameter.clear();
		
	}

}
