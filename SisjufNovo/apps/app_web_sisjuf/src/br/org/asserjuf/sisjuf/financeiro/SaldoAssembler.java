package br.org.asserjuf.sisjuf.financeiro;

import java.util.Date;

/**
 * Congrega os dados necessários na operação de obtenção de saldo.
 * @author Paulo Prado
 *
 */
public class SaldoAssembler {

	/**
	 * Objeto que representa a conta que se deseja obter o saldo.
	 */
	private ContaVO contaVO; 
	
	/**
	 * Data em que se deseja saber o saldo.
	 */
	private Date data;
	
	/**
	 * Obtém o objeto da propriedade contaVO.
	 * @return Conta que se deseja obter o saldo
	 */
	public ContaVO getContaVO() {
		return contaVO;
	}
	
	/**
	 * Seta o objeto da propriedade contaVO.
	 * @param contaVO Objeto que se deseja setar na propriedade
	 */
	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}

	/**
	 * Obtém o valor da propriedade data.
	 * @return Data do saldo
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Seta o valor da propriedade data.
	 * @param data Valor que se deseja setar na propriedade
	 */
	public void setData(Date data) {
		this.data = data;
	}
	
}
