package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.VinculacaoPlanoDAO;

/**
 * Classe que encapsula os m�todos de acesso a Banco da entidade que guarda a vincula��o da pessoa com o plano
 * @author Paulo
 *
 */
public class VinculacaoPlanoRN {

	
	private BeneficiarioRN	beneficiarioRN;
	
	/**
	 * Classe de acesso ao banco de dados da entidade "VinculadoPlano" do Sisjuf.
	 */
	private VinculacaoPlanoDAO	vinculacaoPlanoDAO;

	public void setvinculacaoPlanoDAO(VinculacaoPlanoDAO vinculacaoPlanoDAO) {
		this.vinculacaoPlanoDAO = vinculacaoPlanoDAO;
	}
	
	/**
	 * Obt�m os vinculados atraves de crit�rios de filtro.
	 * @param vo VinculacaoPlanoVO com crit�rios de filtro preenchidos.
	 * @return Cole��o de beneficiarios, encapsulados na classe VinculadoPlanoAssembler.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<VinculacaoPlanoVO> findVinculadosPlanoByAssociado(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoDAO.findVinculadosPlanoByAssociado(vo);
	}		
	
	
	/**
	 * Insere uma Vincula��o ao Plano de uma pessoa.
	 * @param vo VinculacaoPlanoVO que se deseja inserir.
	 * @return VinculacaoPlanoVO que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public VinculacaoPlanoVO insert(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {

		validateFields(vo);
	
		VinculacaoPlanoVO voOld = vinculacaoPlanoDAO.findByFilter(vo);
		
		if (voOld != null && voOld.getCodigo()!= null){
			vo.setCodigo(voOld.getCodigo());
			vinculacaoPlanoDAO.update(vo);		
		}else{		
			vo =  vinculacaoPlanoDAO.insert(vo);			
		}		

		vinculacaoPlanoDAO.insertHistorico(vo);

		return vo;
		
	}	
	
	/**
	 * atualiza uma vincula��o.
	 * @param vo VinculacaoPlanoVO que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		validateFields(vo);		

		vinculacaoPlanoDAO.update(vo);
		
		VinculacaoPlanoVO histVO =  vinculacaoPlanoDAO.findHistoricoByVinculacao(vo);
		histVO.setDataVinculacao(vo.getDataVinculacao());
		histVO.setDataDesVinculacao(vo.getDataDesVinculacao());		
		
		vinculacaoPlanoDAO.updateHistorico(histVO);
		
	}		
	
	/**
	 * Verifica se os campos obrigat�rios de "vincula��o" est�o preenchidos.
	 * @param vo VinculacaoPlanoVO cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		if (vo.getPessoa() == null || vo.getPessoa().getCodigo() == null) {
			throw new SmartAppException("A pessoa deve ser preenchido.");
		}
		if (vo.getAssociado() == null || vo.getAssociado().getCodigo() == null) {
			throw new SmartAppException("O associado deve ser preenchido.");
		}
		
		if (vo.getPlano() == null || vo.getPlano().getCodigo() == null) {
			throw new SmartAppException("O plano deve ser preenchido.");
		}
		
		if (vo.getDataVinculacao() == null) {
			throw new SmartAppException("A data de vinculação deve ser preenchido.");
		}		
	}
	
	/**
	 * Obt�m os historicos de vincula��o atraves de crit�rios de filtro.
	 * @param vo VinculadoPlanoAssembler com crit�rios de filtro preenchidos.
	 * @return Cole��o de historicoVinculados, encapsulados na classe VinculadoPlanoAssembler.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<VinculadoPlanoAssembler> findHistoricoVinculadosPlanoByFilter(VinculadoPlanoAssembler vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoDAO.findHistoricoVinculadosPlanoByFilter(vo);
	}	
	
	/**
	 * Retorna o objeto vincula��o filtrando pela chave primaria (codigo) 
	 * @param vo
	 * @return obj plano convenio
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public VinculacaoPlanoVO findByPrimaryKey(VinculacaoPlanoVO vo) throws SmartEnvException, SmartAppException {
		return vinculacaoPlanoDAO.findByPrimaryKey(vo);
	}

	public void setBeneficiarioRN(BeneficiarioRN beneficiarioRN) {
		this.beneficiarioRN = beneficiarioRN;
	}

	public void setVinculacaoPlanoDAO(VinculacaoPlanoDAO vinculacaoPlanoDAO) {
		this.vinculacaoPlanoDAO = vinculacaoPlanoDAO;
	}		
	
}
