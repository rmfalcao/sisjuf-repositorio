package br.org.asserjuf.sisjuf.associados.convenio;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;

public abstract class FaturaFixaRNAb extends FaturaFixaRN {

	protected abstract FaturaVO validar(FaturaVO fatura) throws SmartEnvException, SmartAppException;
	
}
