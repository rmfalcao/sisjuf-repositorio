package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * Congrega os dados de um tipo de opera��o de lan�amento.
 * 
 * @author Paulo Prado
 *
 */
public class TipoOperacaoVO implements Serializable{

	/**
	 * C�digo interno do tipo de opera��o.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo do tipo de opera��o do lan�amento.
	 */
	private String nome;
	
	/**
	 * Sigla do tipo de opera��o.
	 */
	private String sigla;
	
	/**
	 * Flag que indica se o tipo de opera��o � de previs�o.
	 */
	private String flgPrevisao;
	
	/**
	 * Flag que indica se o tipo de opera��o � de d�bito e deve ser lan�ado negativo.
	 */
	private String flgDebito;
		
	/**
	 * Construtor sem par�metros da classe (n�o faz nada). 
	 */
	public TipoOperacaoVO() {  }
	
	/**
	 * Construtor que inicializa a propriedade codigo do objeto instanciado.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public TipoOperacaoVO(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obt�m o valor da propriedade flgDebito.
	 * @return Flag que indica se o tipo de opera��o � de d�bito
	 */
	public String getFlgDebito() {
		return flgDebito;
	}

	/**
	 * Seta o valor da propriedade flgDebito. 
	 * @param flgDebito Valor que se deseja setar na propriedade
	 */
	public void setFlgDebito(String flgDebito) {
		this.flgDebito = flgDebito;
	}

	/**
	 * Obt�m o valor da propriedade flgPrevisao.
	 * @return Flag que indica se o tipo de opera��o � de previs�o
	 */
	public String getFlgPrevisao() {
		return flgPrevisao;
	}

	/**
	 * Seta o valor da propriedade flgPrevisao.
	 * @param flgPrevisao Valor que se deseja setar na propriedade
	 */
	public void setFlgPrevisao(String flgPrevisao) {
		this.flgPrevisao = flgPrevisao;
	}

	/**
	 * Obt�m o valor da propriedade codigo.
	 * @return C�digo interno do tipo de opera��o
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
	 * Obt�m o valor da propriedade nome.
	 * @return Nome do tipo de opera��o
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
	
	/**
	 * Obt�m o valor da propriedade sigla.
	 * @return Sigla do tipo de opera��o
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * Seta o valor da propriedade sigla.
	 * @param sigla Valor que se deseja setar na propriedade
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}
