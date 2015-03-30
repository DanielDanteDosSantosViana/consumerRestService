package br.gov.ancine.ws.restservice;

import java.util.Collection;
import java.util.List;

import br.gov.ancine.ws.restservice.core.consumer.Consumer;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.mensage.ErrorResponse;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;
import br.gov.ancine.ws.restservice.core.searchParameter.Parameter;
import br.gov.ancine.ws.restservice.core.searchParameter.ParameterURI;


public class PessoaConsumer extends Consumer<PessoaDTO>  {

	public Response<Collection<PessoaDTO>> getPessoas() throws ServiceErrorException{
		
		return consumer("http://127.0.0.1:8080/gerador-rest/pessoa/collectio").configureReturnCollection(List.class).get();
		
	}
	
	public Response<PessoaDTO> getPessoa() throws ServiceErrorException{
		Parameter param = new ParameterURI();
		param.putParameter("{idPessoa}", "1");
		
		return consumer("http://localhost:3000/gerador-rest/{idPessoa}/endereco",param).get();
		
	}
	
	public static void main(String[] args) {
		PessoaConsumer pessoa = new PessoaConsumer();
		try {
/*			Response<Collection<PessoaDTO>> responsePessoa = pessoa.getPessoas();
			Collection<PessoaDTO> pessoas =responsePessoa.getMensagem();
			String id = null;
			for (PessoaDTO pessoaDTO : pessoas) {
				 id = pessoaDTO.getIdPessoa();
			}
			System.out.println(id);
			*/
			Response<PessoaDTO> pessoaSing = pessoa.getPessoa();
			PessoaDTO  pessoaDTO = pessoaSing.getMensagem();
			if(pessoaDTO!=null){
				System.out.println(pessoaDTO.getIdPessoa());
			}
			ErrorResponse error= pessoaSing.getError();
			System.out.println(error.getComplemento());
			
		} catch (ServiceErrorException e) {

			System.out.println(e.getMessage());
		}
	}

}
