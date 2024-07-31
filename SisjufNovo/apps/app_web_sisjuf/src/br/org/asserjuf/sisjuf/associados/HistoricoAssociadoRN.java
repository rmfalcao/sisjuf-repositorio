package br.org.asserjuf.sisjuf.associados;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoRN;
import br.org.asserjuf.sisjuf.associados.dados.HistoricoAssociadoDAO;
import br.org.asserjuf.sisjuf.util.DataRN;

public class HistoricoAssociadoRN {

	private HistoricoAssociadoDAO 	historicoAssociadoDAO;
	private DataRN					dataRN;
	private VinculacaoPlanoRN		vinculacaoPlanoRN;
	
	


	public HistoricoAssociadoVO insert(HistoricoAssociadoVO historicoAssociado) throws SmartEnvException, SmartAppException {
		
		if (historicoAssociado.getData() == null) {
			historicoAssociado.setData(dataRN.getCurrentDate());

		}
		
		if (historicoAssociado.getTipoEvento().getCodigo() == 2) { // evento de exclusao
			vinculacaoPlanoRN.removeAllByAssociado(historicoAssociado.getAssociado());
		}
		
		return historicoAssociadoDAO.insert(historicoAssociado);
		
	}
	
	public Collection<HistoricoAssociadoVO> findByFilter(HistoricoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		
		return historicoAssociadoDAO.findByFilter(assembler);
		
		
	}

	public void setDataRN(DataRN dataRN) {
		this.dataRN = dataRN;
	}
	
	public void setVinculacaoPlanoRN(VinculacaoPlanoRN vinculacaoPlanoRN) {
		this.vinculacaoPlanoRN = vinculacaoPlanoRN;
	}

	public void setHistoricoAssociadoDAO(HistoricoAssociadoDAO historicoAssociadoDAO) {
		this.historicoAssociadoDAO = historicoAssociadoDAO;
	}

	public HistoricoAssociadoVO findUltimoNaoCancelado(
			AssociadoAssembler associado) throws SmartEnvException {
		return historicoAssociadoDAO.findUltimoNaoCancelado(associado);
	}

	public void updateDate(HistoricoAssociadoVO historico) throws SmartEnvException {
		historicoAssociadoDAO.updateDate(historico);
		
	}
	
}
