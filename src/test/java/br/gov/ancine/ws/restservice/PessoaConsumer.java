package br.gov.ancine.ws.restservice;

import java.util.Collection;
import java.util.List;

import br.gov.ancine.ws.restservice.core.consumer.Consumer;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;


public class PessoaConsumer extends Consumer<PessoaDTO>  {

	public Response<Collection<PessoaDTO>> getPessoa() throws ServiceErrorException{
		
		return consumer("http://127.0.0.1:8080/gerador-rest/pessoa/collectio").configureReturnCollection(List.class).get();
		
	}
	
	public static void main(String[] args) {
		PessoaConsumer pessoa = new PessoaConsumer();
		try {
			Response<Collection<PessoaDTO>> responsePessoa = pessoa.getPessoa();
			Collection<PessoaDTO> pessoas =responsePessoa.getMensagem();
			String id = null;
			for (PessoaDTO pessoaDTO : pessoas) {
				 id = pessoaDTO.getIdPessoa();
			}
			System.out.println(id);
			
		} catch (ServiceErrorException e) {

			System.out.println(e.getMessage());
		}
	}

}
