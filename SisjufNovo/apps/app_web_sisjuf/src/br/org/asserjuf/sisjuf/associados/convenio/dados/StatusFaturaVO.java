package br.org.asserjuf.sisjuf.associados.convenio.dados;

import br.com.falc.smartFW.SmartVO;

public class StatusFaturaVO extends SmartVO {

	private Short 	codigo;
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
	
	
	
}
