package br.org.asserjuf.sisjuf.financeiro;

import br.org.asserjuf.sisjuf.financeiro.BancoVO;

/**
 * 
 *  Congrega os dados de uma conta de banco.
 * 
 * @author Paulo Prado
 *
 */
public class ContaBancoVO extends ContaAssociadoVO {


	
	/**
	 * Nome do titular da conta.
	 */
	private String nomTitular;
	
	/**
	 * CPF ou CNPJ do titular da conta
	 */
	private String cpfCnpjTitular;

	/**
	 * Construtor sem parâmetros da classe (não faz nada). 
	 */
	public ContaBancoVO() {
		
	}
	
	/**
	 * Construtor que inicializa a propriedade codigo do objeto instanciado.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public ContaBancoVO(Integer codigo) {
		super(codigo);
	}
	

	
	/**
	 * Obtém o valor da propriedade cpfCnpjTitular.
	 * @return CPF/CNPJ do titular da conta
	 */
	public String getCpfCnpjTitular() {
		return cpfCnpjTitular;
	}
	
	/**
	 * Seta o valor da propriedade cpfCnpjTitular.
	 * @param cpfCnpjTitular Valor que se deseja setar na propriedade
	 */
	public void setCpfCnpjTitular(String cpfCnpjTitular) {
		this.cpfCnpjTitular = cpfCnpjTitular;
	}
	

	
	/**
	 * Obtém o valor da propriedade nomTitular.
	 * @return Nome do titular da conta
	 */
	public String getNomTitular() {
		return nomTitular;
	}
	
	/**
	 * Seta o valor da propriedade nomTitular.
	 * @param nomTitular Valor que se deseja setar na propriedade
	 */
	public void setNomTitular(String nomTitular) {
		this.nomTitular = nomTitular;
	}
	

		
}
