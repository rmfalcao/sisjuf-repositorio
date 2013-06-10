package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

public class AssociadoFiltroAssembler extends AssociadoVO {
	
	private Date 										dataInicioAssociacao;
	private	Date 									dataFimAssociacao;
	private String									statusPossuiFilho;
	private String									statusPossuiDependente;
	private String									statusIncluirInativos;
	private String									statusPreCadastro;
	private Date 										dataInicioNascimento;
	private	Date 									dataFimNascimento;
	private FilhoVO 								filho;
	private Date										dataInicioNascimentoFilho;
	private Date										dataFimNascimentoFilho;
	private DependenteVO 						dependente;
	private Date										dataInicioNascimentoDependente;
	private Date										dataFimNascimentoDependente;
	private Double										valorInicioMensalidade;
	private Double										valorFimMensalidade;
	private HistoricoFiltroAssembler	historico;
	private Short 									numeroInicioCalcado;
	private Short 									numeroFimCalcado;

	public String getStatusIncluirInativos() {
		return statusIncluirInativos;
	}
	public void setStatusIncluirInativos(String statusIncluirInativos) {
		this.statusIncluirInativos = statusIncluirInativos;
	}
	public Date getDataFimAssociacao() {
		return dataFimAssociacao;
	}
	
	public String getStrDataFimAssociacao() {
		if (dataFimAssociacao != null){
			return dataFimAssociacao.toString();
		}
		return null;
	}
	
	public void setDataFimAssociacao(Date dataFimAssociacao) {
		this.dataFimAssociacao = dataFimAssociacao;
	}
	public Date getDataFimNascimento() {
		return dataFimNascimento;
	}
	
	public String getStrDataFimNascimento() {
		if (dataFimNascimento != null){
			return dataFimNascimento.toString();
		}
		return null;
	}
	
	public void setDataFimNascimento(Date dataFimNascimento) {
		this.dataFimNascimento = dataFimNascimento;
	}
	public Date getDataFimNascimentoDependente() {
		return dataFimNascimentoDependente;
	}
	
	public String getStrDataFimNascimentoDependente() {
		if (dataFimNascimentoDependente != null){
			return dataFimNascimentoDependente.toString();
		}
		return null;
	}
	
	public void setDataFimNascimentoDependente(Date dataFimNascimentoDependente) {
		this.dataFimNascimentoDependente = dataFimNascimentoDependente;
	}
	public Date getDataFimNascimentoFilho() {
		return dataFimNascimentoFilho;
	}
	
	public String getStrDataFimNascimentoFilho() {
		if (dataFimNascimentoFilho != null){
			return dataFimNascimentoFilho.toString();
		}
		return null;
	}
	
	public void setDataFimNascimentoFilho(Date dataFimNascimentoFilho) {
		this.dataFimNascimentoFilho = dataFimNascimentoFilho;
	}
	public Date getDataInicioAssociacao() {
		return dataInicioAssociacao;
	}
	
	public String getStrDataInicioAssociacao() {
		if (dataInicioAssociacao != null){
			return dataInicioAssociacao.toString();
		}
		return null;
	}
	
	public void setDataInicioAssociacao(Date dataInicioAssociacao) {
		this.dataInicioAssociacao = dataInicioAssociacao;
	}
	public Date getDataInicioNascimento() {
		return dataInicioNascimento;
	}
	
	public String getStrDataInicioNascimento() {
		if (dataInicioNascimento != null){
			return dataInicioNascimento.toString();
		}
		return null;
	}
	
	public void setDataInicioNascimento(Date dataInicioNascimento) {
		this.dataInicioNascimento = dataInicioNascimento;
	}
	public Date getDataInicioNascimentoDependente() {
		return dataInicioNascimentoDependente;
	}
	
	public String getStrDataInicioNascimentoDependente() {
		if (dataInicioNascimentoDependente != null){
			return dataInicioNascimentoDependente.toString();
		}
		return null;
	}
	
	public void setDataInicioNascimentoDependente(
			Date dataInicioNascimentoDependente) {
		this.dataInicioNascimentoDependente = dataInicioNascimentoDependente;
	}
	public Date getDataInicioNascimentoFilho() {
		return dataInicioNascimentoFilho;
	}
	
	public String getStrDataInicioNascimentoFilho() {
		if (dataInicioNascimentoFilho != null){
			return dataInicioNascimentoFilho.toString();
		}
		return null;
	}
	
	public void setDataInicioNascimentoFilho(Date dataInicioNascimentoFilho) {
		this.dataInicioNascimentoFilho = dataInicioNascimentoFilho;
	}
	public DependenteVO getDependente() {
		return dependente;
	}
	public void setDependente(DependenteVO dependente) {
		this.dependente = dependente;
	}
	public FilhoVO getFilho() {
		return filho;
	}
	public void setFilho(FilhoVO filho) {
		this.filho = filho;
	}
	public HistoricoFiltroAssembler getHistorico() {
		return historico;
	}
	public void setHistorico(HistoricoFiltroAssembler historico) {
		this.historico = historico;
	}
	public String getStatusPossuiDependente() {
		return statusPossuiDependente;
	}
	public void setStatusPossuiDependente(String statusPossuiDependente) {
		this.statusPossuiDependente = statusPossuiDependente;
	}
	public String getStatusPossuiFilho() {
		return statusPossuiFilho;
	}
	public void setStatusPossuiFilho(String statusPossuiFilho) {
		this.statusPossuiFilho = statusPossuiFilho;
	}
	public Double getValorFimMensalidade() {
		return valorFimMensalidade;
	}
	public void setValorFimMensalidade(Double valorFimMensalidade) {
		this.valorFimMensalidade = valorFimMensalidade;
	}
	public Double getValorInicioMensalidade() {
		return valorInicioMensalidade;
	}
	public void setValorInicioMensalidade(Double valorInicioMensalidade) {
		this.valorInicioMensalidade = valorInicioMensalidade;
	}
	public Short getNumeroFimCalcado() {
		return numeroFimCalcado;
	}
	public void setNumeroFimCalcado(Short numeroFimCalcado) {
		this.numeroFimCalcado = numeroFimCalcado;
	}
	public Short getNumeroInicioCalcado() {
		return numeroInicioCalcado;
	}
	public void setNumeroInicioCalcado(Short numeroInicioCalcado) {
		this.numeroInicioCalcado = numeroInicioCalcado;
	}
	public String getStatusPreCadastro() {
		return statusPreCadastro;
	}
	public void setStatusPreCadastro(String statusPreCadastro) {
		this.statusPreCadastro = statusPreCadastro;
	}
}