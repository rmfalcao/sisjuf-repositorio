package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Date;

import br.com.falc.smartFW.SmartVO;

public class FaturaPreviaFiltroAssembler extends SmartVO {

	private FaturaVO 	fatura;
	private Date		dataInicioApuracaoAssociados;
	private Date		dataFimApuracaoAssociados;


	
	
	public FaturaVO getFatura() {
		return fatura;
	}
	public void setFatura(FaturaVO fatura) {
		this.fatura = fatura;
	}
	public Date getDataInicioApuracaoAssociados() {
		return dataInicioApuracaoAssociados;
	}
	public void setDataInicioApuracaoAssociados(Date dataInicioApuracaoAssociados) {
		this.dataInicioApuracaoAssociados = dataInicioApuracaoAssociados;
	}
	public Date getDataFimApuracaoAssociados() {
		return dataFimApuracaoAssociados;
	}
	public void setDataFimApuracaoAssociados(Date dataFimApuracaoAssociados) {
		this.dataFimApuracaoAssociados = dataFimApuracaoAssociados;
	}
	
	
	
}
