package br.org.asserjuf.sisjuf.financeiro;

import java.text.NumberFormat;
import java.util.Collection;

/**
 * Congrega os dados a serem retornados pela opera��o de extrato.
 * @author Paulo Prado
 *
 */
public class ExtratoAssembler {

	
	/**
	 * Conta do extrato
	 */
	private ContaBancoVO		contaBanco;
	
	public ContaBancoVO getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(ContaBancoVO contaBanco) {
		this.contaBanco = contaBanco;
	}

	/**
	 * Saldo da conta no in�cio do per�odo do extrato.
	 */
	private Double 		saldoInicial;

	/**
	 * Saldo da conta no final do per�odo do extrato.
	 */
	private Double 		saldoFinal;
	
	/**
	 * Cole��o das baixas de lan�amentos ocorridos no per�odo do extrato.
	 */
	private Collection	baixaLancamento;
	
	private DiretorFinanceiroVO diretorFinanceiroVO;
	
	
	public DiretorFinanceiroVO getDiretorFinanceiroVO() {
		return diretorFinanceiroVO;
	}

	public void setDiretorFinanceiroVO(DiretorFinanceiroVO diretorFinanceiroVO) {
		this.diretorFinanceiroVO = diretorFinanceiroVO;
	}

	/**
	 * Obt�m a cole��o da propriedade baixaLancamento.
	 * @return Cole��o das baixas de lan�amentos no per�odo do extrato
	 */
	public Collection getBaixaLancamento() {
		return baixaLancamento;
	}
	
	/**
	 * Seta na propriedade baixaLancamento a cole��o das baixas do p�r�odo do extrato. 
	 * @param baixaLancamento Valor que se deseja setar na propriedade
	 */
	public void setBaixaLancamento(Collection baixaLancamento) {
		this.baixaLancamento = baixaLancamento;
	}
	
	/**
	 * Obt�m o valor da propriedade saldoFinal.
	 * @return Saldo da conta no final do per�odo do extrato
	 */
	public Double getSaldoFinal() {
		return saldoFinal;
	}

	/**
	 * Seta o valor da propriedade saldoFinal.
	 * @param saldoFinal Valor que se deseja setar na propriedade
	 */
	public void setSaldoFinal(Double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	
	/**
	 * Obt�m o valor da propriedade saldoInicial.
	 * @return Saldo da conta no inicial do per�odo do extrato
	 */
	public Double getSaldoInicial() {
		return saldoInicial;
	}
	
	/**
	 * Seta o valor da propriedade saldoInicial.
	 * @param saldoInicial Valor que se deseja setar na propriedade
	 */
	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
		
	/**
	 * Obt�m o valor do saldo inicial formatado para exibi��o na interface
	 * @return Saldo inicial formatado
	 */
	public String getSaldoInicialFormatado() {
		if (saldoInicial != null) {
			NumberFormat	 numberFormatter	= NumberFormat.getInstance();
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(saldoInicial);
			
		}
		return "";
	}
	
	/**
	 * Obt�m o valor do saldo final formatado para exibi��o na interface
	 * @return Saldo final formatado
	 */
	public String getSaldoFinalFormatado() {
		if (saldoFinal != null) {
			NumberFormat	 numberFormatter	= NumberFormat.getInstance();
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(saldoFinal);
			
		}
		return "";
	}
	
}
