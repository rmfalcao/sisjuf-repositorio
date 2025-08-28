package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;

public class InconsistenciaNucreVO extends SmartVO {

	private Long cpf;
	private String nome;
	private String categoriaAssociado;
	private Float valorConsignado;
	private Float valorPrevisto;
	private String tipoInconsistencia;
	
	
	public String getCategoriaAssociado() {
		return categoriaAssociado;
	}
	public void setCategoriaAssociado(String categoriaAssociado) {
		this.categoriaAssociado = categoriaAssociado;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipoInconsistencia() {
		return tipoInconsistencia;
	}
	public void setTipoInconsistencia(String tipoInconsistencia) {
		this.tipoInconsistencia = tipoInconsistencia;
	}
	public Float getValorConsignado() {
		return valorConsignado;
	}
	public void setValorConsignado(Float valorConsignado) {
		this.valorConsignado = valorConsignado;
	}
	public Float getValorPrevisto() {
		return valorPrevisto;
	}
	public void setValorPrevisto(Float valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}
	
	
	
}
