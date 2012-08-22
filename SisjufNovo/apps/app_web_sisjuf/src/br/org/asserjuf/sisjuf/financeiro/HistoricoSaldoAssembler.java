package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.util.Date;

/**
 * Congrega os dados necessários na manipulação do histórico de saldo 
 * @author Paulo Prado
 *
 */
public class HistoricoSaldoAssembler implements Serializable{

	/**
	 * Conta que terá seu histórico de saldo manipulado.
	 */
	private ContaVO contaVO;
	
	/**
	 * Lançamento gerador da manipulação do histórico de saldo.
	 */
	private LancamentoVO lancamentoVO;
	
	/**
	 * Data a partir da qual o histórico de saldo deve ser manipulado.
	 */
	private Date data;
	
	/**
	 * Construtor sem parâmetros da classe (não faz nada). 
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
	 * Obtém o objeto que representa o Lançamento da propriedade lancamentoVO.
	 * @return Lançamento gerador da manipulação do histórico de saldo
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
	 * Obtém o objeto da propriedade contaVO.
	 * @return Conta que terá seu histórico de saldo manipulado.
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
	 * @return Data para manipulação do histórico de saldo
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
