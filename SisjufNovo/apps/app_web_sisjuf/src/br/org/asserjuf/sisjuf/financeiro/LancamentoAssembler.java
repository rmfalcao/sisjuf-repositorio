package br.org.asserjuf.sisjuf.financeiro;

import java.text.NumberFormat;
import java.util.Collection;

/**
 * Congrega os dados a serem retornados pela opera��o de consulta de lan�amento.
 * @author Paulo Prado
 *
 */
public class LancamentoAssembler  {

	/**
	 * Diretor financeiro na data do �ltimo lan�amento.
	 */
	private DiretorFinanceiroVO diretorFinanceiroVO;
	
	/**
	 * Cole��o dos lan�amentos ocorridos no per�odo da consulta.
	 */
	private Collection	lancamentos;
		
	/**
	 * Obt�m a cole��o da propriedade lancamentos.
	 * @return Cole��o dos lan�amentos ocorridos no per�odo da consulta
	 */
	public Collection<LancamentoVO> getLancamentos() {
		return lancamentos;
	}
	
	/**
	 * Seta na propriedade lancamentos a cole��o dos lan�amentos do p�r�odo da consulta. 
	 * @param lancamento Valor que se deseja setar na propriedade
	 */
	public void setLancamentos(Collection lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	/**
	 * Obt�m o valor da propriedade diretorFinanceiro
	 * @return Diretor Financeiro na data do �ltimo lan�amento
	 */
	public DiretorFinanceiroVO getDiretorFinanceiroVO() {
		return diretorFinanceiroVO;
	}

	/**
	 * Seta o valor da propriedade diretorFinanceiro.
	 * @param diretorFinanceiro Valor que se deseja setar na propriedade
	 */
	public void setDiretorFinanceiroVO(DiretorFinanceiroVO vo) {
		this.diretorFinanceiroVO = vo;
	}
		
}
