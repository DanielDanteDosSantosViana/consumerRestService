package br.com.ws.restservice;

public class EnderecoDTO {

		private Long idEndereco;
		private String tipo;
		private String cep;
		private String logradouro;
		private String numero;
		private String complemento;
		private String bairro;
		private UnidadeDTO unidade;
		private RegiaoDTO regiao;
		private PaisDTO pais;
		private MunicipioDTO municipio;
		
		
		public UnidadeDTO getUnidade() {
			return unidade;
		}
		public void setUnidade(UnidadeDTO unidade) {
			this.unidade = unidade;
		}
		public RegiaoDTO getRegiao() {
			return regiao;
		}
		public void setRegiao(RegiaoDTO regiao) {
			this.regiao = regiao;
		}
		public PaisDTO getPais() {
			return pais;
		}
		public void setPais(PaisDTO pais) {
			this.pais = pais;
		}
		public MunicipioDTO getMunicipio() {
			return municipio;
		}
		public void setMunicipio(MunicipioDTO municipio) {
			this.municipio = municipio;
		}
		

		public Long getIdEndereco() {
			return idEndereco;
		}
		public void setIdEndereco(Long idEndereco) {
			this.idEndereco = idEndereco;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getLogradouro() {
			return logradouro;
		}
		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

	}


