package br.org.asserjuf.sisjuf.associados.convenio.cliente;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaArquivoVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.ReportVitalmedVO;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.facade.ConvenioFacade;
import br.org.asserjuf.sisjuf.entidadesComuns.EstadoVO;
import br.org.asserjuf.sisjuf.entidadesComuns.facade.EntidadesComunsFacade;

/**
 * Implementação da classe que representa o "proxy" para o façade Convenio.
 * @author Paulo
 *
 */
public class ConvenioDelegate {

	protected static transient Logger LOG = Logger.getLogger(ConvenioDelegate.class);
	
	/**
	 * Façace Convênio do Sisjuf.
	 */
	private ConvenioFacade	convenioBean;
	
	private EntidadesComunsFacade entidadesComunsBean;
		

	/**
	 * "Proxy" para o método findAllConvenio() no façade Convenio
	 * @return Collection de ConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public Collection<ConvenioVO> findAllConvenio() throws SmartEnvException, SmartAppException {
		return convenioBean.findAllConvenio();
	}
	
	/**
	 * "Proxy" para o método findConvenioByPrymaryKey() no façade Convenio.
	 * @param vo 
	 * @return ConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public ConvenioVO findConvenioByPrimaryKey(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findConvenioByPrimaryKey(vo); 
	}
	
	/**
	 * "Proxy" para o método updateConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void updateConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.updateConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método removeConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void removeConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.removeConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método insertConvenio() no façade Convenio.
	 * @param vo
	 * @return ConvenioVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public ConvenioVO insertConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.insertConvenio(vo); 
	}	
	
	/**
	 * "Proxy" para o método findConvenioByFilter() no façade Convenio.
	 * @param vo Convênio com critérios de filtro preenchidos. 
	 * @return Collection de ConvenioVO
	 * @throws SmartAppException 
	 * @throws SmartEnvException 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public Collection<ConvenioVO> findConvenioByFilter(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findConvenioByFilter(vo);
	}

	/**
	 * "Proxy" para o método findAllAtividades() no façade Convenio
	 * @return Collection de AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public Collection<AtividadeConvenioVO> findAllAtividades() throws SmartEnvException, SmartAppException {
		return convenioBean.findAllAtividadeConvenio();
	}
	
	/**
	 * "Proxy" para o método findAllAtividades() no façade Convenio
	 * @return Collection de AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public Collection<AtividadeConvenioVO> findAllAtividadesAtivas() throws SmartEnvException, SmartAppException {
		return convenioBean.findAllAtividadesAtivasConvenio();
	}	
	
	/**
	 * "Proxy" para o método findAtividadeByPrimaryKey() no façade Convenio.
	 * @param vo 
	 * @return AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public AtividadeConvenioVO findAtividadeByPrimaryKey(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findAtividadeConvenioByPrimaryKey(vo); 
	}
	
	/**
	 * "Proxy" para o método updateAtividade() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void updateAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.updateAtividadeConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método removeAtividade() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void removeAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.removeAtividadeConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método insertAtividade() no façade Convenio.
	 * @param vo
	 * @return AtividadeConvenioVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public AtividadeConvenioVO insertAtividade(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.insertAtividadeConvenio(vo); 
	}	
	
	/**
	 * "Proxy" para o método findAtividadeConvenioByFilter() no façade Convenio.
	 * @return Collection de AtividadeConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */		
	public Collection<AtividadeConvenioVO> findAtividadeConvenioByFilter(AtividadeConvenioVO vo)throws SmartEnvException, SmartAppException {
		Collection<AtividadeConvenioVO> convenios = new ArrayList<AtividadeConvenioVO>();
		return convenios;
	}	
	
	/**
	 * "Proxy" para o método findAllPlanoConvenio() no façade Convenio
	 * @return Collection de PlanoConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public Collection<PlanoConvenioVO> findAllPlanoConvenio() throws SmartEnvException, SmartAppException {
		return convenioBean.findAllPlanoConvenio();
	}
	
	/**
	 * "Proxy" para o método findPlanoConvenioByPrimaryKey() no façade Convenio.
	 * @param vo 
	 * @return PlanoConvenioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public PlanoConvenioVO findPlanoConvenioByPrimaryKey(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findPlanoConvenioByPrimaryKey(vo); 
	}
	
	/**
	 * "Proxy" para o método updatePlanoConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void updatePlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.updatePlanoConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método removeConvenio() no façade Convenio.
	 * @param vo
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public void removePlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioBean.removePlanoConvenio(vo);
	}
	
	/**
	 * "Proxy" para o método insertPlanoConvenio() no façade Convenio.
	 * @param vo
	 * @return ConvenioVO 
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */	
	public PlanoConvenioVO insertPlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.insertPlanoConvenio(vo); 
	}	
	
	public Collection<PlanoConvenioVO> findPlanoConvenioByFilter(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findPlanoConvenioByFilter(vo);
	}	
	
	public Collection<EstadoVO> findAllEstados()throws SmartEnvException, SmartAppException  {
			return entidadesComunsBean.findAllEstado();			
	}

	public void setConvenioBean(ConvenioFacade convenioBean) {
		this.convenioBean = convenioBean;
	}
	
	public void setEntidadesComunsBean(EntidadesComunsFacade entidadesComunsBean){
		this.entidadesComunsBean = entidadesComunsBean;
	}

	public Collection<FaturaVO> findUltimasFaturas(FaturaVO fatura) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<VinculacaoPlanoVO> findVinculadosPlanoByAssociado(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return convenioBean.findVinculadosPlanoByAssociado(vo);
	}	
	
	
	public Collection<BeneficiarioVO> findBeneficiariosByFilter(BeneficiarioVO beneficiario) throws SmartEnvException, SmartAppException {
		return convenioBean.findBeneficiariosByFilter(beneficiario);
	}
	
	
	public Collection<FaturaVO> findFaturaByConvenio(ConvenioVO convenio) throws SmartEnvException {
		return convenioBean.findFaturaByConvenio(convenio);
	}

	public FaturaVO gerarFaturaVariavel(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return convenioBean.gerarFaturaVariavel(fatura);
	}
	
	public FaturaVO gerarFaturaFixa(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return convenioBean.gerarFaturaFixa(fatura);
	}
	
	public FaturaVO gerarFaturaPrevia(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return convenioBean.gerarFaturaPrevia(fatura); 
	}
	
	public Collection<FaturaVO> findFaturaByFilter(FaturaFiltroAssembler fatura) throws SmartEnvException, SmartAppException {
		return convenioBean.findFaturaByFilter(fatura);
	}
	
	public FaturaVO findByPrimaryKey(FaturaVO fatura) throws SmartEnvException {
		return convenioBean.findByPrimaryKey(fatura);
	}

	public Collection<StatusFaturaVO> findAllStatusFatura() throws SmartEnvException {
		return convenioBean.findAllStatusFatura();
	}
	
	public FaturaArquivoVO validarFatura(FaturaArquivoVO faturaArquivo) throws SmartEnvException, SmartAppException {
		return convenioBean.validarFatura(faturaArquivo);
	}
	
	public Collection<ReportVitalmedVO> findReportVitalmed(ReportVitalmedVO report) throws SmartEnvException, SmartAppException {
		return convenioBean.findReportVitalmed(report);
	}
	
}