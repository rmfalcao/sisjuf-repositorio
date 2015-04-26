package br.org.asserjuf.sisjuf.associados.convenio;

import java.util.Collection;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.dados.PlanoConvenioDAO;

/**
 * Classe que encapsula os m�todos de acesso a Banco de Plano
 * @author Paulo
 *
 */
public class PlanoConvenioRN {

	/**
	 * Classe de acesso ao banco de dados da entidade "Plano Conv�nio" do Sisjuf.
	 */
	private PlanoConvenioDAO	planoConvenioDAO;
	

	public void setPlanoConvenioDAO(PlanoConvenioDAO planoConvenioDAO) {
		this.planoConvenioDAO = planoConvenioDAO;
	}		
	
	/**
	 * Obt�m todos os planos.
	 * @return Cole��o de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findAll() throws SmartEnvException, SmartAppException {
		return planoConvenioDAO.findAll();
	}
	
	/**
	 * Obt�m um plano por chave.
	 * @param vo Inst�ncia de PlanoConvenioVO que encapsula o c�digo do conv�nio que se deseja recuperar.
	 * @return Conv�nio desejada.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO findByPrimaryKey(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		return planoConvenioDAO.findByPrimaryKey(vo);
	}
	
	/**
	 * Atualiza um plano.
	 * @param vo Plano que se deseja atualizar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void update(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {			
		
		validateFields(vo);	
		
		PlanoConvenioVO planoVelho = this.findByPrimaryKey(vo);
		
		if (planoVelho.getValor().doubleValue() != vo.getValor().doubleValue()){
			planoConvenioDAO.insertHistorico(planoVelho);
		}	
		
		planoConvenioDAO.update(vo);
		
		System.out.println("FIM");
	}
	
	/**
	 * Exclui um Plano.
	 * @param vo Plano que se deseja excluir.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public void remove(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
			planoConvenioDAO.remove(vo);		
	}
	
	/**
	 * Insere um plano.
	 * @param vo Plano que se deseja inserir.
	 * @return Plano que foi inserido.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public PlanoConvenioVO insert(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		
		validateFields(vo);
		
		return planoConvenioDAO.insert(vo);
	}
	
	/**
	 * Obt�m os planos atraves de crit�rios de filtro.
	 * @param vo PlanoConvenioVO com crit�rios de filtro preenchidos.
	 * @return Cole��o de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findByFilter(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {
		return planoConvenioDAO.findByFilter(vo);
	}	
	
	/**
	 * Verifica se os campos obrigat�rios do "Plano" est�o preenchidos.
	 * @param vo Plano cujos dados se deseja verificar.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	private void validateFields(PlanoConvenioVO vo) throws SmartEnvException, SmartAppException {				
		

		if (vo.getNome() == null || vo.getNome().equals("")) {
			throw new SmartAppException("O nome deve ser preenchido.");
		}
		
		if (vo.getValor() == null) {
			throw new SmartAppException("O valor deve ser preenchido.");
		}	
		
		if (vo.getDataInicio() == null) {
			throw new SmartAppException("A data de início deve ser preenchido.");
		}				
		
		//Caso seja uma atualiza��o
		if (vo.getCodigo() != null){
			PlanoConvenioVO planoVelho =this.findByPrimaryKey(vo);
			//Caso a dataInicio seja mudada ent�o a nova deve ser maior que a anterior caso a mesma exista.
			if (vo.getDataInicio()!= null && planoVelho.getDataInicio() != null
					&& ( vo.getDataInicio().getTime() < planoVelho.getDataInicio().getTime())){
				throw new SmartAppException("A nova data inicio deve ser maior que a data inicio anterior.");
			}
		}
	}
	
	/**
	 * Obt�m os HISTORICO DE VALORES DO PLANO atraves de crit�rios de filtro.
	 * @param vo PlanoConvenioVO com crit�rios de filtro preenchidos.
	 * @return Cole��o de planos, encapsulados na classe PlanoConvenioVO.
	 * @throws SmartEnvException
	 * @throws SmartAppException
	 */
	public Collection<PlanoConvenioVO> findHistoricoValorPlanoByPlano(PlanoConvenioVO planoConvenio) throws SmartEnvException, SmartAppException {
		if(planoConvenio != null && planoConvenio.getCodigo() != null){
			return planoConvenioDAO.findHistoricoValorPlanoByPlano(planoConvenio);
		}else{
			throw new SmartAppException("O campo plano deve ser preenchido.");
		}
	}
}