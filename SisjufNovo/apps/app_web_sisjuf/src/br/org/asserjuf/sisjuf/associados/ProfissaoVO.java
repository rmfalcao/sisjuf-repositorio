package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;

/**
 * Congrega os dados da entidade "profissão".
 * @author Rodrigo Falcão
 *
 */
public class ProfissaoVO extends SmartVO {

	/**
	 * Código seqüencial interno do banco.
	 */
	private Integer	codigo;
	
	/**
	 * Nome da profissão.
	 */
	private String	nome;

	/**
	 * Obtém o valor da propriedade código.
	 * @return Código seqüencial (no Sisjuf) da profissão.
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
	 * @return Nome da profissão.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Seta o valor recebido como parâmetro na propriedade nome do objeto.
	 * @param nome Texto contendo o nome da profissão.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
