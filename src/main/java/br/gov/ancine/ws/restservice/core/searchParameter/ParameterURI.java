package br.gov.ancine.ws.restservice.core.searchParameter;

import java.util.HashMap;
import java.util.Map;

public final class ParameterURI {

	private static Map<String, String> parameter = new HashMap<String, String>();
	
	public static String putParameter(String key, String value){
	  return parameter.put(key, value);
	}

	public static Map<String, String> getParameter() {
		return parameter;
	}

}
