package br.org.asserjuf.sisjuf.util;

public class ParametroVO {

	private String 	nome;
	private String	valorTextual;
	
	public ParametroVO(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValorTextual() {
		return valorTextual;
	}
	public void setValorTextual(String valorTextual) {
		this.valorTextual = valorTextual;
	}
	
	
	
	
}
