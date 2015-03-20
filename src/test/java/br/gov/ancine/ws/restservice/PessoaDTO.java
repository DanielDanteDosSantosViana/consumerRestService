package br.gov.ancine.ws.restservice;

import java.util.List;

public class PessoaDTO {
	
	private String idPessoa;
	private List<EnderecoDTO> enderecos;

	public String getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}
	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

}
