package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * Congrega os dados de forma de pagamento de um lançamento da Asserjuf.
 * @author paulo
 *
 */
public class FormaPagamentoVO  implements Serializable {
	
	/**
	 * Código seqüencial da forma de pagamento.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo da forma de pagamento.
	 */
	private String	nome;

	
	/**
	 * Obtém o valor do código da forma de pagamento.
	 * @return Código da forma de lançamento.
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Seta o valor do código da forma de pagamento.
	 * @param codigo Código da forma de pagamento.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o nome descritivo da forma de pagamento.
	 * @return Nome descritivo da forma de pagamento.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Seta o valor do nome descritivo da forma de pagamento.
	 * @param nome Nome descritivo da forma de pagamento.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
