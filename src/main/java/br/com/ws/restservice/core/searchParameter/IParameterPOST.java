package br.com.ws.restservice.core.searchParameter;

import java.util.Map;

public interface IParameterPOST {

	Integer[] putParameter(String Key, Integer[] value);
	
	Map<String, Integer[]> getParameterPost();
}
