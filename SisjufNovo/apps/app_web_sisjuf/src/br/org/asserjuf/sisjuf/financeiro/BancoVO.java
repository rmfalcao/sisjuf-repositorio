package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * BancoVO congrega os dados de banco da Asserjuf.
 * 
 * @author Rodrigo Falcão
 *
 */
public class BancoVO implements Serializable{

	
	/**
	 * Código seqüencial interno do banco.
	 */
	private Integer codigo;

	/**
	 * Texto que representa o nome do banco.
	 */
	private String 	nome;
	
	/**
	 * Texto que representa o número do banco.
	 */
	private String	numero;
	
	/**
	 * Texto que representa a sigla do banco.
	 */
	private String 	sigla;
	
	/**
	 * Obtém o valor da propriedade sigla.
	 * @return Retorna a sigla do banco.
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * Seta o valor recebido como parâmetro na propriedade sigla do objeto.
	 * @param sigla Texto contendo a sigla que se deseja setar.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	/**
	 * Obtém o valor da propriedade numero.
	 * @return Retorna o número do banco.
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Seta o valor recebido como parâmetro na propriedade numero do objeto.
	 * @param numero Texto contendo o número que se deseja setar.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Obtém o valor da propriedade codigo.
	 * @return Retorna o código seqüencial interno (pertencente ao Sisjuf) do banco.
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Seta o valor recebido como parâmetro na propriedade codigo do objeto.
	 * @param codigo Número contendo o código que se deseja setar.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtém o valor da propriedade nome.
	 * @return Retorna o nome do banco.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Seta o valor recebido como parâmetro na propriedade nome do objeto.
	 * @param nome Texto contendo o nome que se deseja setar.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
