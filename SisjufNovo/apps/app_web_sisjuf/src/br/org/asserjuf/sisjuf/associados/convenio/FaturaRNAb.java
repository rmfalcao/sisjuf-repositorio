package br.org.asserjuf.sisjuf.associados.convenio;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;

public abstract class FaturaRNAb extends FaturaRN {



	protected abstract FaturaVO gerarFaturaPrevia(FaturaVO fatura) throws SmartEnvException, SmartAppException;
	
}
