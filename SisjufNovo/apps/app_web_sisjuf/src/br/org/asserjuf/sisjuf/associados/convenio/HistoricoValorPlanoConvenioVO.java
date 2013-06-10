package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class HistoricoValorPlanoConvenioVO extends SmartVO {

	private Integer codigo;
	private PlanoConvenioVO planoConvenio;
	private Double		valor;
	private Date		dataInicio;
	private Date		dataCadastro;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public PlanoConvenioVO getPlanoConvenio() {
		return planoConvenio;
	}
	public void setPlanoConvenio(PlanoConvenioVO planoConvenio) {
		this.planoConvenio = planoConvenio;
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
	public Date getDataCadasrto() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	


}
