package br.org.asserjuf.sisjuf.associados;

import java.util.Date;

import com.vortice.seguranca.vo.UsuarioVO;

import br.com.falc.smartFW.SmartVO;

public class HistoricoAssociadoVO extends SmartVO {

	
	private AssociadoVO 	associado;
	private TipoEventoVO 	tipoEvento;
	private Date			data;
	private Integer			codigo;
	
	private UsuarioVO		usuario;
	
	
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
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
