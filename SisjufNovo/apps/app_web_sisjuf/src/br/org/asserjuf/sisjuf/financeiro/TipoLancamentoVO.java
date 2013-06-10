package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * Congrega os dados de um tipo de lançamento da Asserjuf.
 * 
 * @author Paulo Prado
 *
 */
public class TipoLancamentoVO implements Serializable{
	
	/**
	 * Código interno do tipo de lançamento.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo do tipo de lançamento.
	 */
	private String nome;
	
	/**
	 * Flag que indica se o tipo de lançamento pode ser apagado ou editada.
	 */
	private String flgNaoApagavel;	
	
	/**
	 * Construtor sem parâmetros da classe (não faz nada). 
	 */
	public TipoLancamentoVO() {	}
	
	/**
	 * Construtor que inicializa a propriedade codigo do objeto instanciado.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public TipoLancamentoVO(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o valor da propriedade flgNaoApagavel.
	 * @return Flag que indica se o tipo de lançamento pode ser apagado ou editada
	 */
	public String getFlgNaoApagavel() {
		return flgNaoApagavel;
	}

	/**
	 * Seta o valor da propriedade flgNaoApagavel.
	 * @param flgNaoApagavel Valor que se deseja setar na propriedade
	 */
	public void setFlgNaoApagavel(String flgNaoApagavel) {
		this.flgNaoApagavel = flgNaoApagavel;
	}

	/**
	 * Obtem o valor da propriedade codigo.
	 * @return Código interno do tipo de lançamento
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
	 * Obtém o valor da propriedade nome.
	 * @return Nome descritivo do tipo de lançamento.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Seta o valor da propriedade nome
	 * @param nome Valor que se deseja setar na propriedade.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
