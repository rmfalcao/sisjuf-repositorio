package br.org.asserjuf.sisjuf.financeiro;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.financeiro.dados.LancamentoDAO;
import br.org.asserjuf.sisjuf.util.DataRN;
import br.org.asserjuf.sisjuf.util.ParametroRN;

public class LancamentoFaturaRN extends LancamentoAssociacaoRNAb {

	public LancamentoVO efetuarLancamento(LancamentoFaturaVO lancamentoFatura) throws SmartEnvException, SmartAppException {
		
		super.efetuarLancamento(lancamentoFatura);
		
		gravarAssociacao(lancamentoFatura);
			
		return lancamentoFatura;
		
	}
	
	public void estornarLancamento(LancamentoFaturaVO lancamentoFatura) throws SmartEnvException, SmartAppException {
		
		limparAssociacao(lancamentoFatura);
		
		super.estornarLancamento(lancamentoFatura);
		
	}
	
	public Collection<LancamentoFaturaVO> findByFatura(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		
		return lancamentoDAO.findByFatura(fatura);
		
		
	}

	@Override
	protected void gravarAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException {
		
		LancamentoFaturaVO lancamentoFatura	= (LancamentoFaturaVO) lancamento;
		
		this.lancamentoDAO.insert(lancamentoFatura);
		
		
	}

	@Override
	protected void limparAssociacao(LancamentoVO lancamento) throws SmartEnvException, SmartAppException {

		LancamentoFaturaVO lancamentoFatura	= (LancamentoFaturaVO) lancamento;
		
		this.lancamentoDAO.remove(lancamentoFatura);
		
	}

	public void setContaRN(ContaRN contaRN) {
		this.contaRN = contaRN;
	}

	public void setDataRN(DataRN dataRN) {
		this.dataRN = dataRN;
	}

	public void setLancamentoDAO(LancamentoDAO lancamentoDAO) {
		this.lancamentoDAO = lancamentoDAO;
	}

	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}

	public void setTipoOperacaoRN(TipoOperacaoRN tipoOperacaoRN) {
		this.tipoOperacaoRN = tipoOperacaoRN;
	}

	public void setDiretorFinanceiroRN(DiretorFinanceiroRN diretorFinanceiroRN) {
		this.diretorFinanceiroRN = diretorFinanceiroRN;
	}
	
}
