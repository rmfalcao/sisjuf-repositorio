package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * Congrega os dados de uma origem de lan�amento da Asserjuf.
 * 
 * @author Paulo Prado
 *
 */
public class OrigemLancamentoVO implements Serializable{
	
	/**
	 * codigo interno da origem de lan�amento.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo da origem de lan�amento.
	 */
	private String nome;
	
	/**
	 * Flag que indica se essa origem � ativa para lan�amento manual.
	 */
	private String flgAtivo;
	
	/**
	 * Flag que indica se a origem de lan�amento pode ser apagado ou editada.
	 */
	private String flgNaoApagavel;
	
	/**
	 * Construtor sem par�metros da classe (n�o faz nada). 
	 */
	public OrigemLancamentoVO() {	}

	/**
	 * Construtor que inicializa a propriedade codigo do objeto instanciado.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public OrigemLancamentoVO(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obt�m o valor da propriedade flgNaoApagavel.
	 * @return Flag que indica se a origem de lan�amento pode ser apagado ou editada
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
	 * Obt�m o valor da propriedade codigo.
	 * @return C�digo interno da origem de lan�amento
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
	 * Obt�m o valor da propriedade flgAtivo.
	 * @return Flag que indica se a origem de lan�amento � ativa
	 */
	public String getFlgAtivo() {
		return flgAtivo;
	}
	
	/**
	 * Seta o valor da propriedade flgAtivo.
	 * @param flgAtivo Valor que se deseja setar na propriedade
	 */
	public void setFlgAtivo(String flgAtivo) {
		this.flgAtivo = flgAtivo;
	}
	
	/**
	 * Obt�m o valor da propriedade nome.
	 * @return Nome descritivo da origem de lan�amento
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o valor da propriedade nome.
	 * @param nome Valor que se deseja setar na propriedade
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
