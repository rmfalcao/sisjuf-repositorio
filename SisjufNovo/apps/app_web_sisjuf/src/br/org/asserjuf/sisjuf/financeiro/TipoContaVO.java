package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * Congrega os dados de um tipo de conta da Asserjuf.
 * 
 * @author Paulo Prado
 *
 */
public class TipoContaVO implements Serializable{
	
	/**
	 * c�digo interno do tipo de conta
	 */
	private Integer codigo;
	
	/**
	 * descri��o do tipo de conta
	 */
	private String descricao;
	
	/**
	 * Obt�m o valor da propriedade codigo.
	 * @return C�digo do tipo de conta
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Seta o valor da propriedade codigo.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m o valor da propriedade descricao.
	 * @return Descri��o do tipo de conta
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Seta o valor da propriedade descricao
	 * @param descricao Valor que se deseja setar na propriedade
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
