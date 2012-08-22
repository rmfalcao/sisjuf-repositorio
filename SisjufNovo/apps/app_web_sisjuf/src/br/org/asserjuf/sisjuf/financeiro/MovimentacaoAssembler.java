package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.util.Date;

/**
 * Congrega os dados necessários na operação de movimentação de valores entre contas. 
 * @author Paulo Prado
 *
 */
public class MovimentacaoAssembler implements Serializable{

	/**
	 * Representa a conta de origem da movimentação de valores entre conta.
	 */
	private ContaVO contaOrigemVO;

	/**
	 * Representa a conta de destino da movimentação de valores entre conta.
	 */
	private ContaVO contaDestinoVO;

	/**
	 * Valor da movimentação de valores entre conta.
	 */
	private Double valor;

	/**
	 * Data da movimentação de valores entre conta.
	 */
	private Date data;
	
	private FormaPagamentoVO 	formaPagamentoVO;
	
	private String 				numeroCheque;
	
	
	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public FormaPagamentoVO getFormaPagamentoVO() {
		return formaPagamentoVO;
	}

	public void setFormaPagamentoVO(FormaPagamentoVO formaPagamentoVO) {
		this.formaPagamentoVO = formaPagamentoVO;
	}

	/**
	 * Obtém o valor da propriedade data.
	 * @return Data da movimentação
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
	
	/**
	 * Obtém o valor da propriedade contaDestinoVO.
	 * @return Conta de destino da movimentação
	 */
	public ContaVO getContaDestinoVO() {
		return contaDestinoVO;
	}
	
	/**
	 *  Seta o valor da propriedade contaDestinoVO.
	 * @param contaDestinoVO Valor que se deseja setar na propriedade
	 */
	public void setContaDestinoVO(ContaVO contaDestinoVO) {
		this.contaDestinoVO = contaDestinoVO;
	}
	
	/**
	 * Obtém o valor da propriedade contaOrigemVO.
	 * @return Conta de origem da movimentação
	 */
	public ContaVO getContaOrigemVO() {
		return contaOrigemVO;
	}
	
	/**
 	 *  Seta o valor da propriedade contaOrigemVO.
	 * @param contaOrigemVO Valor que se deseja setar na propriedade
	 */
	public void setContaOrigemVO(ContaVO contaOrigemVO) {
		this.contaOrigemVO = contaOrigemVO;
	}
	
	/**
	 * Obtém o valor da propriedade valor.
	 * @return Valor da movimentação
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 *  Seta o valor da propriedade valor.
	 * @param valor Valor que se deseja setar na propriedade
	*/
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
		
}
