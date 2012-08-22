package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * Congrega os dados de forma de pagamento de um lan�amento da Asserjuf.
 * @author paulo
 *
 */
public class FormaPagamentoVO  implements Serializable {
	
	/**
	 * C�digo seq�encial da forma de pagamento.
	 */
	private Integer codigo;
	
	/**
	 * Nome descritivo da forma de pagamento.
	 */
	private String	nome;

	
	/**
	 * Obt�m o valor do c�digo da forma de pagamento.
	 * @return C�digo da forma de lan�amento.
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Seta o valor do c�digo da forma de pagamento.
	 * @param codigo C�digo da forma de pagamento.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obt�m o nome descritivo da forma de pagamento.
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
