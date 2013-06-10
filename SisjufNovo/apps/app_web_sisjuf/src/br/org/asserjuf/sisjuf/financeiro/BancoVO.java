package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;

/**
 * 
 * BancoVO congrega os dados de banco da Asserjuf.
 * 
 * @author Rodrigo Falc�o
 *
 */
public class BancoVO implements Serializable{

	
	/**
	 * C�digo seq�encial interno do banco.
	 */
	private Integer codigo;

	/**
	 * Texto que representa o nome do banco.
	 */
	private String 	nome;
	
	/**
	 * Texto que representa o n�mero do banco.
	 */
	private String	numero;
	
	/**
	 * Texto que representa a sigla do banco.
	 */
	private String 	sigla;
	
	/**
	 * Obt�m o valor da propriedade sigla.
	 * @return Retorna a sigla do banco.
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * Seta o valor recebido como par�metro na propriedade sigla do objeto.
	 * @param sigla Texto contendo a sigla que se deseja setar.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	/**
	 * Obt�m o valor da propriedade numero.
	 * @return Retorna o n�mero do banco.
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Seta o valor recebido como par�metro na propriedade numero do objeto.
	 * @param numero Texto contendo o n�mero que se deseja setar.
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Obt�m o valor da propriedade codigo.
	 * @return Retorna o c�digo seq�encial interno (pertencente ao Sisjuf) do banco.
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Seta o valor recebido como par�metro na propriedade codigo do objeto.
	 * @param codigo N�mero contendo o c�digo que se deseja setar.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m o valor da propriedade nome.
	 * @return Retorna o nome do banco.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Seta o valor recebido como par�metro na propriedade nome do objeto.
	 * @param nome Texto contendo o nome que se deseja setar.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
