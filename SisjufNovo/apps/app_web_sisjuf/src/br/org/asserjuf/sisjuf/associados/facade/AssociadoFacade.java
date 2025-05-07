package br.org.asserjuf.sisjuf.associados.facade;

import java.util.Collection;
import java.util.List;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoImportacaoNucreVO;
import br.org.asserjuf.sisjuf.associados.AssociadoRN;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.DocumentoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.DocumentosAssociadoRN;
import br.org.asserjuf.sisjuf.associados.EmailRN;
import br.org.asserjuf.sisjuf.associados.EmailVO;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoRN;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.InconsistenciaNucreVO;
import br.org.asserjuf.sisjuf.associados.ParentescoRN;
import br.org.asserjuf.sisjuf.associados.ParentescoVO;
import br.org.asserjuf.sisjuf.associados.ItemPlanilhaNucreVO;
import br.org.asserjuf.sisjuf.associados.RelatorioAssociadosDependentesVO;
import br.org.asserjuf.sisjuf.associados.TipoEventoRN;
import br.org.asserjuf.sisjuf.associados.TipoEventoVO;
import br.org.asserjuf.sisjuf.associados.convenio.OutroBeneficiavelVO;
import br.org.asserjuf.sisjuf.associados.convenio.OutrosBeneficiaveisRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;

public class AssociadoFacade {

	private AssociadoRN 			associadoRN;
	private	EmailRN					emailRN;
	private	HistoricoAssociadoRN	historicoAssociadoRN;
	private ParentescoRN			parentescoRN;
	private TipoEventoRN			tipoEventoRN;
	private VinculacaoPlanoRN		vinculacaoPlanoRN;	
	private OutrosBeneficiaveisRN	outrosBeneficiaveisRN;
	private DocumentosAssociadoRN	documentosAssociadoRN;
	
	
	public Collection<AssociadoVO> findAssociadoByFilter(AssociadoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return associadoRN.findByFilter(assembler);
	}
	
	public AssociadoVO findAssociadoByPrimaryKey(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoRN.findByPrimaryKey(associado);
	}
	
	public AssociadoVO insertAssociado(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		return associadoRN.insert(associado);
	}
	
	public void updateAssociado(AssociadoAssembler associado) throws SmartEnvException, SmartAppException {
		associadoRN.update(associado);
	}
	
	public void removeAssociado(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		associadoRN.remove(associado);
	}
	
	public void sendEmail(EmailVO email)  throws SmartEnvException, SmartAppException {
		emailRN.send(email);
	}
	
	public Collection<AssociadoVO> findAllAssociados()  throws SmartEnvException, SmartAppException {
		return associadoRN.findAll();
	}
	
	public Collection<HistoricoAssociadoVO> findHistoricoByFilter(HistoricoFiltroAssembler assembler)  throws SmartEnvException, SmartAppException {
		return historicoAssociadoRN.findByFilter(assembler);
	}
	
	public Float findValorAtualMensalidade()  throws SmartEnvException, SmartAppException {
		return associadoRN.findValorAtualSocioUsuario();
	}
	
	public void insertValorSocioUsuario(Float valor)  throws SmartEnvException, SmartAppException {
		associadoRN.insertValorSocioUsuario(valor);
	}
	
	public Collection<AssociadoImportacaoNucreVO> importPlanilhaNucre(Collection<ItemPlanilhaNucreVO> planilha)  throws SmartEnvException, SmartAppException {
		return associadoRN.importPlanilhaNucre(planilha);
	}
	
	public Collection<AssociadoVO> listarAniversariantesPorMes(Short mesAniversariante) throws SmartEnvException, SmartAppException {
		return associadoRN.listarAniversariantesPorMes(mesAniversariante);
	}
	
	public Collection<AssociadoVO> findProximosAniversariantes()  throws SmartEnvException, SmartAppException {
		return associadoRN.findProximosAniversariantes();
	}
	
	public Collection<ParentescoVO> findAllParentesco()  throws SmartEnvException, SmartAppException {
		return parentescoRN.findAll();
	}
	
	public AssociadoVO findAssociadoByName(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoRN.findByName(associado);
	}

	public AssociadoVO findAssociadoByCPF(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoRN.findByCPF(associado);
	}

	public Collection<TipoEventoVO> findAllTipoEvento()  throws SmartEnvException, SmartAppException {
		return tipoEventoRN.findAll();
	}
	
	public void insertHistoricoAssociado(HistoricoAssociadoVO historico) throws SmartEnvException, SmartAppException {
		historicoAssociadoRN.insert(historico);
	}
	
	public VinculacaoPlanoVO insertVinculacaoPlano(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoRN.insert(vo);
	}	
	
	public void updateVinculacaoPlano(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		vinculacaoPlanoRN.update(vo);
	}		
	
	public Collection<VinculadoPlanoAssembler> findHistoricoVinculadosPlanoByFilter(VinculadoPlanoAssembler vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoRN.findHistoricoVinculadosPlanoByFilter(vo);
	}		
	
	public VinculacaoPlanoVO findVinculadoPlanoByPrimaryKey(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoRN.findByPrimaryKey(vo);
	}	
	
		
	public Collection<OutroBeneficiavelVO> findOutrosBeneficiaveisByAssociado(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		return outrosBeneficiaveisRN.findByAssociado(vo);
	}
	
	public OutroBeneficiavelVO findOutrosBeneficiavelByPrimaryKey(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {		
		return outrosBeneficiaveisRN.findByPrimaryKey(vo);
	}
	
	public void updateOutroBeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		outrosBeneficiaveisRN.update(vo);
	}	
	
	public void removeOutroBeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {		
		outrosBeneficiaveisRN.remove(vo);
	}
	
	public OutroBeneficiavelVO insertOutrobeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {							
		return outrosBeneficiaveisRN.insert(vo);
	}
	
	public DocumentoAssociadoVO insertDocumentoAssociado(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoRN.insert(vo);
	}
	
	public DocumentoAssociadoVO findDocumentoAssociadoByPrimaryKey(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoRN.findByPrimaryKey(vo);
	}
	
	public Collection<DocumentoAssociadoVO> findDocumentosByAssociado(AssociadoVO vo) throws SmartEnvException, SmartAppException {
		return documentosAssociadoRN.findDocumentosByAssociado(vo);
	}	
	
	
	public void removeDocumento(DocumentoAssociadoVO documento) throws SmartEnvException {
		documentosAssociadoRN.remover(documento);
		
	}
	
	
	
	
	
	public void setAssociadoRN(AssociadoRN associadoRN) {
		this.associadoRN = associadoRN;
	}

	public void setEmailRN(EmailRN emailRN) {
		this.emailRN = emailRN;
	}

	public void setHistoricoAssociadoRN(HistoricoAssociadoRN historicoAssociadoRN) {
		this.historicoAssociadoRN = historicoAssociadoRN;
	}

	public void setParentescoRN(ParentescoRN parentescoRN) {
		this.parentescoRN = parentescoRN;
	}

	public void setTipoEventoRN(TipoEventoRN tipoEventoRN) {
		this.tipoEventoRN = tipoEventoRN;
	}	

	public void setVinculacaoPlanoRN(VinculacaoPlanoRN vinculacaoPlanoRN) {
		this.vinculacaoPlanoRN = vinculacaoPlanoRN;
	}
	
	public void setDocumentosAssociadoRN(DocumentosAssociadoRN documentosAssociadoRN) {
		this.documentosAssociadoRN = documentosAssociadoRN;
	}

	public void setOutrosBeneficiaveisRN(OutrosBeneficiaveisRN outrosBeneficiaveisRN) {
		this.outrosBeneficiaveisRN = outrosBeneficiaveisRN;
	}

	public Collection<RelatorioAssociadosDependentesVO> findRelatorioAssociadosDependentes() throws SmartEnvException {
		
		return associadoRN.findRelatorioAssociadosDependentes();
	}

	public List<InconsistenciaNucreVO> gerarRelatorioInconsistenciasNUCRE(List<ItemPlanilhaNucreVO> relatorioNucre) throws SmartEnvException, SmartAppException {
		
		return associadoRN.gerarRelatorioInconsistenciasNUCRE(relatorioNucre);
		
	}


	
	
}