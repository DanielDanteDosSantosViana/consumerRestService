package br.gov.ancine.ws.restservice.core.searchParameter;

import java.util.HashMap;
import java.util.Map;

public class ParameterPOST implements IParameterPOST{

	private static Map<String, Integer[]> parameterPost = new HashMap<String, Integer[]>();

	public Integer[] putParameter(String Key, Integer[] value) {
		return parameterPost.put(Key, value);
	}

	public Map<String, Integer[]> getParameterPost() {
		return parameterPost;
	}
}
