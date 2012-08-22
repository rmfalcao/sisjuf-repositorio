package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

public class FaturaArquivoVO extends FaturaVO {


	private Collection<ItemFaturaInconsistenteVO> 	itensInconsistentes;

	public Collection<ItemFaturaInconsistenteVO> getItensInconsistentes() {
		return itensInconsistentes;
	}

	public void setItensInconsistentes(
			Collection<ItemFaturaInconsistenteVO> itensInconsistentes) {
		this.itensInconsistentes = itensInconsistentes;
	}


	
	
	
}
