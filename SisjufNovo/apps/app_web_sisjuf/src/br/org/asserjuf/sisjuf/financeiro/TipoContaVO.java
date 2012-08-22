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
	 * código interno do tipo de conta
	 */
	private Integer codigo;
	
	/**
	 * descrição do tipo de conta
	 */
	private String descricao;
	
	/**
	 * Obtém o valor da propriedade codigo.
	 * @return Código do tipo de conta
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
	 * Obtém o valor da propriedade descricao.
	 * @return Descrição do tipo de conta
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
