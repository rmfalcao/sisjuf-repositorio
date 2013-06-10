package br.org.asserjuf.sisjuf.financeiro;

import java.text.NumberFormat;
import java.util.Collection;

/**
 * Congrega os dados a serem retornados pela operação de consulta de lançamento.
 * @author Paulo Prado
 *
 */
public class LancamentoAssembler  {

	/**
	 * Diretor financeiro na data do último lançamento.
	 */
	private DiretorFinanceiroVO diretorFinanceiroVO;
	
	/**
	 * Coleção dos lançamentos ocorridos no período da consulta.
	 */
	private Collection	lancamentos;
		
	/**
	 * Obtém a coleção da propriedade lancamentos.
	 * @return Coleção dos lançamentos ocorridos no período da consulta
	 */
	public Collection<LancamentoVO> getLancamentos() {
		return lancamentos;
	}
	
	/**
	 * Seta na propriedade lancamentos a coleção dos lançamentos do péríodo da consulta. 
	 * @param lancamento Valor que se deseja setar na propriedade
	 */
	public void setLancamentos(Collection lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	/**
	 * Obtém o valor da propriedade diretorFinanceiro
	 * @return Diretor Financeiro na data do último lançamento
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
