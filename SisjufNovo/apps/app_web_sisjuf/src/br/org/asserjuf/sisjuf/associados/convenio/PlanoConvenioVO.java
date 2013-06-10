package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;
import java.util.List;

import br.com.falc.smartFW.SmartVO;

public class PlanoConvenioVO extends SmartVO {

	private Integer		codigo;
	private ConvenioVO	convenio;
	private String		nome;
	private String		descricao;
	private Double		valor;
	private Date		dataInicio;
	private String		desativado;
	private String		dedutivel;
	private Integer		codigoHist;
	private List<HistoricoValorPlanoConvenioVO> historicoValorPlanoConvenio;
	private Boolean flagDesativado;
	private Boolean flagDedutivel;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ConvenioVO getConvenio() {
		return convenio;
	}

	public void setConvenio(ConvenioVO convenio) {
		this.convenio = convenio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getCodigoHist() {
		return codigoHist;
	}

	public void setCodigoHist(Integer codigoHist) {
		this.codigoHist = codigoHist;
	}

	public void setHistoricoValorPlanoConvenio(
			List<HistoricoValorPlanoConvenioVO> historicoValorPlanoConvenio) {
		this.historicoValorPlanoConvenio = historicoValorPlanoConvenio;
	}

	public List<HistoricoValorPlanoConvenioVO> getHistoricoValorPlanoConvenio() {
		return historicoValorPlanoConvenio;
	}

	public String getDesativado() {
		return desativado;
	}

	public void setDesativado(String desativado) {
		this.desativado = desativado;
	}

	public String getDedutivel() {
		return dedutivel;
	}

	public void setDedutivel(String dedutivel) {
		this.dedutivel = dedutivel;
	}
	
	/**
	 * Obtém um flag que informa se o plano está ativo.
	 * @return bool True/False
	 */
	public boolean getFlagDesativado() {
		
		if (this.getDesativado() == null || this.getDesativado().equals("")){
			return false;	
		}		
		if (this.getDesativado().equals("N")) {
			return false;
		} else if (this.getDesativado().equals("S")){
			return true;
		}
		
		return false;
		
	}
	
	public void setFlagDesativado(boolean pflagDesativado){
		if (Boolean.TRUE == pflagDesativado){
			this.setDesativado("S");
		}else if (Boolean.FALSE == pflagDesativado){
			this.setDesativado("N");
		}
		flagDesativado = pflagDesativado;
	}
	
	/**
	 * Obtém um flag que informa se o plano é ou não dedutivél.
	 * @return bool True/False
	 */
	public boolean getFlagDedutivel() {
		if (this.getDedutivel() == null || this.getDedutivel().equals(""))
			return false;
		
		if (this.getDedutivel().equals("S")) {
			return true;
		} else if (this.getDedutivel().equals("N")){
			return false;
		}
		
		return false;
			
	}
	
	public void setFlagDedutivel(boolean pFlagDedutivel){
		if (Boolean.FALSE == pFlagDedutivel){
			this.setDedutivel("N");
		}else if (Boolean.TRUE == pFlagDedutivel){
			this.setDedutivel("S");
		}
		flagDedutivel = pFlagDedutivel;
	}	
	
	
}