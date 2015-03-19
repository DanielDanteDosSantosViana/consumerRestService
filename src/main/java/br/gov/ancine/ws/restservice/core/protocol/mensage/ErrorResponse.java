package br.gov.ancine.ws.restservice.core.protocol.mensage;

public class ErrorResponse {

	private String tipo;
	private String descricao;
	private String complemento;
	
	
	public ErrorResponse(String tipo , String descricao , String complemento){
		this.tipo = tipo;
		this.descricao = descricao;
		this.complemento = complemento;
	}

	public ErrorResponse(){
		
	}


	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
