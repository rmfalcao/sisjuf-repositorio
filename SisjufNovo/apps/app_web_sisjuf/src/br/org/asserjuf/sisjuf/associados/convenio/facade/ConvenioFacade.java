package br.org.asserjuf.sisjuf.associados.convenio.facade;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.RelatorioIRVO;
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
import br.org.asserjuf.sisjuf.associados.convenio.ItemFaturaInconsistenteVO;
import br.org.asserjuf.sisjuf.associados.convenio.StatusFaturaRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoRN;
import br.org.asserjuf.sisjuf.associados.convenio.VinculacaoPlanoVO;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioRN;
import br.org.asserjuf.sisjuf.associados.convenio.ConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioRN;
import br.org.asserjuf.sisjuf.associados.convenio.PlanoConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.dados.StatusFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;

/**
 * Classe responsav�l por chamar os m�todos dos RNs diversos que envolvem a funcionalidade "Convenios"
 * @author Paulo
 *
 */
public class ConvenioFacade {

	/**
	 * Encapsula as regras de neg�cio de convenio.
	 */
	public ConvenioRN	convenioRN;

	/**
	 * Encapsula as regras de neg�cio de beneficiario.
	 */
	public BeneficiarioRN	beneficiarioRN;

	
	/**
	 * Encapsula as regras de neg�cio de atividades dos conv�nios.
	 */
	public AtividadeConvenioRN	atividadeConvenioRN;	
	
	/**
	 * Encapsula as regras de neg�cio de Beneficiarios.
	 */
	public VinculacaoPlanoRN	vinculacaoPlanoRN;
	
	/**
	 * Encapsula as regras de neg�cio de Plano.
	 */
	public PlanoConvenioRN	planoConvenioRN;	


	/**
	 * Encapsula as regras de neg�cio de Fatura.
	 */
	public FaturaRN 		faturaRN;
	
	/**
	 * Encapsula as regras de neg�cio de Status Fatura.
	 */
	public StatusFaturaRN 		statusFaturaRN;
	
	/**
	 * Encapsula as regras de neg�cio de Fatura Vari�vel.
	 */
	public FaturaVariavelRN 		faturaVariavelRN;
	

	/**
	 * Encapsula as regras de neg�cio de Fatura Fixa.
	 */
	public FaturaFixaRN 		faturaFixaRN;
	
	/**
	 * Obt�m todos os conv�nios.
	 * @return Cole��o de todos os conv�nios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findAllConvenio() throws SmartEnvException, SmartAppException {
		return convenioRN.findAll();
	}
	
	
	/**
	 * Obt�m um conv�nio por chave.
	 * @param vo Inst�ncia de ConvenioVO que encapsula o c�digo do conv�nio que se deseja recuperar.
	 * @return Conv�nio desejado.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO findConvenioByPrimaryKey(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.findByPrimaryKey(vo);
	}
	
	
	/**
	 * Atualiza um conv�nio.
	 * @param vo Conv�nio que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void updateConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioRN.update(vo);
	}
	
	
	/**
	 * Exclui um conv�nio.
	 * @param vo Conv�nio que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void removeConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		convenioRN.remove(vo);
	}

	
	/**
	 * Insere um conv�nio.
	 * @param vo Conv�nio que se deseja inserir.
	 * @return Conv�nio que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public ConvenioVO insertConvenio(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.insert(vo);
	}	
	
	
	/**
	 * Obt�m os conv�nios atraves de crit�rios de filtro.
	 * @param vo Conv�nio com crit�rios de filtro preenchidos.
	 * @return Cole��o de conv�nios, encapsulados na classe ConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<ConvenioVO> findConvenioByFilter(ConvenioVO vo) throws SmartEnvException, SmartAppException {
		return convenioRN.findByFilter(vo);
	}	
	
	/**
	 * Obt�m todos as atividades.
	 * @return Cole��o de todos as atividades, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAllAtividadeConvenio() throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.findAll();
	}
	
	/**
	 * Obt�m todos as atividades.
	 * @return Cole��o de todos as atividades, encapsulados na classe AtividadeConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<AtividadeConvenioVO> findAllAtividadesAtivasConvenio() throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.findAllAtivas();
	}	
	
	
	/**
	 * Obt�m as atividades por chave.
	 * @param vo Inst�ncia de AtividadeConvenioVO que encapsula o c�digo do conv�nio que se deseja recuperar.
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
	 * Obt�m os Beneficiarios atraves de crit�rios de filtro.
	 * @param vo BeneficiarioVO com crit�rios de filtro preenchidos.
	 * @return Cole��o de beneficiarios, encapsulados na classe BeneficiarioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public  Collection<VinculacaoPlanoVO> findVinculadosPlanoByAssociado(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoRN.findVinculadosPlanoByAssociado(vo);
	}	
	
	/**
	 * Insere uma atividade.
	 * @param vo Atividade que se deseja inserir.
	 * @return Conv�nio que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public AtividadeConvenioVO insertAtividadeConvenio(AtividadeConvenioVO vo) throws SmartEnvException, SmartAppException {
		return atividadeConvenioRN.insert(vo);
	}		
	
	
	/**
	 * Obt�m todos os planos.
	 * @return Cole��o de todos os planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findAllPlanoConvenio() throws SmartEnvException, SmartAppException {
		return planoConvenioRN.findAll();
	}
	
	
	/**
	 * Obt�m um plano por chave.
	 * @param vo Inst�ncia de PlanoConvenioVO que encapsula o c�digo do plano que se deseja recuperar.
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
	 * Obt�m os planos atraves de crit�rios de filtro.
	 * @param vo Plano com crit�rios de filtro preenchidos.
	 * @return Cole��o de planos, encapsulados na classe PlanoConvenioVO.
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
	 * Obt�m os benefici�rios por filtro.
	 * @return Cole��o de beneficiarios, encapsulados na classe BeneficiarioVO.
	 * @param BeneficiarioVO
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<BeneficiarioVO> findBeneficiariosByFilter(BeneficiarioVO beneficiario) throws SmartEnvException, SmartAppException {
		return beneficiarioRN.findByFilter(beneficiario);
	}
	
	/**
	 * Obt�m as faturas recentes de um conv�nio.
	 * @return Cole��o de faturas, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 */
	public Collection<FaturaVO> findFaturaByConvenio(ConvenioVO convenio) throws SmartEnvException {
		return faturaRN.findByConvenio(convenio);
	}
	
	public Collection<FaturaVO> findFaturaByLancamento(LancamentoVO lancamento) throws SmartEnvException {
		return faturaRN.findByLancamento(lancamento);
	}
	
	/**
	 * Gerar fatura de um conv�nio de fatura vari�vel.
	 * @return Fatura gerada, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException 
	 */
	public FaturaVO gerarFaturaVariavel(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return faturaVariavelRN.gerar(fatura);
	}
	
	/**
	 * Gerar fatura de um conv�nio de fatura fixa.
	 * @return Fatura gerada, encapsulados na classe FaturaVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException 
	 */
	public FaturaVO gerarFaturaFixa(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		return faturaFixaRN.gerar(fatura);
	}
	
	/**
	 * Gerar pr�via de fatura de um conv�nio de fatura fixa.
	 * @return Fatura pr�via, encapsulados na classe FaturaVO.
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
	
	public Collection<ItemFaturaInconsistenteVO> findRelatorioInconsistenciasByCodigo(FaturaArquivoVO faturaArquivo) throws SmartEnvException {
		return this.faturaFixaRN.findRelatorioInconsistenciasByCodigo(faturaArquivo);
	}
	
	public void updateStatus(FaturaVO fatura) throws SmartEnvException, SmartAppException {
		 faturaRN.updateStatus(fatura);
	}
	
	public Collection<PlanoConvenioVO> findHistoricoValorPlanoByPlano(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioRN.findHistoricoValorPlanoByPlano(vo);
	}
	
	public RelatorioIRVO findRelatorioIR(RelatorioIRVO filtro) throws SmartEnvException, SmartAppException {
		return beneficiarioRN.findRelatorioIR(filtro);
	}
}