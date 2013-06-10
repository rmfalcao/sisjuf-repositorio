package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class HistoricoFiltroAssembler extends SmartVO {

	private Date 	dataInicio;
	private Date	dataFim;
	private TipoEventoVO tipoEvento;
	
	public Date getDataFim() {
		return dataFim;
	}
	
	public String getDataFimStr(){
		if (dataFim != null)
			return dataFim.toString();
		return null;
	}
	
	public String getDataInicioStr(){
		if (dataInicio != null)
			return dataInicio.toString();
		return null;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public TipoEventoVO getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEventoVO tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
	
}
