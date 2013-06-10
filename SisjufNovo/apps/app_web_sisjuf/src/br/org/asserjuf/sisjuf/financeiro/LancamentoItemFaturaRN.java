package br.org.asserjuf.sisjuf.financeiro;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;

public class LancamentoItemFaturaRN extends LancamentoAssociacaoRNAb {

	public LancamentoVO efetuarLancamento(LancamentoItemFaturaVO lancamentoItemFatura) throws SmartEnvException, SmartAppException {
		
		super.efetuarLancamento(lancamentoItemFatura);
		
		gravarAssociacao(lancamentoItemFatura);
			
		return lancamentoItemFatura;
		
	}
	
	public void estornarLancamento(LancamentoItemFaturaVO lancamentoItemFatura) throws SmartEnvException, SmartAppException {
		
		limparAssociacao(lancamentoItemFatura);
		
		super.estornarLancamento(lancamentoItemFatura);
		
	}

	@Override
	protected void gravarAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException {
		
		LancamentoItemFaturaVO lancamentoItemFatura	= (LancamentoItemFaturaVO) lancamento;
		
		this.lancamentoDAO.insert(lancamentoItemFatura);
		
		
	}

	@Override
	protected void limparAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException {

		LancamentoItemFaturaVO lancamentoItemFatura	= (LancamentoItemFaturaVO) lancamento;
		
		this.lancamentoDAO.remove(lancamentoItemFatura);
		
	}
	
}
