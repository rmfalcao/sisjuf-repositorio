package br.org.asserjuf.sisjuf.associados;

import br.com.falc.smartFW.SmartVO;

public class RelatorioAssociadosDependentesVO extends SmartVO {

	private String 	faixaEtaria;
	
	private Integer	quantidadeAssociados;
	
	private Integer	quantidadeDependentes;

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public Integer getQuantidadeAssociados() {
		return quantidadeAssociados;
	}

	public void setQuantidadeAssociados(Integer quantidadeAssociados) {
		this.quantidadeAssociados = quantidadeAssociados;
	}

	public Integer getQuantidadeDependentes() {
		return quantidadeDependentes;
	}

	public void setQuantidadeDependentes(Integer quantidadeDependentes) {
		this.quantidadeDependentes = quantidadeDependentes;
	}
	
	
	
}
