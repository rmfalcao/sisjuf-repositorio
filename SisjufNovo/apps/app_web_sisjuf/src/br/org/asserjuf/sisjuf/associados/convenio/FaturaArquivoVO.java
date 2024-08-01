package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

public class FaturaArquivoVO extends FaturaVO {

	private Integer									codigoRelatorioInconsistencias;
	private Collection<ItemFaturaInconsistenteVO> 	itensInconsistentes;

	public Collection<ItemFaturaInconsistenteVO> getItensInconsistentes() {
		return itensInconsistentes;
	}

	public void setItensInconsistentes(
			Collection<ItemFaturaInconsistenteVO> itensInconsistentes) {
		this.itensInconsistentes = itensInconsistentes;
	}

	public Integer getCodigoRelatorioInconsistencias() {
		return codigoRelatorioInconsistencias;
	}

	public void setCodigoRelatorioInconsistencias(Integer codigoRelatorioInconsistencias) {
		this.codigoRelatorioInconsistencias = codigoRelatorioInconsistencias;
	}
	
	


	
	
	
}
