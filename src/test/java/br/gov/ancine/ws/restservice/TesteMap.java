package br.gov.ancine.ws.restservice;

import java.util.Set;

import br.gov.ancine.ws.restservice.core.searchParameter.ParameterURI;

public class TesteMap {

	public static void main(String[] args) {
		ParameterURI.putParameter("daniel", "123");
		ParameterURI.putParameter("teste", "333");
		ParameterURI.putParameter("teste2", "33");
		
		Set<String> keys = ParameterURI.getParameter().keySet();
		
		for (String string : keys) {
			System.out.println(string);
		}
	}
}
