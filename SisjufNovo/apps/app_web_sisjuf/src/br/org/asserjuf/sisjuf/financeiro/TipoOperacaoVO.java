package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * Congrega os dados de um tipo de operação de lançamento.
 * 
 * @author Paulo Prado
 *
 */
public class TipoOperacaoVO implements Serializable{

	/**
	 * Código interno do tipo de operação.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo do tipo de operação do lançamento.
	 */
	private String nome;
	
	/**
	 * Sigla do tipo de operação.
	 */
	private String sigla;
	
	/**
	 * Flag que indica se o tipo de operação é de previsão.
	 */
	private String flgPrevisao;
	
	/**
	 * Flag que indica se o tipo de operação é de débito e deve ser lançado negativo.
	 */
	private String flgDebito;
		
	/**
	 * Construtor sem parâmetros da classe (não faz nada). 
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
	 * Obtém o valor da propriedade flgDebito.
	 * @return Flag que indica se o tipo de operação é de débito
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
	 * Obtém o valor da propriedade flgPrevisao.
	 * @return Flag que indica se o tipo de operação é de previsão
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
	 * Obtém o valor da propriedade codigo.
	 * @return Código interno do tipo de operação
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
	 * @return Nome do tipo de operação
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
	 * Obtém o valor da propriedade sigla.
	 * @return Sigla do tipo de operação
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
