package br.com.ws.restservice;

import java.util.Collection;
import java.util.List;

import br.com.ws.restservice.core.consumer.Consumer;
import br.com.ws.restservice.core.exception.ServiceErrorException;
import br.com.ws.restservice.core.protocol.mensage.ErrorResponse;
import br.com.ws.restservice.core.protocol.mensage.Response;
import br.com.ws.restservice.core.searchParameter.IParameter;
import br.com.ws.restservice.core.searchParameter.Parameter;


public class PessoaConsumer extends Consumer<PessoaDTO>  {

	public Response<Collection<PessoaDTO>> getPessoas() throws ServiceErrorException{
		IParameter param = new Parameter();
		param.putParameter("id", new String[]{"1","2","3"});
		
		return consumer("http://127.0.0.1:8080/gerador-rest/pessoa/pessoas",param).configureReturnCollection(List.class).post();
		
	}

	public static void main(String[] args) {
		PessoaConsumer pessoa = new PessoaConsumer();
		try {
		Response<Collection<PessoaDTO>> pessoaSing = pessoa.getPessoas();
			Collection<PessoaDTO>  pessoaDTO = pessoaSing.getMensagem();
			
			ErrorResponse error= pessoaSing.getError();
			System.out.println(error.getComplemento());
			
		} catch (ServiceErrorException e) {

			System.out.println(e.getMessage());
		}
	}

}
