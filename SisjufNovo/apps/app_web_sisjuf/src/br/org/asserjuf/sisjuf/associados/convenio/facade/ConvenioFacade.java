package br.org.asserjuf.sisjuf.associados.convenio.facade;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioRN;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioRN;
import br.org.asserjuf.sisjuf.associados.convenio.BeneficiarioVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaArquivoVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFiltroAssembler;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaFixaRN;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaRN;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVariavelRN;
import br.org.asserjuf.sisjuf.associados.convenio.ReportVitalmedVO;
import br.org.asserjuf.sisjuf.associados.convenio.StatusFaturaRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioRN;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioRN;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;

/**
 * Classe responsavél por chamar os métodos dos RNs diversos que envolvem a funcionalidade "Convenios"
 * @author Paulo
 *
 */
public class ConvenioFacade {

	/**
	 * Encapsula as regras de negócio de convenio.
	 */
	public ConvenioRN	convenioRN;

	/**
	 * Encapsula as regras de negócio de beneficiario.
	 */
	public BeneficiarioRN	beneficiarioRN;

	
	/**
	 * Encapsula as regras de negócio de atividades dos convênios.
	 */
	public AtividadeConvenioRN	atividadeConvenioRN;	
	
	/**
	 * Encapsula as regras de negócio de Beneficiarios.
	 */
	public VinculacaoPlanoRN	vinculacaoPlanoRN;
	
	/**
	 * Encapsula as regras de negócio de Plano.
	 */
	public PlanoConvenioRN	planoConvenioRN;	


	/**
	 * Encapsula as regras de negócio de Fatura.
	 */
	public FaturaRN 		faturaRN;
	
	/**
	 * Encapsula as regras de negócio de Status Fatura.
	 */
	public StatusFaturaRN 		statusFaturaRN;
	
	/**
	 * Encapsula as regras de negócio de Fatura Variável.
	 */
	public FaturaVariavelRN 		faturaVariavelRN;
	

	/**
	 * Encapsula as regras de negócio de Fatura Fixa.
	 */
	public FaturaFixaRN 		faturaFixaRN;
	
	/**
	 * Obtém todos os convênios.
	 * @return Coleção de todos os convênios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findAllConvenio() throws SmartEnvException, SmartAppException {
		return convenioRN.findAll();
	}
	
	
	/**
	 * Obtém um convênio por chave.
	 * @param vo Instância de ConvenioVO que encapsula o código do convênio que se deseja recuperar.
	 * @return Convênio desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO findConvenioByPrimaryKey(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.findByPrimaryKey(vo);
	}
	
	
	/**
	 * Atualiza um convênio.
	 * @param vo Convênio que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioRN.update(vo);
	}
	
	
	/**
	 * Exclui um convênio.
	 * @param vo Convênio que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioRN.remove(vo);
	}

	
	/**
	 * Insere um convênio.
	 * @param vo Convênio que se deseja inserir.
	 * @return Convênio que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO insertConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.insert(vo);
	}	
	
	
	/**
	 * Obtém os convênios atraves de critérios de filtro.
	 * @param vo Convênio com critérios de filtro preenchidos.
	 * @return Coleção de convênios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findConvenioByFilter(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.findByFilter(vo);
	}	
	
	/**
	 * Obtém todos as atividades.
	 * @return Coleção de todos as atividades, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAllAtividadeConvenio() throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.findAll();
	}
	
	/**
	 * Obtém todos as atividades.
	 * @return Coleção de todos as atividades, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAllAtividadesAtivasConvenio() throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.findAllAtivas();
	}	
	
	
	/**
	 * Obtém as atividades por chave.
	 * @param vo Instância de AtividadeConvenioVO que encapsula o código do convênio que se deseja recuperar.
	 * @return Atividade desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public AtividadeConvenioVO findAtividadeConvenioByPrimaryKey(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.findByPrimaryKey(vo);
	}
	
	
	/**
	 * Atualiza uma atividade.
	 * @param vo Atividade que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateAtividadeConvenio(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		atividadeConvenioRN.update(vo);
	}
	
	
	/**
	 * Exclui uma atividade.
	 * @param vo Atividade que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeAtividadeConvenio(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		atividadeConvenioRN.remove(vo);
	}

	
	/**
	 * Obtém os Beneficiarios atraves de critérios de filtro.
	 * @param vo BeneficiarioVO com critérios de filtro preenchidos.
	 * @return Coleção de beneficiarios, encapsulados na classe BeneficiarioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public  Collection<VinculacaoPlanoVO> findVinculadosPlanoByAssociado(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoRN.findVinculadosPlanoByAssociado(vo);
	}	
	
	/**
	 * Insere uma atividade.
	 * @param vo Atividade que se deseja inserir.
	 * @return Convênio que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public AtividadeConvenioVO insertAtividadeConvenio(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.insert(vo);
	}		
	
	
	/**
	 * Obtém todos os planos.
	 * @return Coleção de todos os planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findAllPlanoConvenio() throws SmartEnvException, SmartAppException {
		return planoConvenioRN.findAll();
	}
	
	
	/**
	 * Obtém um plano por chave.
	 * @param vo Instância de PlanoConvenioVO que encapsula o código do plano que se deseja recuperar.
	 * @return Plano desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO findPlanoConvenioByPrimaryKey(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioRN.findByPrimaryKey(vo);
	}
	
	
	/**
	 * Atualiza um plano.
	 * @param vo Plano que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updatePlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		planoConvenioRN.update(vo);
	}
	
	
	/**
	 * Exclui um plano.
	 * @param vo Plano que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removePlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		planoConvenioRN.remove(vo);
	}

	
	/**
	 * Insere um plano.
	 * @param vo Plano que se deseja inserir.
	 * @return Plano que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO insertPlanoConvenio(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioRN.insert(vo);
	}		
	
	/** 
	 * Obtém os planos atraves de critérios de filtro.
	 * @param vo Plano com critérios de filtro preenchidos.
	 * @return Coleção de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findPlanoConvenioByFilter(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioRN.findByFilter(vo);
	}		
	
	public void setConvenioRN(ConvenioRN convenioRN) {
		this.convenioRN = convenioRN;
	}

	public void setAtividadeConvenioRN(AtividadeConvenioRN atividadeConvenioRN) {
		this.atividadeConvenioRN = atividadeConvenioRN;
	}


	public void setVinculacaoPlanoRN(VinculacaoPlanoRN vinculacaoPlanoRN) {
		this.vinculacaoPlanoRN = vinculacaoPlanoRN;
	}

	public void setPlanoConvenioRN(PlanoConvenioRN planoConvenioRn) {
		planoConvenioRN = planoConvenioRn;
	}
	
	
	/**
	 * Obtém os beneficiários por filtro.
	 * @return Coleção de beneficiarios, encapsulados na classe BeneficiarioVO.
	 * @param BeneficiarioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<BeneficiarioVO> findBeneficiariosByFilter(BeneficiarioVO beneficiario) throws SmartEnvException, SmartAppException {
		return beneficiarioRN.findByFilter(beneficiario);
	}
	
	/**
	 * Obtém as faturas recentes de um convênio.
	 * @return Coleção de faturas, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 */
	public Collection<FaturaVO> findFaturaByConvenio(ConvenioVO convenio) throws SmartEnvException {
		return faturaRN.findByConvenio(convenio);
	}
	
	/**
	 * Gerar fatura de um convênio de fatura variável.
	 * @return Fatura gerada, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException 
	 */
	public FaturaVO gerarFaturaVariavel(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return faturaVariavelRN.gerar(fatura);
	}
	
	/**
	 * Gerar fatura de um convênio de fatura fixa.
	 * @return Fatura gerada, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException 
	 */
	public FaturaVO gerarFaturaFixa(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return faturaFixaRN.gerar(fatura);
	}
	
	/**
	 * Gerar prévia de fatura de um convênio de fatura fixa.
	 * @return Fatura prévia, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException 
	 */
	public FaturaVO gerarFaturaPrevia(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return faturaFixaRN.gerarFaturaPrevia(fatura);
	}
	
	public Collection<FaturaVO> findFaturaByFilter(FaturaFiltroAssembler fatura) throws SmartEnvException, SmartAppException {
		return faturaRN.findByFilter(fatura);
	}

	public FaturaVO findByPrimaryKey(FaturaVO fatura) throws SmartEnvException {
		return faturaRN.findByPrimaryKey(fatura);
	}
	
	public Collection<StatusFaturaVO> findAllStatusFatura() throws SmartEnvException {
		return statusFaturaRN.findAll();
	}

	public void setBeneficiarioRN(BeneficiarioRN beneficiarioRN) {
		this.beneficiarioRN = beneficiarioRN;
	}


	public void setFaturaRN(FaturaRN faturaRN) {
		this.faturaRN = faturaRN;
	}
	
	public void setFaturaFixaRN(FaturaFixaRN faturaFixaRN) {
		this.faturaFixaRN = faturaFixaRN;
	}

	public void setStatusFaturaRN(StatusFaturaRN statusFaturaRN) {
		this.statusFaturaRN = statusFaturaRN;
	}
	
	public void setFaturaVariavelRN(FaturaVariavelRN faturaVariavelRN) {
		this.faturaVariavelRN = faturaVariavelRN;
	}
	
	public FaturaArquivoVO validarFatura(FaturaArquivoVO faturaArquivo) throws SmartEnvException, SmartAppException {
		return this.faturaFixaRN.validar(faturaArquivo);
	}
	
	public Collection<ReportVitalmedVO> findReportVitalmed(ReportVitalmedVO report) throws SmartEnvException, SmartAppException {
		return this.convenioRN.findReportVitalmed(report);
	}
}