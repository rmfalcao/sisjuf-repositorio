package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 *  Congrega os dados primordiais de uma conta, seja de banco ou caixa.
 * 
 * @author Paulo Prado
 *
 */
public class ContaVO implements Serializable{
	
	/**
	 * Código seqüencial interno da conta.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo da conta em questão.
	 */
	private String nome;
	
	/**
	 * Flag que identifica se a conta pe caisa ou não.
	 */
	private String flgContaCaixa;
	
	/**
	 * Valor do saldo da conta. 
	 */
	private Double saldo;

	/**
	 * Obtém o valor da propriedade flgContaCaixa.
	 * @return Flag que identifica se a conta é caixa
	 */
	public String getFlgContaCaixa() {
		return flgContaCaixa;
	}

	/**
	 * Seta o valor da propriedade flgContaCaixa.
	 * @param flgContaCaixa Valor que se deseja setar na propriedade
	 */
	public void setFlgContaCaixa(String flgContaCaixa) {
		this.flgContaCaixa = flgContaCaixa;
	}

	/**
	 * Construtor sem parâmetros da classe (não faz nada). 
	 */
	public ContaVO() {	}
	
	/**
	 * Construtor que inicializa a propriedade codigo do objeto instanciado.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public ContaVO(Integer codigo) {
		this.codigo	= codigo;
	}
	
	/**
	 * Obtém o valor da propriedade codigo.
	 * @return Código interno da conta
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
	 * @return Nome descritivo da conta
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
	 * Obtém o valor da propriedade saldo.
	 * @return Valor do saldo da conta
	 */
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * Seta o valor da propriedade saldo.
	 * @param saldo Valor que se deseja setar na propriedade
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
