package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.util.Date;

public class LancamentoFiltroAssembler implements Serializable{
	
	private Date dataEfetivacaoInicial;
	private Date dataEfetivacaoFinal;
	private Date dataPrevisaoInicial;
	private Date dataPrevisaoFinal;
	private String flgPrevisao;
	private String flgEfetivacao;
	private ContaVO contaVO;
	private OrigemLancamentoVO origemLancamentoVO;
	private TipoLancamentoVO tipoLancamentoVO;
	private TipoOperacaoVO tipoOperacaoVO;
	private Double valorMenor;
	private Double valorMaior;	
	private String flgNaoQuitado;
	private Boolean flgEfetivadoOuPrevisto;
	
	public Boolean getFlgEfetivadoOuPrevisto() {
		return flgEfetivadoOuPrevisto;
	}
	public void setFlgEfetivadoOuPrevisto(Boolean flgEfetivadoOuPrevisto) {
		this.flgEfetivadoOuPrevisto = flgEfetivadoOuPrevisto;
	}
	public TipoLancamentoVO getTipoLancamentoVO() {
		return tipoLancamentoVO;
	}
	public void setTipoLancamentoVO(TipoLancamentoVO tipoLancamentoVO) {
		this.tipoLancamentoVO = tipoLancamentoVO;
	}
	public ContaVO getContaVO() {
		return contaVO;
	}
	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}
	public String getFlgEfetivacao() {
		return flgEfetivacao;
	}
	public void setFlgEfetivacao(String flgEfetivacao) {
		this.flgEfetivacao = flgEfetivacao;
	}
	public String getFlgNaoQuitado() {
		return flgNaoQuitado;
	}
	public void setFlgNaoQuitado(String flgNaoQuitado) {
		this.flgNaoQuitado = flgNaoQuitado;
	}
	public String getFlgPrevisao() {
		return flgPrevisao;
	}
	public void setFlgPrevisao(String flgPrevisao) {
		this.flgPrevisao = flgPrevisao;
	}
	public OrigemLancamentoVO getOrigemLancamentoVO() {
		return origemLancamentoVO;
	}
	public void setOrigemLancamentoVO(OrigemLancamentoVO origemLancamentoVO) {
		this.origemLancamentoVO = origemLancamentoVO;
	}
	public TipoOperacaoVO getTipoOperacaoVO() {
		return tipoOperacaoVO;
	}
	public void setTipoOperacaoVO(TipoOperacaoVO tipoOperacaoVO) {
		this.tipoOperacaoVO = tipoOperacaoVO;
	}
	
	public Double getValorMaior() {
		return valorMaior;
	}
	public void setValorMaior(Double valorMaior) {
		this.valorMaior = valorMaior;
	}
	public Double getValorMenor() {
		return valorMenor;
	}
	public void setValorMenor(Double valorMenor) {
		this.valorMenor = valorMenor;
	}
	public Date getDataEfetivacaoFinal() {
		return dataEfetivacaoFinal;
	}
	public void setDataEfetivacaoFinal(Date dataEfetivacaoFinal) {
		this.dataEfetivacaoFinal = dataEfetivacaoFinal;
	}
	public Date getDataEfetivacaoInicial() {
		return dataEfetivacaoInicial;
	}
	public void setDataEfetivacaoInicial(Date dataEfetivacaoInicial) {
		this.dataEfetivacaoInicial = dataEfetivacaoInicial;
	}
	public Date getDataPrevisaoFinal() {
		return dataPrevisaoFinal;
	}
	public void setDataPrevisaoFinal(Date dataPrevisaoFinal) {
		this.dataPrevisaoFinal = dataPrevisaoFinal;
	}
	public Date getDataPrevisaoInicial() {
		return dataPrevisaoInicial;
	}
	public void setDataPrevisaoInicial(Date dataPrevisaoInicial) {
		this.dataPrevisaoInicial = dataPrevisaoInicial;
	}

}
