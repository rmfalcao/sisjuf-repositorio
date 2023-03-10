package br.org.asserjuf.sisjuf.associados.cliente;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.AssociadoAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.AssociadoImportacaoNucreVO;
import br.org.asserjuf.sisjuf.associados.AssociadoVO;
import br.org.asserjuf.sisjuf.associados.DocumentoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.EmailVO;
import br.org.asserjuf.sisjuf.associados.HistoricoAssociadoVO;
import br.org.asserjuf.sisjuf.associados.HistoricoFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.ParentescoVO;
import br.org.asserjuf.sisjuf.associados.PlanilhaNucreVO;
import br.org.asserjuf.sisjuf.associados.TipoEventoVO;
import br.org.asserjuf.sisjuf.associados.convenio.OutroBeneficiavelVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculadoPlanoAssembler;
import br.org.asserjuf.sisjuf.associados.facade.AssociadoFacade;

public class AssociadoDelegate  {
	
	protected static transient Logger LOG = Logger.getLogger(AssociadoDelegate.class);

	AssociadoFacade associadoBean;
	
	public void setAssociadoBean(AssociadoFacade associadoBean) {
		this.associadoBean = associadoBean;
	}

	public Collection<AssociadoVO> findAssociadoByFilter(AssociadoFiltroAssembler assembler) throws SmartEnvException, SmartAppException {
		return	associadoBean.findAssociadoByFilter(assembler);
	}

	public AssociadoVO findAssociadoByPrimaryKey(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoBean.findAssociadoByPrimaryKey(associado);
	}
	
	public AssociadoVO insertAssociado(AssociadoAssembler associado)  throws SmartEnvException, SmartAppException {
		return associadoBean.insertAssociado(associado);
	}
	
	public void updateAssociado(AssociadoAssembler associado)  throws SmartEnvException, SmartAppException {
		associadoBean.updateAssociado(associado);
	}
	
	public void removeAssociado(AssociadoVO associado)  throws SmartEnvException, SmartAppException {
			associadoBean.removeAssociado(associado);
	}
	
	public void sendMail(EmailVO email)  throws SmartEnvException, SmartAppException {
		associadoBean.sendEmail(email);
	}
	
	public Collection<AssociadoVO> findAllAssociados()  throws SmartEnvException, SmartAppException {
		return associadoBean.findAllAssociados();
	}
	
	public Collection<HistoricoAssociadoVO> findHistoricoByFilter(HistoricoFiltroAssembler assembler)  throws SmartEnvException, SmartAppException {
		return associadoBean.findHistoricoByFilter(assembler);
	}
	
	public Float findValorAtualSocioUsuario()  throws SmartEnvException, SmartAppException {
		return associadoBean.findValorAtualMensalidade();
	}
	
	public void insertValorSocioUsuario(Float valor)  throws SmartEnvException, SmartAppException {
		associadoBean.insertValorSocioUsuario(valor);
	}
	
	public Collection<AssociadoImportacaoNucreVO> importPlanilhaNucre(Collection<PlanilhaNucreVO> planilha)  throws SmartEnvException, SmartAppException {
		return associadoBean.importPlanilhaNucre(planilha);
	}
	
	public Collection<AssociadoVO> findProximosAniversariantes()  throws SmartEnvException, SmartAppException {
		return associadoBean.findProximosAniversariantes();
	}
	
	public Collection<ParentescoVO> findAll()  throws SmartEnvException, SmartAppException {
		return associadoBean.findAllParentesco();
	}
	
	public AssociadoVO findAssociadoByName(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoBean.findAssociadoByName(associado);
	}
	
	public AssociadoVO findAssociadoByCPF(AssociadoVO associado) throws SmartEnvException, SmartAppException {
		return associadoBean.findAssociadoByCPF(associado);
	}
	
	public Collection<TipoEventoVO> findAllTipoEvento()  throws SmartEnvException, SmartAppException {
		return associadoBean.findAllTipoEvento();
	}

	public Collection<ParentescoVO> findAllParentesco()  throws SmartEnvException, SmartAppException {
		return associadoBean.findAllParentesco();
	}
	
	public void insertHistoricoAssociado(HistoricoAssociadoVO historico) throws SmartEnvException, SmartAppException {
		associadoBean.insertHistoricoAssociado(historico);
	}

	public Collection<OutroBeneficiavelVO> findOutrosBeneficiaveisByAssociado(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		return associadoBean.findOutrosBeneficiaveisByAssociado(vo);
	}
	
	public OutroBeneficiavelVO findOutrosBeneficiavelByPrimaryKey(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {		
		return associadoBean.findOutrosBeneficiavelByPrimaryKey(vo);
	}
	
	public void updateOutroBeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {
		associadoBean.updateOutroBeneficiavel(vo);
	}	
	
	public void removeOutroBeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {		
		associadoBean.removeOutroBeneficiavel(vo);
	}
	
	public OutroBeneficiavelVO insertOutrobeneficiavel(OutroBeneficiavelVO vo) throws SmartEnvException, SmartAppException {							
		return associadoBean.insertOutrobeneficiavel(vo);
	}	


	public VinculacaoPlanoVO findVinculacaoByPrimaryKey(VinculacaoPlanoVO vinculacao) throws SmartEnvException, SmartAppException {
		return associadoBean.findVinculadoPlanoByPrimaryKey(vinculacao);
	}

	public void insertVinculacao(VinculacaoPlanoVO vinculacao) throws SmartEnvException, SmartAppException  {
		associadoBean.insertVinculacaoPlano(vinculacao);
	}

	public void updateVinculacao(VinculacaoPlanoVO vinculacao) throws SmartEnvException, SmartAppException  {
		associadoBean.updateVinculacaoPlano(vinculacao);		
	}
	
	public Collection<VinculadoPlanoAssembler> findHistoricoVinculadosPlanoByFilter(VinculadoPlanoAssembler vo) throws SmartEnvException, SmartAppException {
		return associadoBean.findHistoricoVinculadosPlanoByFilter(vo);
	}
	
	public DocumentoAssociadoVO insertDocumentoAssociado(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return associadoBean.insertDocumentoAssociado(vo);
	}
	
	public DocumentoAssociadoVO findByPrimaryKey(DocumentoAssociadoVO vo) throws SmartEnvException, SmartAppException {
		return associadoBean.findDocumentoAssociadoByPrimaryKey(vo);
	}
	
	public Collection<DocumentoAssociadoVO> findDocumentosByAssociado(AssociadoVO vo) throws SmartEnvException, SmartAppException {
		return associadoBean.findDocumentosByAssociado(vo);
	}
	
}