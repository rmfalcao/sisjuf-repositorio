package br.org.asserjuf.sisjuf.associados.convenio;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;

public class FaturaVitalmedRN extends FaturaFixaRNAb {

	@Override
	protected FaturaVO validar(FaturaVO fatura) throws SmartEnvException,
			SmartAppException {
		// TODO comparar arquivo importado com dados da fatura
		return null;
	}


	@Override
	protected void setaDatasApuracao(FaturaPreviaFiltroAssembler filtro)
			throws SmartAppException, SmartEnvException {
		// TODO 

		

		/*
		 * 
		 * O objetivo deste m�todo � setar duas datas do objeto "filtro" que s�o espec�ficas para a Vitalmed:
		 * 
		 * dataInicioApuracaoAssociados e dataFimApuracaoAssociados
		 * 
		 */
		
		// para efeito de testes, ser� considerada a data de inicio da fatura e a data de fechamento da fatura.
		
		setDatasFatura(filtro.getFatura());
		
		filtro.setDataInicioApuracaoAssociados(filtro.getFatura().getDataInicial());
		filtro.setDataFimApuracaoAssociados(filtro.getFatura().getDataFinal());
		
		
	}

}
