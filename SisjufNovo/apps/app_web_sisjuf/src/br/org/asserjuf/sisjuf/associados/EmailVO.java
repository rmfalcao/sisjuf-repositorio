package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.SmartVO;

public class EmailVO extends SmartVO {

	private String 					assunto;
	private String 					corpo;
	
	private Collection<AssociadoVO>	associados;

	public Collection<AssociadoVO> getAssociados() {
		return associados;
	}

	public void setAssociados(Collection<AssociadoVO> associados) {
		this.associados = associados;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
		
	
}
