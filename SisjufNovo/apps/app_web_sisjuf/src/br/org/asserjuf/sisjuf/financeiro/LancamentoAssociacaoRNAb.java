package br.org.asserjuf.sisjuf.financeiro;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;

public abstract class LancamentoAssociacaoRNAb extends LancamentoRN {

	protected abstract void gravarAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException;
	
	protected abstract void limparAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException;
	
}
