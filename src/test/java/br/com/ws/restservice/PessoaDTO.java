package br.com.ws.restservice;


public class PessoaDTO {
	
	private Integer idPessoa;
	
	private String nome;
	
	private String registroAncine;

	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the registroAncine
	 */
	public String getRegistroAncine() {
		return registroAncine;
	}

	/**
	 * @param registroAncine the registroAncine to set
	 */
	public void setRegistroAncine(String registroAncine) {
		this.registroAncine = registroAncine;
	}
	
	
	
/*	private String idPessoa;
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
	}*/

}
