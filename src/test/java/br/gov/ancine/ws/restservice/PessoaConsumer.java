package br.gov.ancine.ws.restservice;

import java.util.Collection;
import java.util.List;

import br.gov.ancine.ws.restservice.core.consumer.Consumer;
import br.gov.ancine.ws.restservice.core.exception.ServiceErrorException;
import br.gov.ancine.ws.restservice.core.protocol.mensage.ErrorResponse;
import br.gov.ancine.ws.restservice.core.protocol.mensage.Response;
import br.gov.ancine.ws.restservice.core.searchParameter.IParameterGET;
import br.gov.ancine.ws.restservice.core.searchParameter.IParameterPOST;
import br.gov.ancine.ws.restservice.core.searchParameter.ParameterGET;
import br.gov.ancine.ws.restservice.core.searchParameter.ParameterPOST;


public class PessoaConsumer extends Consumer<PessoaDTO>  {

	public Response<Collection<PessoaDTO>> getPessoas() throws ServiceErrorException{
		
		return consumer("http://127.0.0.1:8080/gerador-rest/pessoa/collectio").configureReturnCollection(List.class).get();
		
	}
	
	public Response<PessoaDTO> getPessoa() throws ServiceErrorException{
		IParameterGET param = new ParameterGET();
		param.putParameter("{idPessoa}", "203577");
		
		return consumer("http://jb-hml-mig:8080/agenteeconomico-servicos/agenteeconomico/{idPessoa}/complexos",param).get();	
	}
	
	public Response<Collection<PessoaDTO>> getPessoaPost() throws ServiceErrorException{
		IParameterPOST param = new ParameterPOST();
		param.putParameter("ids", new Integer[]{206768,2461760});
		
		return consumerPost("http://jb-hml-mig:8080/agenteeconomico-servicos/agenteeconomico/complexo/salas",param).configureReturnCollection(List.class).post();	
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
			Response<Collection<PessoaDTO>> pessoaSing = pessoa.getPessoaPost();
			Collection<PessoaDTO>  pessoaDTO = pessoaSing.getMensagem();
			
			ErrorResponse error= pessoaSing.getError();
			System.out.println(error.getComplemento());
			
		} catch (ServiceErrorException e) {

			System.out.println(e.getMessage());
		}
	}

}
