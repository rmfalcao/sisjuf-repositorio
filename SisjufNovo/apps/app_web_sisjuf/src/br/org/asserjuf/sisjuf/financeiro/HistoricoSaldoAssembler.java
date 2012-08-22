package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.util.Date;

/**
 * Congrega os dados necess�rios na manipula��o do hist�rico de saldo 
 * @author Paulo Prado
 *
 */
public class HistoricoSaldoAssembler implements Serializable{

	/**
	 * Conta que ter� seu hist�rico de saldo manipulado.
	 */
	private ContaVO contaVO;
	
	/**
	 * Lan�amento gerador da manipula��o do hist�rico de saldo.
	 */
	private LancamentoVO lancamentoVO;
	
	/**
	 * Data a partir da qual o hist�rico de saldo deve ser manipulado.
	 */
	private Date data;
	
	/**
	 * Construtor sem par�metros da classe (n�o faz nada). 
	 */
	public HistoricoSaldoAssembler() { }

	/**
	 * Construtor que inicializa a propriedade contaVO e data.
	 * @param contaVO Valor que se deseja setar na propriedade
	 * @param data Valor que se deseja setar na propriedade
	 */
	public HistoricoSaldoAssembler(ContaVO contaVO, Date data) {
		this.contaVO = contaVO;
		this.data = data;
	}
	
	/**
	 * Construtor que inicializa a propriedade contaVO e lancamentoVO.
	 * @param contaVO Valor que se deseja setar na propriedade
	 * @param lancamentoVO Valor que se deseja setar na propriedade
	 */
	public HistoricoSaldoAssembler(ContaVO contaVO, LancamentoVO lancamentoVO) {
		this.contaVO = contaVO;
		this.lancamentoVO = lancamentoVO;
	}

	/**
	 * Obt�m o objeto que representa o Lan�amento da propriedade lancamentoVO.
	 * @return Lan�amento gerador da manipula��o do hist�rico de saldo
	 */
	public LancamentoVO getLancamentoVO() {
		return lancamentoVO;
	}

	/**
	 * Seta o objeto da propriedade lancamentoVO.
	 * @param lancamentoVO Objeto que se deseja setar na propriedade
	 */
	public void setLancamentoVO(LancamentoVO lancamentoVO) {
		this.lancamentoVO = lancamentoVO;
	}

	/**
	 * Obt�m o objeto da propriedade contaVO.
	 * @return Conta que ter� seu hist�rico de saldo manipulado.
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
	 * Obt�m o valor da propriedade data.
	 * @return Data para manipula��o do hist�rico de saldo
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
