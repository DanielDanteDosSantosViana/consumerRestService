package br.gov.ancine.ws.restservice;

public class MunicipioDTO {
	
	private Long id;
	private String nome;
	
	public MunicipioDTO(Long id , String nome){
		this.id = id;
		this.nome = nome;
	}

	public MunicipioDTO(){}
	

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
}
