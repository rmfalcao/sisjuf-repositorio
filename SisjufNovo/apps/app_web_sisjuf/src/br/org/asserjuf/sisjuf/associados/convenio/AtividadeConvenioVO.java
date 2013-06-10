package br.org.asserjuf.sisjuf.associados.convenio;

import br.com.falc.smartFW.SmartVO;

public class AtividadeConvenioVO extends SmartVO {

	private Integer codigo;
	private String	nome;
	private String	desativo;
	private Boolean flagDesativado;

	public void setFlagDesativado(Boolean flagDesativado) {
		this.flagDesativado = flagDesativado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesativo() {
		return desativo; 
	}

	public void setDesativo(String desativo) {
		this.desativo = desativo;
	}

	public boolean getFlagDesativado() {
		if (this.getDesativo() == null || this.getDesativo().equals("") || this.getDesativo().equals("N")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setFlagDesativado(boolean pFlagDesativo){
		if (Boolean.FALSE == pFlagDesativo){
			this.setDesativo("N");
		}else if (Boolean.TRUE == pFlagDesativo){
			this.setDesativo("S");
		}
		flagDesativado = pFlagDesativo;
	}
			
}