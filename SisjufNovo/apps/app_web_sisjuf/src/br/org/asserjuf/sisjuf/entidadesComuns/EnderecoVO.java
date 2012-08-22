package br.org.asserjuf.sisjuf.entidadesComuns;

import br.com.falc.smartFW.SmartVO;

public class EnderecoVO extends SmartVO {
	
	private Integer 			codigo;
	private String 			logradouro;
	private String			numero;
	private String			complemento;
	private String			bairro;
	private Integer			cep;
	private MunicipioVO 	municipio;
	
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public MunicipioVO getMunicipio() {
		return municipio;
	}
	public void setMunicipio(MunicipioVO municipio) {
		this.municipio = municipio;
	}
	public Integer getCep() {
		return cep;
	}
	public void setCep(Integer cep) {
		this.cep = cep;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
	
	

}
