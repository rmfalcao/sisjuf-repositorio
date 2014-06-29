package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class HistoricoAssociadoVO extends SmartVO {

	
	private AssociadoVO 	associado;
	private TipoEventoVO 	tipoEvento;
	private Date			data;
	private Integer			codigo;
	
	
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public TipoEventoVO getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEventoVO tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	
	
}
