package br.gov.ancine.ws.restservice;

import java.util.HashMap;
import java.util.Map;

import br.gov.ancine.ws.restservice.core.Consumer;
import br.gov.ancine.ws.restservice.core.Search;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;

public class PessoaConsumer extends Consumer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Response<PessoaDTO> getPessoaEnderecosPor(Long idPessoa) throws ServiceErrorException{
		 
		 Map<String, String> parametros = new HashMap<String, String>();
		 parametros.put("dada",idPessoa.toString());
		 

		return (Response<PessoaDTO>) consumesGet(new Search<PessoaDTO>("asdasd",parametros));

	}

}
