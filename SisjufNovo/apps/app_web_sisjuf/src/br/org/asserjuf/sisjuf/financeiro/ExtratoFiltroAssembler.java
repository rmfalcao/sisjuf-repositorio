package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.util.Date;

/**
 * Congrega os dados necess�rios para o filtro da opera��o de extrato.
 * @author Paulo Prado
 *
 */
public class ExtratoFiltroAssembler implements Serializable{
	
	/**
	 * Representa a conta que se deseja obter o extrato.
	 */
	private ContaVO contaVO;
	
	/**
	 * Data inicial do per�odo que se deseja obter o extrato.
	 */
	private Date dataInicial;
	
	/**
	 * Data final do per�odo que se deseja obter o extrato.
	 */
	private Date dataFinal;
	
	/**
	 * Obt�m o objeto que representa a conta do extrato.
	 * @return Objeto que representa a conta
	 */
	public ContaVO getContaVO() {
		return contaVO;
	}
	
	/**
	 * Seta o objeto de conta na propriedade contaVO. 
	 * @param contaVO Objeto que representa a conta que se deseja setar na propriedade
	 */
	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}

	/**
	 * Obt�m o valor da propriedade dataFinal.
	 * @return Data final do per�odo que se deseja obter o extrato 
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * Seta o valor da propriedade dataFinal.
	 * @param dataFinal Valor que se deseja setar na propriedade 
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * Obt�m o valor da propriedade dataInicial.
	 * @return Data inicial do per�odo que se deseja obter o extrato 
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * Seta o valor da propriedade dataInicial.
	 * @param dataInicial Valor que se deseja setar na propriedade 
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
}
