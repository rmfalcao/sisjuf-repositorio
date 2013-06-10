package br.org.asserjuf.sisjuf.associados.convenio;



import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.FaturaDAO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaRN;
import br.org.asserjuf.sisjuf.util.ParametroRN;
import br.org.asserjuf.sisjuf.util.ParametroVO;

public class FaturaVariavelRN extends FaturaRNAb {

	@Override
	protected FaturaVO gerarFaturaPrevia(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		fatura	=  super.gerar(fatura);
		

		fatura.setStatus(new StatusFaturaVO());
		fatura.getStatus().setCodigo(new Short(parametroRN.findByPrimaryKey(new ParametroVO("STATUS_FATURA_VALIDADA")).getValorTextual()));
		
		this.updateStatus(fatura);
		
		return fatura;
		
		
	}
	
	public void setParametroRN(ParametroRN parametroRN) {
		this.parametroRN = parametroRN;
	}



	public void setFaturaDAO(FaturaDAO faturaDAO) {
		this.faturaDAO = faturaDAO;
	}




	public void setLancamentoFaturaRN(LancamentoFaturaRN lancamentoFaturaRN) {
		this.lancamentoFaturaRN = lancamentoFaturaRN;
	}
	
	public void setConvenioRN(ConvenioRN convenioRN) {
		this.convenioRN = convenioRN;
	}


}
