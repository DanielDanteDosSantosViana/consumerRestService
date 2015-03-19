package br.gov.ancine.ws.restservice;

public class UnidadeDTO {
	
	private Long id;
	private String nome;
	private String sigla;
	
	

	public UnidadeDTO(Long id , String nome , String sigla){
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	public UnidadeDTO(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}




}
