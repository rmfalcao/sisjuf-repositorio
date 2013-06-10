package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

public class VinculacaoPlanoFiltroAssembler extends VinculacaoPlanoVO {

	private Date dataVinculacaoInicial;
	private Date dataVinculacaoFinal;
	
	public void setDataVinculacaoInicial(Date dataVinculacaoInicial) {
		this.dataVinculacaoInicial = dataVinculacaoInicial;
	}
	public Date getDataVinculacaoInicial() {
		return dataVinculacaoInicial;
	}
	public void setDataVinculacaoFinal(Date dataVinculacaoFinal) {
		this.dataVinculacaoFinal = dataVinculacaoFinal;
	}
	public Date getDataVinculacaoFinal() {
		return dataVinculacaoFinal;
	}
}