package br.org.asserjuf.sisjuf.entidadesComuns;

import br.com.falc.smartFW.SmartVO;

public class MunicipioVO extends SmartVO {
	
	private Integer 	codigo;
	private String 		nome;
	private EstadoVO	estado;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public EstadoVO getEstado() {
		return estado;
	}
	public void setEstado(EstadoVO estado) {
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
