package br.org.asserjuf.sisjuf.entidadesComuns;

import br.com.falc.smartFW.SmartVO;

public class EstadoVO extends SmartVO {

	private Short	codigo;
	private String	sigla;
	private String	nome;
	
	public Short getCodigo() {
		return codigo;
	}
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
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
