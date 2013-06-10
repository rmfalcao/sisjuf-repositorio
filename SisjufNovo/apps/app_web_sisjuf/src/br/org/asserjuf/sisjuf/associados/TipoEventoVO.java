package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;

public class TipoEventoVO extends SmartVO {

	private Short codigo;
	private String nome;
	
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
