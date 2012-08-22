package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;

/**
 * Congrega os dados da entidade "profiss�o".
 * @author Rodrigo Falc�o
 *
 */
public class ProfissaoVO extends SmartVO {

	/**
	 * C�digo seq�encial interno do banco.
	 */
	private Integer	codigo;
	
	/**
	 * Nome da profiss�o.
	 */
	private String	nome;

	/**
	 * Obt�m o valor da propriedade c�digo.
	 * @return C�digo seq�encial (no Sisjuf) da profiss�o.
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
	 * @return Nome da profiss�o.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Seta o valor recebido como par�metro na propriedade nome do objeto.
	 * @param nome Texto contendo o nome da profiss�o.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
